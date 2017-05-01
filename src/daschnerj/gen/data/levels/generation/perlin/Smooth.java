package daschnerj.gen.data.levels.generation.perlin;

public class Smooth {
	
	private int width, height;
	private int[][] arr = new int[width][height];
	private int[][] temp = new int[width][height];
	
	public Smooth(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public int[][] smoothTiles()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				temp[i][j] = getValueAverage(i, j);
			}
		}
		
		arr = temp;
		return arr;
	}
	
	public void setArray(int[][] a)
	{
		arr = a;
		temp = a;
	}
	
	
	public int getValueAverage(int x, int y)
	{
		int value = 0;
		int count = 0;
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				if(x == 0 && y == 0)
					continue;
				if(x+i < 0 || y+j < 0 || x+i >= width || y+j >= height)
					continue;
				value = value + arr[x+i][y+j];
				count++;
			}
		}
		if(count == 0)
			return arr[x][y];
		return (value/count);
	}
	
	

}
