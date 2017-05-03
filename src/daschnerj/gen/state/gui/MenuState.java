package daschnerj.gen.state.gui;

import java.awt.Graphics;

import daschnerj.gen.Handler;
import daschnerj.gen.gfx.Assets;
import daschnerj.gen.state.State;
import daschnerj.gen.ui.UIImageButton;
import daschnerj.gen.ui.UILayout;
import daschnerj.gen.ui.UIManager;

public class MenuState extends State {

	private final UIManager uiManager;

	public MenuState(final Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().gameState);
		}));

		uiManager.addObject(new UIImageButton(200, 50, 128, 64, Assets.btn_start, () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().gameState);
		}));

		uiManager.addObject(new UIImageButton(200, 350, 128, 64, Assets.btn_start, () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().gameState);
		}));

		uiManager.addObject(new UIImageButton(200, 600, 128, 64, Assets.btn_start, () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().gameState);
		}));
		
		UILayout uL = new UILayout(uiManager);
		uL.centerHorizontalLayout();
		uL.centerVerticalLayout(10);
	}

	@Override
	public void render(final Graphics g) {

		uiManager.render(g);

		// g.setColor(Color.red);
		// g.fillRect(handler.getMouseManager().getMouseX(),
		// handler.getMouseManager().getMouseY(), 8, 8);

	}

	@Override
	public void tick() {
		uiManager.tick();
	}

}
