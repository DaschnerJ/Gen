package daschnerj.gen.state;

import java.awt.Graphics;

import daschnerj.gen.Handler;

public abstract class State {

	// Current state.
	private static State currentState = null;

	// Changes the state.
	public static void setState(State state) {
		currentState = state;
	}

	// Gets the current state.
	public static State getState() {
		return currentState;
	}

	// CLASS

	protected Handler handler;

	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
