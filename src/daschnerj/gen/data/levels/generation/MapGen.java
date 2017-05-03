package daschnerj.gen.data.levels.generation;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import daschnerj.gen.data.levels.generation.perlin.Noise;
import daschnerj.gen.data.levels.generation.perlin.Populator;
import daschnerj.gen.data.levels.generation.perlin.Smooth;

public class MapGen {

	int width = 1200;
	int height = 900;
	int[][] arr = new int[width][height];

	public MapGen() {

		GenerateBase(1200, 900, 10, 3, 5.0f, 3, new Random());

	}

	private int[][] addArrays(final int[][] a, final int[][] b) {
		final int[][] c = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}

		return c;

	}

	private int[][] colorArray(final int[] colors, final int[] range, final int[][] ar) {
		final int[][] array = new int[ar.length][ar[0].length];
		for (int i = 0; i < colors.length; i++) {
			final int color = colors[i];
			final int min = range[i * 2];
			final int max = range[(i * 2) + 1];
			for (int j = 0; j < ar.length; j++) {
				for (int k = 0; k < ar[0].length; k++) {
					if ((ar[j][k] >= min) && (ar[j][k] <= max)) {
						array[j][k] = color;
					}
				}
			}
		}

		return array;
	}

	private void createImage(final int[][] array) {
		final BufferedImage b = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				b.setRGB(x, y, array[x][y]);
			}
		}

		final ImageIcon icon = new ImageIcon(b);
		final JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(width, height);
		final JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		System.out.println("end");
	}

	private int[][] GenerateBase(final int width, final int height, final int hardness, final int smoothing,
			final float roughness, final int layers, final Random r) {
		int[][] array = new int[width][height];
		final ArrayList<Noise> layerMap = new ArrayList<>();

		final Smooth sm = new Smooth(width, height);

		for (int i = 0; i < layers; i++) {
			final Noise n = new Noise(r, roughness, width, height);
			n.initialise();
			layerMap.add(n);
		}
		for (final Noise n : layerMap) {
			array = addArrays(array, n.getArray(hardness));
		}
		for (int i = 0; i < smoothing; i++) {
			sm.setArray(array);
			array = sm.smoothTiles();
		}

		final int[] colors = { getColor(255, 0, 0, 255), getColor(0, 255, 0, 255), getColor(0, 0, 255, 255) };
		final int[] ranges = { -400, -25, -24, 25, 26, 400 };
		final int[][] a5 = colorArray(colors, ranges, array);
		createImage(a5);
		final Populator pop = new Populator(width, height, a5);
		pop.addIgnore(getColor(255, 0, 0, 255));
		pop.addIgnore(getColor(0, 0, 255, 255));
		pop.setChance(10);
		pop.setPop(getColor(0, 0, 0, 255));
		pop.randomIgnorePopulation();
		createImage(pop.getMap());

		return array;
	}

	private int getColor(final int r, final int g, final int b, final int a) {
		final int col = (a << 24) | (r << 16) | (g << 8) | b;
		return col;
	}

	@SuppressWarnings("unused")
	private void printArray(final int[][] ar) {
		for (final int[] element : ar) {
			for (final int element2 : element) {
				System.out.print(element2 + " ");
			}
			System.out.println();
		}
	}

}
