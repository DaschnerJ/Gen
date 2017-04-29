package daschnerj.gen.data.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	private BufferedImage image;
	
	public Texture(File file)
	{
		setImage(file);
	}
	
	public void setImage(File file)
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(file);
		}
		catch(IOException e)
		{
			this.image = image;
		}
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	

}
