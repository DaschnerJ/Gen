package daschnerj.gen.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

	private final BufferedImage[] images;
	private final ClickListener clicker;

	public UIImageButton(final float x, final float y, final int width, final int height, final BufferedImage[] images,
			final ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}
	
	public UIImageButton(final float x, final float y, final int width, final int height, final BufferedImage image,
			final ClickListener clicker) {
		super(x, y, width, height);
		this.images = new BufferedImage[]{image, image};
		this.clicker = clicker;
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

	@Override
	public void render(final Graphics g) {
		if (hovering) {
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		} else {
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
		}

	}

	@Override
	public void tick() {

	}

}
