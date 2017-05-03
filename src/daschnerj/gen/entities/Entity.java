package daschnerj.gen.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import daschnerj.gen.Handler;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 3;
	protected int health;

	protected Handler handler;

	// Floats for smooth transition.
	protected float x, y;

	// Used for resizing the entity to correct size.
	protected int width, height;

	protected Rectangle bounds;

	protected boolean active = true;

	// Construct the entity at the location.
	public Entity(final Handler handler, final float x, final float y, final int width, final int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;

		bounds = new Rectangle(0, 0, width, height);
	}

	public boolean checkEntityCollisions(final float xOffset, final float yOffset) {
		for (final Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public abstract void die();

	public Rectangle getCollisionBounds(final float xOffset, final float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public int getHealth() {
		return health;
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

	public void hurt(final int amt) {
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}

	public boolean isActive() {
		return active;
	}

	public abstract void render(Graphics g);

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public void setX(final float x) {
		this.x = x;
	}

	public void setY(final float y) {
		this.y = y;
	}

	public abstract void tick();

}
