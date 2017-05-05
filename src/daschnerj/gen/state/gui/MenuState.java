package daschnerj.gen.state.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import daschnerj.gen.Handler;
import daschnerj.gen.data.DataObjects;
import daschnerj.gen.state.State;
import daschnerj.gen.ui.UIImageButton;
import daschnerj.gen.ui.UILayout;
import daschnerj.gen.ui.UIManager;
import daschnerj.gen.utils.Utils;

public class MenuState extends State {

	private BufferedImage img;
	private int centerX, centerY;
	private final UIManager uiManager;

	public MenuState(final Handler handler) {
		super(handler);
		
		img = DataObjects.textures.get("MainMenu").getImage();
		img = Utils.resizeImage(img, handler.getWidth(), handler.getHeight());
		
		Dimension dim = handler.getGame().getDisplay().getCanvas().getSize();
		centerX = ((int)dim.getWidth() - img.getWidth())/2;
		centerY = ((int)dim.getHeight() - img.getHeight())/2;
		
		uiManager = new UIManager(handler);
		setUIManager();
		
				
		uiManager.addObject(new UIImageButton(200, 50, 256, 46, DataObjects.textures.get("button_create-new-world").getImage(), () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().createState);
			handler.getGame().createState.setUIManager();
		}));

		uiManager.addObject(new UIImageButton(200, 50, 256, 46, DataObjects.textures.get("button_load").getImage(), () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().loadState);
			handler.getGame().loadState.setUIManager();
		}));
		
		uiManager.addObject(new UIImageButton(200, 350, 256, 46, DataObjects.textures.get("button_options").getImage(), () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().optionsState);
			handler.getGame().optionsState.setUIManager();
		}));

		uiManager.addObject(new UIImageButton(200, 350, 256, 46, DataObjects.textures.get("button_credits").getImage(), () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().aboutState);
			handler.getGame().aboutState.setUIManager();
		}));

		uiManager.addObject(new UIImageButton(200, 600, 256, 46, DataObjects.textures.get("button_exit").getImage(), () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().gameState);
		}));
		
		UILayout uL = new UILayout(uiManager);
		uL.centerHorizontalLayout();
		uL.centerVerticalLayout(10);
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		g.drawImage(img, centerX, centerY, null);
		uiManager.render(g);

		// g.setColor(Color.red);
		// g.fillRect(handler.getMouseManager().getMouseX(),
		// handler.getMouseManager().getMouseY(), 8, 8);

	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
		
	}

}
