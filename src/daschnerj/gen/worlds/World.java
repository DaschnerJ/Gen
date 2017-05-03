package daschnerj.gen.worlds;

import java.awt.Graphics;

import daschnerj.gen.Handler;
import daschnerj.gen.data.DataObjects;
import daschnerj.gen.entities.EntityManager;
import daschnerj.gen.entities.creatures.Player;
import daschnerj.gen.entities.statics.Tree;
import daschnerj.gen.items.ItemManager;
import daschnerj.gen.tile.Tile;
import daschnerj.gen.utils.Utils;

public class World {

	private Handler handler;
	private int width, height; // Ex 5x5 tile world map
	private int spawnX, spawnY; // Spawn position of player
	// Map
	private String[][] tiles;

	// Entities
	private final EntityManager entityManager;
	// Item
	private ItemManager itemManager;

	public World(final Handler handler, final String path) {
		this.handler = handler;

		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);

		entityManager.addEntity(new Tree(handler, 100, 250));

		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public int getHeight() {
		return height;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public Tile getTile(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= width) || (y >= height))
			return Tile.grassTile;
		final Tile t = DataObjects.tiles.get(tiles[x][y]);
		if (t == null)
			return Tile.dirtTile;
		return t;
	}

	public int getWidth() {
		return width;
	}

	private void loadWorld(final String path) {
		final String file = Utils.loadFileAsString(path);
		final String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new String[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = tokens[(x + (y * width)) + 4];
			}
		}

	}

	public void render(final Graphics g) {
		final int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		final int xEnd = (int) Math.min(width,
				((handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH) + 1);
		final int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		final int yEnd = (int) Math.min(height,
				((handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT) + 1);
		;

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) ((x * Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
						(int) ((y * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset()));
			}
		}
		itemManager.render(g);
		// Entities
		entityManager.render(g);
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void setItemManager(final ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}

}
