package daschnerj.gen.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import daschnerj.gen.data.DataObjects;

public class Tile {

	// STATIC STUFF HERE

	// Map
	// Tile IDs
	public static Tile grassTile = new GrassTile("0");
	public static Tile dirtTile = new DirtTile("1");
	public static Tile rockTile = new RockTile("2");

	// CLASS
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	protected BufferedImage texture;
	protected final String id;

	public Tile(final BufferedImage texture, final String id) {
		this.texture = texture;
		this.id = id;

		DataObjects.tiles.put(id, this);
	}

	public String getId() {
		return id;
	}

	public boolean isSolid() {
		return false;
	}

	public void render(final Graphics g, final int x, final int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public void tick() {

	}

}
