package daschnerj.gen;

import daschnerj.gen.config.ConfigHandler;
import daschnerj.gen.display.Display;
import daschnerj.gen.gfx.Assets;
import daschnerj.gen.gfx.GameCamera;
import daschnerj.gen.state.GameState;
import daschnerj.gen.state.MenuState;
import daschnerj.gen.state.State;
import daschnerj.gen.utils.Utils;

public class Initialization {

	private Game game;

	public Initialization(Game game) {
		this.game = game;
	}

	public void preInit() {
		loadConfigs();
		loadDisplay();
		loadAssets();
		loadHandler();
		loadCamera();
	}

	public void init() {
		preInit();
		createStates();
		postInit();
	}

	public void postInit() {

	}
	
	private void loadConfigs()
	{
		game.setConfigHandler(new ConfigHandler());
	}

	private void loadDisplay() {
		// Creates the display with width and height.

		Display display = new Display(game.getTitle(), game.getWidth(), game.getHeight());
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

	private void loadAssets() {
		
		//Checks working directory.
		System.out.println("The Jar Directory is: " + Utils.getJarDirectory());
		//Checks and creates these needed folders
		Utils.folderCreate("gen");
		Utils.folderCreate("gen\\config");
		Utils.folderCreate("gen\\worlds");
		Utils.folderCreate("gen\\resources");
		Utils.folderCreate("gen\\resources\\fonts");
		Utils.folderCreate("gen\\resources\\textures");
		Utils.folderCreate("gen\\resources\\textures\\interface");
		Utils.folderCreate("gen\\resources\\textures\\entities");
		Utils.folderCreate("gen\\resources\\textures\\items");
		Utils.folderCreate("gen\\resources\\textures\\tiles");
		//Exports all required resources to correct folders.
		exportResources();
		// Loads all images from the sprite sheets to be used.
		Assets.init();
	}

	private void loadHandler() {
		game.setHandler(new Handler(game));
	}

	private void loadCamera() {
		game.setGameCamera(new GameCamera(game.getHandler(), 0, 0));
	}

	private void createStates() {
		// Create new game state.
		game.setGameState(new GameState(game.getHandler()));
		game.setMenuState(new MenuState(game.getHandler()));
		// Sets the game state.
		State.setState(game.getMenuState());
	}
	
	private void exportResources()
	{
		System.out.println("Exporting resources...");
		try {
			
			Utils.exportResource("/slkscr.ttf", "\\gen\\resources\\fonts");
			Utils.exportResource("/inventoryScreen.png", "\\gen\\resources\\textures\\interface");
			Utils.exportResource("/sheet.png", "\\gen\\resources\\textures\\tiles");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
