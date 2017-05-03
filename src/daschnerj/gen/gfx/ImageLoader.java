package daschnerj.gen.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import daschnerj.gen.utils.Utils;

public class ImageLoader {

	public static BufferedImage loadImage(final String path, final String name) {
		final String fullPath = Utils.getDirectory() + "\\" + path + "\\" + name;
		System.out.println("Directory check: " + fullPath);
		BufferedImage img = null;

		try {
			System.out.println("Attempting to load resource...");
			img = ImageIO.read(new File(fullPath));
			System.out.println("Loaded resource!");
			return img;
		} catch (final IOException e) {
			System.out.println("Failed to load image at: " + fullPath);

		}
		return null;
	}

}
