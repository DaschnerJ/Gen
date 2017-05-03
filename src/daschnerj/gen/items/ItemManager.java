package daschnerj.gen.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import daschnerj.gen.Handler;

public class ItemManager {

	private Handler handler;
	private ArrayList<Item> items;

	public ItemManager(final Handler handler) {
		this.handler = handler;
		items = new ArrayList<>();
	}

	public void addIem(final Item i) {
		i.setHandler(handler);
		items.add(i);
	}

	public Handler getHandler() {
		return handler;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void render(final Graphics g) {
		for (final Item i : items) {
			i.render(g);
		}
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void setItems(final ArrayList<Item> items) {
		this.items = items;
	}

	public void tick() {
		final Iterator<Item> it = items.iterator();
		while (it.hasNext()) {
			final Item i = it.next();
			i.tick();
			if (i.isPickedUp()) {
				it.remove();
			}
		}
	}

}
