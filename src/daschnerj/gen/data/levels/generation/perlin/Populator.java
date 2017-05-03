package daschnerj.gen.data.levels.generation.perlin;

import java.util.ArrayList;
import java.util.Random;

import daschnerj.gen.data.levels.GameLocation;

public class Populator {

	private int height, width;
	private int[][] array = new int[width][height];

	private int pop;
	private int chance;

	private final ArrayList<Integer> ignore = new ArrayList<>();

	private final ArrayList<GameLocation> locations = new ArrayList<>();

	public Populator(final int width, final int height, final int[][] array) {
		this.height = height;
		this.width = width;

		this.array = array;
	}

	public void addIgnore(final int i) {
		ignore.add(i);
	}

	public void cleaarIgnore() {
		ignore.clear();
	}

	public int[][] getMap() {
		return array;
	}

	public void randomIgnorePopulation() {
		final Random rand = new Random();
		final int Low = 0;
		final int High = 100;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				final int Result = rand.nextInt(High - Low) + Low;
				if (Result < chance) {
					if (!ignore.contains(array[i][j])) {
						array[i][j] = pop;
					}
				}

			}
		}
	}

	public void randomPopulation() {
		final Random rand = new Random();
		final int Low = 0;
		final int High = 100;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				final int Result = rand.nextInt(High - Low) + Low;
				if (Result < chance) {
					array[i][j] = pop;
					locations.add(new GameLocation(i, j, pop));
				}

			}
		}
	}

	public void removeIgnore(final int i) {
		ignore.remove(i);
	}

	public void setChance(final int chance) {
		if (chance > 100) {
			this.chance = 100;
		} else if (chance < 0) {
			this.chance = 0;
		} else {
			this.chance = chance;
		}
	}

	public void setPop(final int pop) {
		this.pop = pop;
	}

}
