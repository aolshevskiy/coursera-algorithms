package coursera.seamcarving;

import com.coursera.algorithms.algs4.DirectedEdge;
import com.coursera.algorithms.stdlib.Picture;

import java.awt.*;

public class SeamCarver {
	private final int ow, oh;
	private int h, w;
	private final int[] data;

	public SeamCarver(Picture picture) {
		ow = w = picture.width();
		oh = h = picture.height();
		data = new int[w*h*3];
		for(int x=0;x<w;x++)
			for(int y=0;y<h;y++)
				set(x, y, picture.get(x, y));
	}

	private void checkDimension(int dimension) {
		if(dimension <= 1)
			throw new IllegalArgumentException();
	}

	private void checkSeam(int[] seam, int expectedLength) {
		if(seam.length != expectedLength)
			throw new IllegalArgumentException(String.format("Invalid seam length %d, expected %d", seam.length, expectedLength));
		for(int i=0;i<seam.length-1;i++)
			if(Math.abs(seam[i]-seam[i+1]) > 1)
				throw new IllegalArgumentException();
	}

	public void removeVerticalSeam(int[] seam) {
		checkDimension(w);
		checkSeam(seam, h);
		for(int y=0;y<h;y++)
			for(int x=0;x<w;x++)
				if(x != seam[y])
					set(x<seam[y]?x:x-1,y,get(x,y));
		w--;
	}

	public void removeHorizontalSeam(int[] seam) {
		checkDimension(h);
		checkSeam(seam, w);
		for(int y=0;y<h;y++)
			for(int x=0;x<w;x++)
				if(y != seam[x])
					set(x,y<seam[x]?y:y-1,get(x,y));
		h--;
	}

	private static int[] findSeam(int d1, int d2, double[][] energyMatrix) {
		PseudoGraph g = new PseudoGraph(energyMatrix);
		SeamSP sp = new SeamSP(g, 0);
		int[] result = new int[d2];
		int i = 0;
		for(DirectedEdge e: sp.pathTo(g.V()-1)) {
			if(i == d2)
				return result;
			result[i] = (e.to()-1)%d1;
			i++;
		}
		assert false: "Should never reach here";
		return null;
	}

	public int[] findVerticalSeam() {
		return findSeam(width(), height(), SeamUtil.energyMatrix(this));
	}

	public int[] findHorizontalSeam() {
		return findSeam(height(), width(), SeamUtil.transposedEnergyMatrix(this));
	}

	public double energy(int x, int y) {
		if(x < 0 || x >= width() || y < 0 || y >= height())
			throw new IndexOutOfBoundsException();
		if(x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
			return SeamUtil.MAX_ENERGY;
		return SeamUtil.deltaSquare(get(x + 1, y), get(x - 1, y))
			+ SeamUtil.deltaSquare(get(x, y + 1), get(x, y - 1));
	}

	public int width() {
		return w;
	}

	public int height() {
		return h;
	}

	private Color get(int x, int y) {
		return new Color(
			data[y*ow*3+x*3+0],
			data[y*ow*3+x*3+1],
			data[y*ow*3+x*3+2]);
	}

	private void set(int x, int y, Color color) {
		data[y*ow*3+x*3+0] = color.getRed();
		data[y*ow*3+x*3+1] = color.getGreen();
		data[y*ow*3+x*3+2] = color.getBlue();
	}

	public Picture picture() {
		Picture result = new Picture(w, h);
		for(int x=0;x<w;x++)
			for(int y=0;y<h;y++)
				result.set(x,y,get(x,y));
		return result;
	}
}