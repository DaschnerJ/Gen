package daschnerj.gen.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import daschnerj.gen.ui.UIManager;

public class KeyManager implements KeyListener {

	// Array of keys on keyboard whether they are receiving input or not.
	private final boolean[] keys, justPressed, cantPress;

	public boolean up, down, left, right;

	public boolean aUp, aDown, aLeft, aRight;
	
	private UIManager uiManager;

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}

	public boolean keyJustPressed(final int keyCode) {
		if ((keyCode < 0) || (keyCode >= keys.length))
			return false;
		return justPressed[keyCode];
	}

	@Override
	public void keyPressed(final KeyEvent e) {

		if ((e.getKeyCode() < 0) || (e.getKeyCode() >= keys.length))
			return;
		// If the key was pressed then it turns to true.
		keys[e.getKeyCode()] = true;
		//System.out.println("Pressed!");
	}

	@Override
	public void keyReleased(final KeyEvent e) {

		if ((e.getKeyCode() < 0) || (e.getKeyCode() >= keys.length))
			return;
		// If the key was released then it turns to false.
		keys[e.getKeyCode()] = false;
		
		if(uiManager != null)
			uiManager.onKeyRelease(e);
	}
	
	public void setUIManager(UIManager uiManager)
	{
		this.uiManager = uiManager;
	}

	@Override
	public void keyTyped(final KeyEvent e) {

	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			} else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
	}

}
