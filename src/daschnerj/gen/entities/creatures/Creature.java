package daschnerj.gen.entities.creatures;

import daschnerj.gen.Handler;
import daschnerj.gen.entities.Entity;
import daschnerj.gen.tile.Tile;

public abstract class Creature extends Entity {

	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

	protected float speed;
	protected float xMove, yMove;

	public Creature(final Handler handler, final float x, final float y, final int width, final int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	protected boolean collisionWithTile(final int x, final int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	@Override
	public int getHealth() {
		return health;
	}

	public float getSpeed() {
		return speed;
	}

	// GETTERS AND SETTERS
	public float getxMove() {
		return xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0F)) {
			moveX();
		}
		if (!checkEntityCollisions(0F, yMove)) {
			moveY();
		}
	}

	public void moveX() {
		if (xMove > 0)// Move right
		{
			final int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = (tx * Tile.TILEWIDTH) - bounds.x - bounds.y - 1;
			}
		} else if (xMove < 0)// Move left
		{
			final int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = ((tx * Tile.TILEWIDTH) + Tile.TILEWIDTH) - bounds.x;
			}
		}

	}

	public void moveY() {
		if (yMove < 0)// Move up
		{
			final int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ((ty * Tile.TILEHEIGHT) + Tile.TILEHEIGHT) - bounds.y;
			}
		} else if (yMove > 0)// Move down
		{
			final int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = (ty * Tile.TILEHEIGHT) - bounds.y - bounds.height - 1;
			}
		}
	}

	@Override
	public void setHealth(final int health) {
		this.health = health;
	}

	public void setSpeed(final float speed) {
		this.speed = speed;
	}

	public void setxMove(final float xMove) {
		this.xMove = xMove;
	}

	public void setyMove(final float yMove) {
		this.yMove = yMove;
	}

}
