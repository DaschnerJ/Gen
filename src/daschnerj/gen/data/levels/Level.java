package daschnerj.gen.data.levels;

import java.io.File;
import java.util.HashMap;

import daschnerj.gen.data.files.Lines;

public class Level extends Lines {

	private final int[] d;
	private final String[][] tiles;
	private String[][] temperature;
	private String[][] rainfall;
	private final HashMap<String, String[]> entityData = new HashMap<>();

	public Level(final File f) {
		super(f);
		d = getLevelDimensions();
		tiles = new String[d[0]][d[1]];
		loadTiles();
		loadTemperature();
		loadRainfall();
		loadEntityData();
	}

	private int[] getArray(final String l, final String del) {
		final int[] d = new int[l.split(del).length];
		final String[] s = l.split(del);
		for (int i = 0; i < s.length; i++) {
			d[i] = Integer.valueOf(s[i]);
		}
		return d;
	}

	public int[] getLevelDimensions() {
		return getArray(lines.get(0), ":");
	}

	public int[] getLevelPosition() {
		return getArray(lines.get(0), ":");
	}

	public boolean hasPlayer() {
		return entityData.containsKey("Player");
	}

	private boolean loadEntityData() {
		final int diff = (2 * d[0]) + d[0] + 2;
		final int end = lines.size();
		if (diff >= end)
			return true;
		for (int i = diff; i < end; i++) {
			final String[] s = lines.get(i).split(":");
			entityData.put(s[0], s);
		}
		return true;
	}

	private boolean loadRainfall() {
		final int diff = (2 * d[0]) + 2;
		final int end = (2 * d[0]) + d[0] + 2;
		for (int i = diff; i < end; i++) {
			rainfall[i - diff] = lines.get(i).split(" ");
		}
		return true;
	}

	private boolean loadTemperature() {
		final int diff = d[0] + 2;
		final int end = d[0] + d[0] + 2;
		for (int i = diff; i < end; i++) {
			temperature[i - diff] = lines.get(i).split(" ");
		}
		return true;
	}

	private boolean loadTiles() {
		final int diff = 2;
		final int end = getLevelDimensions()[0] + 2;
		for (int i = diff; i < end; i++) {
			tiles[i - 2] = lines.get(i).split(" ");
		}
		return true;
	}

}
