package daschnerj.gen.state;

import java.awt.Graphics;

import daschnerj.gen.Handler;
import daschnerj.gen.ui.UIManager;
import daschnerj.gen.utils.Utils;
import daschnerj.gen.worlds.World;

public class GameState extends State {

	private final World world;

	public GameState(final Handler handler) {
		super(handler);
		world = new World(handler, Utils.getDirectory() + "\\Gen\\Worlds\\world1.txt");
		handler.setWorld(world);

	}

	@Override
	public void render(final Graphics g) {
		world.render(g);
	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void setUIManager() {
		// TODO Auto-generated method stub
		
	}


}
