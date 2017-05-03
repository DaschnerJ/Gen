package daschnerj.gen.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	// The image sheet to pull images from.
	private final BufferedImage sheet;

	// The SpriteSheet constructor.
	public SpriteSheet(final BufferedImage sheet) {
		this.sheet = sheet;
	}

	// Obtains the sub image from the sprite sheet.
	public BufferedImage crop(final int x, final int y, final int width, final int height) {
		return sheet.getSubimage(x, y, width, height);
	}

}
