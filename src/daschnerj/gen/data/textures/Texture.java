package daschnerj.gen.data.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	private BufferedImage image;

	public Texture(final File file) {
		setImage(file);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(final BufferedImage image) {
		this.image = image;
	}

	public void setImage(final File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (final IOException e) {
			this.image = image;
		}
	}

}
