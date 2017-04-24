package daschnerj.gen.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import daschnerj.gen.loader.Lists;

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

	public Tile(BufferedImage texture, String id) {
		this.texture = texture;
		this.id = id;

		Lists.tiles.put(id, this);
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public boolean isSolid() {
		return false;
	}

	public String getId() {
		return id;
	}

}
