package daschnerj.gen.gfx.animation;

import java.awt.image.BufferedImage;

public class Strip {
	
	private BufferedImage[] strip;
	
	public Strip(BufferedImage image)
	{
		int size = getSize(getWidth(image), getHeight(image));
		if(size != 0)
		{
			int[] c = getChunks(image);
			strip = splitImage(image, c);
			System.out.println("Size of new split image is: " + strip.length);
		}
		else
		{
			strip = new BufferedImage[1];
			strip[0] = image;
		}
	}
	
	private int getWidth(BufferedImage image)
	{
		return image.getWidth();
	}
	
	private int getHeight(BufferedImage image)
	{
		return image.getHeight();
	}
	
	private int getMinDimension(BufferedImage image)
	{
		return Math.min(image.getWidth(), image.getHeight());
	}
	
	private int getSize(int a, int b)
	{
		if(a%b == 0 || b%a == 0)
		{
			return Math.min(a, b);
		}
		else
		{
			return 0;
		}
	}
	
	private int[] getChunks(BufferedImage image)
	{
		return getChunks(image.getWidth(), image.getHeight(), image);
	}
	
	private int[] getChunks(int a, int b, BufferedImage image)
	{
		int d = direction(getWidth(image), getHeight(image));
		int rows, columns;
		
		if(d == 0)
		{
			columns = 1;
			rows = b/a;
		}
		else
		{
			columns = a/b;
			rows = 1;
		}
		int[] c = {rows, columns, rows*columns};
		return c;
	}
	
	private BufferedImage[] splitImage(BufferedImage image, int[] c)
	{
		int rows = c[0];
		int columns = c[1];
		int chunks = c[2];
		int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(getMinDimension(image), getMinDimension(image), image.getType());

      
            }
        }
        
        return imgs;
	}
	
	/**
	 * If 0 is returned then the image is tall, otherwise the image is wide.
	 * @param a Width.
	 * @param b Height.
	 * @return Returns 0 or 1.
	 */
	private int direction(int a, int b)
	{
		if(a < b)
			return 0;
		else
			return 1;
	}

	public BufferedImage[] getStrip() {
		return strip;
	}

	public void setStrip(BufferedImage[] strip) {
		this.strip = strip;
	}
	
	

}
