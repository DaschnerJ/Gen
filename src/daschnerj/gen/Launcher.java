package daschnerj.gen;

import daschnerj.gen.data.levels.generation.MapGen;

public class Launcher {

	public static void main(final String[] args) {

		// Create the game.
		final Game game = new Game("Gen!", 800, 800);
		// Start the game loop.
		game.start();
		new MapGen();

	}

}
