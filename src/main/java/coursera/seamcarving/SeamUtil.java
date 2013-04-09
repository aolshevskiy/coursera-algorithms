package coursera.seamcarving;

import java.awt.*;

class SeamUtil {
	static final double MAX_ENERGY = 195075;

	static double deltaSquare(Color a, Color b) {
		return square(a.getRed() - b.getRed())
			+ square(a.getGreen() - b.getGreen())
			+ square(a.getBlue() - b.getBlue());
	}

	private static double square(double d) {
		return d * d;
	}

	static double[][] energyMatrix(SeamCarver carver) {
		double[][] result = new double[carver.height()][];
		for(int y = 0; y < carver.height(); y++) {
			result[y] = new double[carver.width()];
			for(int x = 0; x < carver.width(); x++)
				result[y][x] = carver.energy(x, y);
		}
		return result;
	}

	static double[][] transposedEnergyMatrix(SeamCarver carver) {
		double[][] result = new double[carver.width()][];
		for(int x = 0; x < carver.width(); x++) {
			result[x] = new double[carver.height()];
			for(int y = 0; y < carver.height(); y++)
				result[x][y] = carver.energy(x, y);
		}
		return result;
	}
}
