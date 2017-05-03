package daschnerj.gen.data.textures;

import java.awt.image.BufferedImage;

public class Strip {

	private BufferedImage[] strip;

	public Strip(final BufferedImage image) {
		final int size = getSize(getWidth(image), getHeight(image));
		if (size != 0) {
			final int[] c = getChunks(image);
			strip = splitImage(image, c);
			System.out.println("Size of new split image is: " + strip.length);
		} else {
			strip = new BufferedImage[1];
			strip[0] = image;
		}
	}

	/**
	 * If 0 is returned then the image is tall, otherwise the image is wide.
	 *
	 * @param a
	 *            Width.
	 * @param b
	 *            Height.
	 * @return Returns 0 or 1.
	 */
	private int direction(final int a, final int b) {
		if (a < b)
			return 0;
		else
			return 1;
	}

	private int[] getChunks(final BufferedImage image) {
		return getChunks(image.getWidth(), image.getHeight(), image);
	}

	private int[] getChunks(final int a, final int b, final BufferedImage image) {
		final int d = direction(getWidth(image), getHeight(image));
		int rows, columns;

		if (d == 0) {
			columns = 1;
			rows = b / a;
		} else {
			columns = a / b;
			rows = 1;
		}
		final int[] c = { rows, columns, rows * columns };
		return c;
	}

	private int getHeight(final BufferedImage image) {
		return image.getHeight();
	}

	private int getMinDimension(final BufferedImage image) {
		return Math.min(image.getWidth(), image.getHeight());
	}

	private int getSize(final int a, final int b) {
		if (((a % b) == 0) || ((b % a) == 0))
			return Math.min(a, b);
		else
			return 0;
	}

	public BufferedImage[] getStrip() {
		return strip;
	}

	private int getWidth(final BufferedImage image) {
		return image.getWidth();
	}

	public void setStrip(final BufferedImage[] strip) {
		this.strip = strip;
	}

	private BufferedImage[] splitImage(final BufferedImage image, final int[] c) {
		final int rows = c[0];
		final int columns = c[1];
		final int chunks = c[2];
		final int count = 0;
		final BufferedImage imgs[] = new BufferedImage[chunks]; // Image array
																// to hold image
																// chunks
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				// Initialize the image array with image chunks
				imgs[count] = new BufferedImage(getMinDimension(image), getMinDimension(image), image.getType());

			}
		}

		return imgs;
	}

}
