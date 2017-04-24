package daschnerj.gen.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	// The image sheet to pull images from.
	private BufferedImage sheet;

	// The SpriteSheet constructor.
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	// Obtains the sub image from the sprite sheet.
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

}
