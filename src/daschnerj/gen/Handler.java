package daschnerj.gen;

import daschnerj.gen.gfx.GameCamera;
import daschnerj.gen.input.KeyManager;
import daschnerj.gen.input.MouseManager;
import daschnerj.gen.worlds.World;

public class Handler {

	/**
	 * Handler contains basic functions to get and set values within the over
	 * all game.
	 */
	private Game game;
	private World world;

	public Handler(final Game game) {

		this.game = game;

	}

	public Game getGame() {
		return game;
	}

	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public int getWidth() {
		return game.getWidth();
	}

	public World getWorld() {
		return world;
	}

	public void setGame(final Game game) {
		this.game = game;
	}

	public void setWorld(final World world) {
		this.world = world;
	}

}
