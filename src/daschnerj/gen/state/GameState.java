package daschnerj.gen.state;

import java.awt.Graphics;

import daschnerj.gen.Handler;
import daschnerj.gen.utils.Utils;
import daschnerj.gen.worlds.World;

public class GameState extends State {

	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, Utils.getDirectory() + "\\Worlds\\world1.txt");
		handler.setWorld(world);

	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
