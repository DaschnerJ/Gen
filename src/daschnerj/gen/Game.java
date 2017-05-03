package daschnerj.gen;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import daschnerj.gen.data.config.ConfigHandler;
import daschnerj.gen.display.Display;
import daschnerj.gen.gfx.GameCamera;
import daschnerj.gen.input.KeyManager;
import daschnerj.gen.input.MouseManager;
import daschnerj.gen.state.State;

public class Game implements Runnable {

	private ConfigHandler configHandler;

	// The display on the game frame.
	private Display display;

	// The width and height of the frame and canvas.
	private int width, height;

	// The title of the game window.
	public String title;

	// The game loop running variable.
	private boolean running = false;

	// The game thread.
	private Thread thread;

	// The buffer and variables used to draw to the screen.
	// Prevents flickering.
	private BufferStrategy bs;
	private Graphics g;

	// States.
	public State gameState;
	public State menuState;

	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;

	public Game(final String title, final int width, final int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();

	}

	public BufferStrategy getBs() {
		return bs;
	}

	public ConfigHandler getConfigHandler() {
		return configHandler;
	}

	public Display getDisplay() {
		return display;
	}

	public Graphics getG() {
		return g;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public State getGameState() {
		return gameState;
	}

	public Handler getHandler() {
		return handler;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Gets the key manager.
	 *
	 * @return KeyManager
	 */
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public State getMenuState() {
		return menuState;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public Thread getThread() {
		return thread;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	// Initial initialization of the game.
	private void init() {

		final Initialization ini = new Initialization(this);
		ini.init();

	}

	public boolean isRunning() {
		return running;
	}

	// Renders the game, draws to the screen.
	private void render() {
		// Sets the BufferStrategy.
		bs = display.getCanvas().getBufferStrategy();
		// Checks if the BufferStrategy is set.
		if (bs == null) {
			// Creates a BufferStrategy from the display with 3 buffers.
			display.getCanvas().createBufferStrategy(3);
			// Returns to prevent code in the method to be run and prevent
			// errors.
			return;
		}

		// Gets the graphic object to draw with.
		g = bs.getDrawGraphics();

		// Clear Screen of anything drawn.
		g.clearRect(0, 0, width, height);

		// Begin Drawing.

		// Checks the state before drawing otherwise there is no point to draw
		// if there is no state.
		if (State.getState() != null) {
			State.getState().render(g);
			;
		}

		// End Drawing.

		// Show what has been drawn.
		bs.show();

		// Remove what has been drawn.
		g.dispose();

	}

	// The run method of the game thread.
	@Override
	public void run() {
		init();

		// To limit the frames rate of the game to make it even among all
		// computers.
		final int fps = 60;
		final double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		// To see frame rate.
		long timer = 0;
		@SuppressWarnings("unused")
		int ticks = 0;

		while (running) {

			// Check the amount of time passed to determine the next tick.
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			// Check if it time to update
			if (delta >= 1) {
				// Update objects.
				tick();
				// Draw objects.
				render();
				// Decrease delta timer.
				delta--;
				// Increase ticks.
				ticks++;
			}

			if (timer >= 1000000000) {
				// System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public void setBs(final BufferStrategy bs) {
		this.bs = bs;
	}

	public void setConfigHandler(final ConfigHandler configHandler) {
		this.configHandler = configHandler;
	}

	public void setDisplay(final Display display) {
		this.display = display;
	}

	public void setG(final Graphics g) {
		this.g = g;
	}

	public void setGameCamera(final GameCamera gameCamera) {
		this.gameCamera = gameCamera;
	}

	public void setGameState(final State gameState) {
		this.gameState = gameState;
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public void setKeyManager(final KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public void setMenuState(final State menuState) {
		this.menuState = menuState;
	}

	public void setMouseManager(final MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}

	public void setRunning(final boolean running) {
		this.running = running;
	}

	public void setThread(final Thread thread) {
		this.thread = thread;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	// Start the game thread.
	/**
	 * Synchronized prevents two or more invocations of synchronized methods on
	 * the same object to interleave and forces the execution of this thread to
	 * complete before other objects may invoke. Second, when the thread exits,
	 * it makes a before and after relation in the program allowing visibility
	 * to all threads.
	 */
	public synchronized void start() {
		// Make sure the game is not already running, otherwise it exits the
		// method to prevent reinitialization.
		if (running)
			return;
		// Starts the game loop.
		running = true;
		// Create the game thread.
		thread = new Thread(this);
		// Start the game thread.
		thread.start();
	}

	// Stop the game thread.
	public synchronized void stop() {
		// If the game thread isn't running then there isn't anything to stop.
		if (!running)
			return;
		// Stops the game loop.
		running = false;
		try {
			// Stops the game thread.
			thread.join();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Updates the game.
	private void tick() {
		// Ticks and obtains player inputs.
		keyManager.tick();

		// Checks if the state is not null to make sure the game can tick.
		if (State.getState() != null) {
			State.getState().tick();
		}
	}

}
