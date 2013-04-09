package coursera.seamcarving;

import com.coursera.algorithms.stdlib.Picture;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;

import static coursera.seamcarving.SeamUtil.energyMatrix;
import static org.fest.assertions.api.Assertions.assertThat;

public class SeamCarverTest {
	@Test
	public void pseudoGraph() {
		SeamCarver carver = new SeamCarver(largeTestPic());
		int[] actual = carver.findVerticalSeam();
		assertThat(actual).isEqualTo(new int[]{2, 3, 3, 3, 2});
		actual = carver.findHorizontalSeam();
		assertThat(actual).isEqualTo(new int[]{2, 3, 3, 3, 2, 1});
	}

	@DataProvider(name = "energyData")
	static Object[][] energyData() {
		return new Object[][]{
			{testPic(), testPicEnergy()},
			{largeTestPic(), largeTestPicEnergy()}
		};
	}

	@Test(dataProvider = "energyData")
	public void energy(Picture pic, double[][] expected) {
		SeamCarver carver = new SeamCarver(pic);
		double[][] actual = energyMatrix(carver);
		assertThat(actual).isEqualTo(expected);
	}

	static Picture makePic(int X, int Y, int[] data) {
		Picture pic = new Picture(X, Y);
		for(int x = 0; x < X; x++)
			for(int y = 0; y < Y; y++) {
				int ptr = y * X * 3 + x * 3;
				pic.set(x, y, new Color(data[ptr + 0], data[ptr + 1], data[ptr + 2]));
			}
		return pic;
	}

	static Picture testPic() {
		return makePic(3, 4, new int[]{
			255, 101, 51, 255, 101, 153, 255, 101, 255,
			255, 153, 51, 255, 153, 153, 255, 153, 255,
			255, 203, 51, 255, 204, 153, 255, 205, 255,
			255, 255, 51, 255, 255, 153, 255, 255, 255
		});
	}

	static double[][] testPicEnergy() {
		return new double[][]{
			{195075, 195075, 195075},
			{195075, 52225, 195075},
			{195075, 52024, 195075},
			{195075, 195075, 195075}
		};
	}

	static Picture largeTestPic() {
		return makePic(6, 5, new int[]{
			97, 82, 107, 220, 172, 141, 243, 71, 205, 129, 173, 222, 225, 40, 209, 66, 109, 219,
			181, 78, 68, 15, 28, 216, 245, 150, 150, 177, 100, 167, 205, 205, 177, 147, 58, 99,
			196, 224, 21, 166, 217, 190, 128, 120, 162, 104, 59, 110, 49, 148, 137, 192, 101, 89,
			83, 143, 103, 110, 79, 247, 106, 71, 174, 92, 240, 205, 129, 56, 146, 121, 111, 147,
			82, 157, 137, 92, 110, 129, 183, 107, 80, 89, 24, 217, 207, 69, 32, 156, 112, 31,
		});
	}

	static double[][] largeTestPicEnergy() {
		return new double[][]{
			{195075.0, 195075.0, 195075.0, 195075.0, 195075.0, 195075.0},
			{195075.0, 23346.0, 51304.0, 31519.0, 55112.0, 195075.0},
			{195075.0, 47908.0, 61346.0, 35919.0, 38887.0, 195075.0},
			{195075.0, 31400.0, 37927.0, 14437.0, 63076.0, 195075.0},
			{195075.0, 195075.0, 195075.0, 195075.0, 195075.0, 195075.0}
		};
	}
}


