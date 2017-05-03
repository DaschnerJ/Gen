package daschnerj.gen.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import daschnerj.gen.Handler;
import daschnerj.gen.gfx.Assets;
import daschnerj.gen.gfx.Text;
import daschnerj.gen.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private final ArrayList<Item> inventoryItems;

	private final int invX = 64, invY = 48, invWidth = 512, invHeight = 384, invListCenterX = invX + 171,
			invListCenterY = invY + (invHeight / 2) + 5, invListSpacing = 30;

	private final int invImageX = 452, invImageY = 82, invImageWidth = 64, invImageHeight = 64;

	private final int invCountX = 484, invCountY = 172;

	private int selectedItem = 0;

	public Inventory(final Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<>();
	}

	public void addItem(final Item item) {
		for (final Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public Handler getHandler() {
		return handler;
	}

	// Inventory methods

	public boolean isActive() {
		return active;
	}

	// GETTERS SETTERS

	public void render(final Graphics g) {
		if (!active)
			return;

		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

		final int len = inventoryItems.size();
		if (len == 0)
			return;

		for (int i = -5; i < 6; i++) {
			if (((selectedItem + i) < 0) || ((selectedItem + i) >= len)) {
				continue;
			}
			if (i == 0) {
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
						invListCenterY + (i * invListSpacing), true, Color.YELLOW, Assets.font28);
			} else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
						invListCenterY + (i * invListSpacing), true, Color.WHITE, Assets.font28);
			}
		}

		final Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		if (!active)
			return;

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			selectedItem--;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
			selectedItem++;
		}

		if (selectedItem < 0) {
			selectedItem = inventoryItems.size() - 1;
		} else if (selectedItem >= inventoryItems.size()) {
			selectedItem = 0;
		}
	}

}
