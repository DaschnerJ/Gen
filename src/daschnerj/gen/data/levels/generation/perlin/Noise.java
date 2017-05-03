package daschnerj.gen.data.levels.generation.perlin;

import java.util.Random;

public class Noise {
	/** Source of entropy */
	private final Random rand_;

	/** Amount of roughness */
	float roughness_;

	/** Plasma fractal grid */
	private final float[][] grid_;

	/**
	 * Generate a noise source based upon the midpoint displacement fractal.
	 *
	 * @param rand
	 *            The random number generator
	 * @param roughness
	 *            a roughness parameter
	 * @param width
	 *            the width of the grid
	 * @param height
	 *            the height of the grid
	 */
	public Noise(final Random rand, final float roughness, final int width, final int height) {
		roughness_ = roughness / width;
		grid_ = new float[width][height];
		rand_ = (rand == null) ? new Random() : rand;
	}

	// generate the fractal
	private void generate(final int xl, final int yl, final int xh, final int yh) {
		final int xm = (xl + xh) / 2;
		final int ym = (yl + yh) / 2;
		if ((xl == xm) && (yl == ym))
			return;

		grid_[xm][yl] = 0.5f * (grid_[xl][yl] + grid_[xh][yl]);
		grid_[xm][yh] = 0.5f * (grid_[xl][yh] + grid_[xh][yh]);
		grid_[xl][ym] = 0.5f * (grid_[xl][yl] + grid_[xl][yh]);
		grid_[xh][ym] = 0.5f * (grid_[xh][yl] + grid_[xh][yh]);

		final float v = roughen(0.5f * (grid_[xm][yl] + grid_[xm][yh]), xl + yl, yh + xh);
		grid_[xm][ym] = v;
		grid_[xm][yl] = roughen(grid_[xm][yl], xl, xh);
		grid_[xm][yh] = roughen(grid_[xm][yh], xl, xh);
		grid_[xl][ym] = roughen(grid_[xl][ym], yl, yh);
		grid_[xh][ym] = roughen(grid_[xh][ym], yl, yh);

		generate(xl, yl, xm, ym);
		generate(xm, yl, xh, ym);
		generate(xl, ym, xm, yh);
		generate(xm, ym, xh, yh);
	}

	public int[][] getArray(final int hardness) {
		final int[][] arr = new int[grid_.length][grid_[0].length];
		for (int i = 0; i < grid_.length; i++) {
			for (int j = 0; j < grid_[0].length; j++) {
				arr[i][j] = (int) (hardness * grid_[i][j]);
			}
			// System.out.println();
		}
		return arr;
	}

	public void initialise() {
		final int xh = grid_.length - 1;
		final int yh = grid_[0].length - 1;

		// set the corner points
		grid_[0][0] = rand_.nextFloat() - 0.5f;
		grid_[0][yh] = rand_.nextFloat() - 0.5f;
		grid_[xh][0] = rand_.nextFloat() - 0.5f;
		grid_[xh][yh] = rand_.nextFloat() - 0.5f;

		// generate the fractal
		generate(0, 0, xh, yh);
	}

	/**
	 * Dump out as a CSV
	 */
	public void printAsCSV() {
		for (final float[] element : grid_) {
			for (int j = 0; j < grid_[0].length; j++) {
				System.out.print(element[j]);
				System.out.print(",");
			}
			System.out.println();
		}
	}

	// Add a suitable amount of random displacement to a point
	private float roughen(final float v, final int l, final int h) {
		return v + (roughness_ * (float) (rand_.nextGaussian() * (h - l)));
	}

	/**
	 * Convert to a Boolean array
	 *
	 * @return the boolean array
	 */
	public boolean[][] toBooleans() {
		final int w = grid_.length;
		final int h = grid_[0].length;
		final boolean[][] ret = new boolean[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				ret[i][j] = grid_[i][j] < 0;
			}
		}
		return ret;
	}

}
