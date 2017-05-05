package daschnerj.gen.state.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import daschnerj.gen.Handler;
import daschnerj.gen.data.DataObjects;
import daschnerj.gen.state.State;
import daschnerj.gen.ui.UIImageButton;
import daschnerj.gen.ui.UIManager;
import daschnerj.gen.utils.Utils;

public class CreateState extends State{
	
	private BufferedImage img;
	private int centerX, centerY;
	private final UIManager uiManager;
	
	private BufferedImage createButton;

	public CreateState(Handler handler) {
		super(handler);
		
		img = DataObjects.textures.get("MainMenu").getImage();
		img = Utils.resizeImage(img, handler.getWidth(), handler.getHeight());
		
		createButton = DataObjects.textures.get("button_create-new-world").getImage();
		
		Dimension dim = handler.getGame().getDisplay().getCanvas().getSize();
		centerX = ((int)dim.getWidth() - img.getWidth())/2;
		centerY = ((int)dim.getHeight() - img.getHeight())/2;
		
		uiManager = new UIManager(handler);
		
		uiManager.addObject(new UIImageButton(handler.getWidth()-createButton.getWidth()-10, handler.getHeight()-createButton.getHeight()-10, 256, 46, createButton, () -> {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().gameState);
		}));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		g.drawImage(img, centerX, centerY, null);
		uiManager.render(g);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
		
	}

}
