package daschnerj.gen.gfx;

import daschnerj.gen.Handler;
import daschnerj.gen.entities.Entity;
import daschnerj.gen.tile.Tile;

public class GameCamera {

	private final Handler handler;
	private float xOffset, yOffset;

	public GameCamera(final Handler handler, final float xOffset, final float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void centerOnEntity(final Entity e) {
		xOffset = (e.getX() - (handler.getWidth() / 2)) + (e.getWidth() / 2);
		yOffset = (e.getY() - (handler.getHeight() / 2)) + (e.getHeight() / 2);
		checkBlankSpace();
	}

	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > ((handler.getWorld().getWidth() * Tile.TILEWIDTH) - handler.getWidth())) {
			xOffset = (handler.getWorld().getWidth() * Tile.TILEWIDTH) - handler.getWidth();
		}

		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > ((handler.getWorld().getHeight() * Tile.TILEHEIGHT) - handler.getHeight())) {
			yOffset = (handler.getWorld().getHeight() * Tile.TILEHEIGHT) - handler.getHeight();
		}
	}

	public float getxOffset() {
		return xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void move(final float xAmt, final float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public void setxOffset(final float xOffset) {
		this.xOffset = xOffset;
	}

	public void setyOffset(final float yOffset) {
		this.yOffset = yOffset;
	}

}
