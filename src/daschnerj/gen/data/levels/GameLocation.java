package daschnerj.gen.data.levels;

public class GameLocation {

	int x;
	int y;
	int id = -1;

	public GameLocation(final int x, final int y) {
		this.x = x;
		this.y = y;

	}

	public GameLocation(final int x, final int y, final int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public int[] get() {
		final int[] a = { x, y };
		return a;
	}

	public boolean hasId() {
		if (id != -1)
			return true;
		return false;
	}

}
