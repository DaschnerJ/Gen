package daschnerj.gen.data.sounds;

public class SoundVector {
	private final float x, y;

	public SoundVector(final float x, final float y) {
		this.x = x;
		this.y = y;
	}

	public float getMagnitude() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public SoundVector normalize() {
		final float magnitude = this.getMagnitude();
		if (magnitude == 0)
			return new SoundVector(0, 0);
		return new SoundVector(x / magnitude, y / magnitude);
	}

	public SoundVector subtract(final SoundVector subtract) {
		return new SoundVector(this.x - subtract.x, this.y - subtract.y);
	}

	@Override
	public String toString() {
		return "{SoundVector(" + x + "," + y + ")}";
	}
}