package daschnerj.gen.data.levels.generation;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import daschnerj.gen.data.levels.generation.perlin.Noise;
import daschnerj.gen.data.levels.generation.perlin.Smooth;

public class MapGen {

	int width = 1200;
	int height = 900;
	int[][] arr = new int[width][height];

	public MapGen() {
		/*generateNoise();
		createImage();
		System.out.println(SimplexNoise.p.length);*/
		
		//generateFractalTerrain();
		int h = 10;
		Random rand = new Random();
		Noise n = new Noise(rand, 10.0f, width, height);
		n.initialise();
		//n.printAsCSV();
		int[][] a1 = n.getArray(h);
		//createImage(a1);
		Noise n2 = new Noise(rand, 1.0f, width, height);
		n2.initialise();
		//n.printAsCSV();
		int[][] a2 = n2.getArray(h);
		//createImage(a2);
		int[][] a3 = addArrays(a1, a2);
		//createImage(a3);
		Noise n3 = new Noise(rand, 5.0f, width, height);
		n3.initialise();
		int[][] a4 = n3.getArray(h);
		a4 = addArrays(a3, a4);
		createImage(a4);
		Smooth sm = new Smooth(width, height);
		for(int i = 0; i < 3; i++)
		{
			sm.setArray(a4);
			a4 = sm.smoothTiles();
		}
		createImage(a4);
		
		int[] colors = {getColor(255,0,0,255), getColor(0,255,0,255), getColor(0,0,255,255)};
		int[] ranges = {-300, -25, -24, 25, 26, 300};
		//System.out.println(Arrays.deepToString(a4));
		int[][] a5 = colorArray(colors, ranges, a4);
		createImage(a5);
		int[][] a6 = colorArray(colors, ranges, a3);
		createImage(a6);
				
		
		
		
	}
	
	private void printArray(int[][] ar)
	{
		for (int i = 0; i < ar.length; i++) {
		    for (int j = 0; j < ar[i].length; j++) {
		        System.out.print(ar[i][j] + " ");
		    }
		    System.out.println();
		}
	}
	
	private int getColor(int r, int g, int b, int a)
	{
		int col = (a << 24) | (r << 16) | (g << 8) | b;
		return col;
	}
	
	private int[][] addArrays(int[][] a, int [][] b)
	{
		int[][] c = new int[width][height];
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		
		return c;
		
	}
	
	private int[][] colorArray(int[] colors, int[] range, int[][] ar)
	{
		int[][] array = new int[ar.length][ar[0].length];
		//printArray(ar);
		for(int i = 0; i < colors.length; i++)
		{
			int color = colors[i];
			int min = range[i*2];
			int max = range[i*2+1];
			//System.out.println("Setting color ints: " + color);
			for(int j = 0; j < ar.length; j++)
			{
				for(int k = 0; k < ar[0].length; k++)
				{
					if(ar[j][k]>=min && ar[j][k] <= max)
					{
						array[j][k] = color;
					}
				}
			}
		}
	
		return array;
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

	private void createImage(int[][] array) {
		BufferedImage b = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				b.setRGB(x, y, array[x][y]);
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
