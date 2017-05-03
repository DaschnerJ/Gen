package daschnerj.gen.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import daschnerj.gen.Handler;
import daschnerj.gen.data.DataObjects;
import daschnerj.gen.gfx.Assets;

public class Item {

	// HANDLER

	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.wood, "Wood", "0");
	public static Item rockItem = new Item(Assets.rock, "Rock", "1");

	// CLASS

	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final String id;

	protected Rectangle bounds;

	protected int x, y, count;
	protected boolean pickedUp = false;

	public Item(final BufferedImage texture, final String name, final String id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;

		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

		DataObjects.items.put(id, this);
	}

	public Item createNew(final int count) {
		final Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}

	public Item createNew(final int x, final int y) {
		final Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}

	public int getCount() {
		return count;
	}

	public Handler getHandler() {
		return handler;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void render(final Graphics g) {
		if (handler == null)
			return;
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}

	public void render(final Graphics g, final int x, final int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPickedUp(final boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public void setPosition(final int x, final int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}

	public void setTexture(final BufferedImage texture) {
		this.texture = texture;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public void tick() {
		if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}

}
