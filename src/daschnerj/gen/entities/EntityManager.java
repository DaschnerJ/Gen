package daschnerj.gen.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import daschnerj.gen.Handler;
import daschnerj.gen.entities.creatures.Player;

public class EntityManager {

	private Handler handler;
	private Player player;

	private ArrayList<Entity> entities;
	private final Comparator<Entity> renderSorter = (a, b) -> {
		if ((a.getY() + a.getHeight()) < (b.getY() + b.getHeight()))
			return -1;
		return 1;
	};

	public EntityManager(final Handler handler, final Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<>();
		addEntity(player);
	}

	public void addEntity(final Entity e) {
		entities.add(e);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public Handler getHandler() {
		return handler;
	}

	// GETTERS AND SETTERS

	public Player getPlayer() {
		return player;
	}

	public void render(final Graphics g) {
		// You may use this type of for loop here because it has no mechanical
		// importance.
		for (final Entity e : entities) {
			e.render(g);
		}
		player.postRender(g);
	}

	public void setEntities(final ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public void tick() {
		final Iterator<Entity> it = entities.iterator();
		// This has to be the normal for loop otherwise collision gets messed
		// up.
		while (it.hasNext()) {
			final Entity e = it.next();
			e.tick();
			if (!e.isActive()) {
				it.remove();
			}
		}
		entities.sort(renderSorter);
	}

}
