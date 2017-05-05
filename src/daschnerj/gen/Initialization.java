package daschnerj.gen;

import daschnerj.gen.data.config.ConfigHandler;
import daschnerj.gen.display.Display;
import daschnerj.gen.gfx.Assets;
import daschnerj.gen.gfx.GameCamera;
import daschnerj.gen.state.GameState;
import daschnerj.gen.state.State;
import daschnerj.gen.state.gui.AboutState;
import daschnerj.gen.state.gui.CreateState;
import daschnerj.gen.state.gui.LoadState;
import daschnerj.gen.state.gui.MenuState;
import daschnerj.gen.state.gui.OptionsState;
import daschnerj.gen.state.gui.PauseState;
import daschnerj.gen.state.gui.SaveState;
import daschnerj.gen.utils.Utils;

public class Initialization {

	/**
	 * The game object that allows access.
	 */
	private final Game game;

	/**
	 * Initializes the game.
	 *
	 * @param game
	 */
	public Initialization(final Game game) {
		this.game = game;
		System.out.println("Location of jar is: " + Utils.getDirectory());
	}

	/**
	 * Creates the basic states for the game. Creates menus and GUI.
	 */
	private void createStates() {
		// Create new game state.
		game.setGameState(new GameState(game.getHandler()));
		game.setMenuState(new MenuState(game.getHandler()));
		game.setAboutState(new AboutState(game.getHandler()));
		game.setCreateState(new CreateState(game.getHandler()));
		game.setLoadState(new LoadState(game.getHandler()));
		game.setOptionsState(new OptionsState(game.getHandler()));
		game.setPauseState(new PauseState(game.getHandler()));
		game.setSaveState(new SaveState(game.getHandler()));
		// Sets the current game state.
		State.setState(game.getMenuState());
	}

	/**
	 * Runs the initializations.
	 */
	public void init() {
		preInit();
		createStates();
		postInit();
	}

	/**
	 * Loads all the initial needed assets.
	 */
	private void loadAssets() {

		System.out.println("Trying to run from: " + Utils.getDirectory() + "\\Assets.jar");
		/**
		 * Runs the GenInstaller jar to place initial files.
		 */
		Utils.runJar(Utils.getDirectory() + "\\Assets.jar");
		/**
		 * Loads the assets into the game.
		 */
		Assets.init();
	}

	/**
	 * Loads the player's Camera, focused on the player.
	 */
	private void loadCamera() {
		game.setGameCamera(new GameCamera(game.getHandler(), 0, 0));
	}

	/**
	 * Loads and creates the configs.
	 */
	private void loadConfigs() {
		game.setConfigHandler(new ConfigHandler());
	}

	/**
	 * Creates the initial display for the game and activates initial inputs.
	 */
	private void loadDisplay() {

		// Creates the display with width and height.
		final Display display = new Display(game.getTitle(), game.getWidth(), game.getHeight());
		// Gets the form of input.
		display.getFrame().addKeyListener(game.getKeyManager());
		// Need both frame and canvas depending on computer and prevent
		// glitches.
		display.getFrame().addMouseListener(game.getMouseManager());
		display.getFrame().addMouseMotionListener(game.getMouseManager());
		display.getCanvas().addMouseListener(game.getMouseManager());
		display.getCanvas().addMouseMotionListener(game.getMouseManager());
		game.setDisplay(display);
	}

	/**
	 * Creates the initial Handler used to control the overall game.
	 */
	private void loadHandler() {
		game.setHandler(new Handler(game));
	}

	/**
	 * Runs the post game objects.
	 */
	public void postInit() {

	}

	/**
	 * Things that must be loaded before the game can fully run.
	 */
	public void preInit() {
		loadConfigs();
		loadDisplay();
		loadAssets();
		loadHandler();
		loadCamera();
	}

	/**
	 * private void exportResources() { System.out.println("Exporting
	 * resources..."); try {
	 *
	 * Utils.exportResource("/slkscr.ttf", "\\gen\\resources\\fonts");
	 * Utils.exportResource("/inventoryScreen.png",
	 * "\\gen\\resources\\textures\\interface");
	 * Utils.exportResource("/sheet.png", "\\gen\\resources\\textures\\tiles");
	 *
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

}
