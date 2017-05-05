package daschnerj.gen.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	protected boolean active = false;

	public UIObject(final float x, final float y, final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, width, height);

	}
	
	public void setBounds(float x, float y, int width, int height)
	{
		bounds = new Rectangle((int) x, (int) y, width, height);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isHovering() {
		return hovering;
	}
	
	public boolean isActive()
	{
		return active;
	}

	// GETTERS AND SETTERS

	public abstract void onClick();
	
	public abstract void onType(KeyEvent e);

	public void onMouseMove(final MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			hovering = false;
		}
	}
	
	public void onKeyPress(final KeyEvent e)
	{
		if(active)
			onType(e);
	}
	
	public void setActive(boolean b)
	{
		active = b;
	}

	public void onMouseRelease(final MouseEvent e) {
		if (hovering) {
			onClick();
		}
	}

	public abstract void render(Graphics g);

	public void setHeight(final int height) {
		this.height = height;
		setBounds(x, y, this.width, this.height);
	}

	public void setHovering(final boolean hovering) {
		this.hovering = hovering;
	}

	public void setWidth(final int width) {
		this.width = width;
		setBounds(x, y, this.width, this.height);
	}

	public void setX(final float x) {
		this.x = x;
		setBounds(x, y, this.width, this.height);
	}

	public void setY(final float y) {
		this.y = y;
		setBounds(x, y, this.width, this.height);
	}

	public abstract void tick();

}
