package daschnerj.gen.data.levels.generation;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import daschnerj.gen.data.levels.generation.noise.SimplexNoise;
import daschnerj.gen.data.levels.generation.perlin.Noise;
import daschnerj.gen.data.levels.generation.threes.FractalTerrain;
import daschnerj.gen.data.levels.generation.threes.RGB;
import daschnerj.gen.data.levels.generation.threes.Terrain;
import daschnerj.gen.data.levels.generation.threes.Triple;

public class MapGen {

	int width = 1000;
	int height = 1000;
	int[][] arr = new int[width][height];

	public MapGen() {
		/*generateNoise();
		createImage();
		System.out.println(SimplexNoise.p.length);*/
		
		//generateFractalTerrain();
		Random rand = new Random();
		Noise n = new Noise(rand, 1.0f, width, height);
		n.initialise();
		//n.printAsCSV();
		arr = n.getArray();
		createImage();
	}
	
	/**public void generateFractalTerrain()
	{
		double exaggeration = .7;
		int lod = 5;
		int steps = 1 << lod;
		Triple[][] map = new Triple[steps + 1][steps + 1];
		RGB[][] colors = new RGB[steps + 1][steps + 1];
		Terrain terrain = new FractalTerrain (lod, .5, width, height);
		for (int i = 0; i <= steps; ++ i) {
		  for (int j = 0; j <= steps; ++ j) {
		    double x = 1.0 * i / steps, z = 1.0 * j / steps;
		    double altitude = terrain.getAltitude (x, z);
		    map[i][j] = new Triple (x, altitude * exaggeration, z);       
		    colors[i][j] = terrain.getColor (x, z);
		  }
		}
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				arr[i][j] = terrain.getColor(i, j).toRGB();
			}
		}
	}

	public int[][] generateNoise() {
		SimplexNoise.genP();
		float frequency = 1.0f / (float) width;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				arr[x][y] = (int) (700 * SimplexNoise.noise(x * frequency, y * frequency));
				arr[x][y] = (arr[x][y] + 1) / 2; // generate values between 0
													// and 1
			}
		}

		return arr;
	}**/

	private void createImage() {
		BufferedImage b = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = (int) arr[x][y] << 16 | (int) arr[x][y] << 8 | (int) arr[x][y];
				b.setRGB(x, y, rgb);
			}
		}
		ImageIcon icon = new ImageIcon(b);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(width, height);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		System.out.println("end");
	}

}
