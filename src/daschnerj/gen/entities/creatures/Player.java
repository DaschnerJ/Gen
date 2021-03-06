package daschnerj.gen.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import daschnerj.gen.Handler;
import daschnerj.gen.entities.Entity;
import daschnerj.gen.gfx.Assets;
import daschnerj.gen.gfx.animation.Animation;
import daschnerj.gen.inventory.Inventory;

public class Player extends Creature {

	// Animations
	private final Animation animDown, animUp, animLeft, animRight;

	// Attack Timer
	private long lastAttackTimer;

	private final long attackCooldown = 800;

	private long attackTimer = attackCooldown;
	// Inventory
	private Inventory inventory;

	public Player(final Handler handler, final float x, final float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;

		// Animations
		animDown = new Animation(350, Assets.player_down);
		animUp = new Animation(350, Assets.player_up);
		animLeft = new Animation(350, Assets.player_left);
		animRight = new Animation(350, Assets.player_right);

		inventory = new Inventory(handler);
	}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		if (inventory.isActive())
			return;

		final Rectangle cb = getCollisionBounds(0, 0);
		final Rectangle ar = new Rectangle();
		final int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().aUp) {
			ar.x = (cb.x + (cb.width / 2)) - (arSize / 2);
			ar.y = cb.y - arSize;
		} else if (handler.getKeyManager().aDown) {
			ar.x = (cb.x + (cb.width / 2)) - (arSize / 2);
			ar.y = cb.y + cb.height;
		} else if (handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = (cb.y + (cb.height / 2)) - (arSize / 2);
		} else if (handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = (cb.y + (cb.height / 2)) - (arSize / 2);
		} else
			return;

		attackTimer = 0;

		for (final Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}

	}

	@Override
	public void die() {
		System.out.println("You lose!");

	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0)
			return animLeft.getCurrentFrame();
		else if (xMove > 0)
			return animRight.getCurrentFrame();
		else if (yMove < 0)
			return animUp.getCurrentFrame();
		else
			return animDown.getCurrentFrame();
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (inventory.isActive())
			return;

		if (handler.getKeyManager().up) {
			yMove = -speed;
		}
		if (handler.getKeyManager().down) {
			yMove = speed;
		}
		if (handler.getKeyManager().left) {
			xMove = -speed;
		}
		if (handler.getKeyManager().right) {
			xMove = speed;
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void postRender(final Graphics g) {
		inventory.render(g);
	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		// g.setColor(Color.red);
		// g.fillRect((int) (x + bounds.x -
		// handler.getGameCamera().getxOffset()),(int) (y + bounds.y -
		// handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	public void setInventory(final Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void tick() {
		// Animations
		animDown.tick();
		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

		// Attack
		checkAttacks();

		inventory.tick();
	}

}
