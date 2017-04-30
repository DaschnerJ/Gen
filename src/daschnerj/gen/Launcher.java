package daschnerj.gen;

import daschnerj.gen.data.levels.generation.MapGen;

public class Launcher {

	public static void main(String[] args) {

		// Create the game.
		Game game = new Game("Gen!", 600, 600);
		// Start the game loop.
		game.start();
		new MapGen();

	}

}
