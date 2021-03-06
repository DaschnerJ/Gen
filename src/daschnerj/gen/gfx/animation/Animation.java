package daschnerj.gen.gfx.animation;

import java.awt.image.BufferedImage;

public class Animation {

	private final int speed;
	private int index;
	private long lastTime, timer;
	private final BufferedImage[] frames;

	public Animation(final int speed, final BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length) {
				index = 0;
			}
		}
	}

}
