package daschnerj.gen.state;

import java.awt.Graphics;

import daschnerj.gen.Handler;
import daschnerj.gen.ui.UIManager;

public abstract class State {

	// Current state.
	private static State currentState = null;

	// Gets the current state.
	public static State getState() {
		return currentState;
	}

	// Changes the state.
	public static void setState(final State state) {
		currentState = state;
	}

	// CLASS

	protected Handler handler;

	public State(final Handler handler) {
		this.handler = handler;
	}
	
	public Handler getHandler()
	{
		return handler;
	}
	
	public abstract void setUIManager();

	public abstract void render(Graphics g);

	public abstract void tick();

}
