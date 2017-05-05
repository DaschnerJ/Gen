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
import daschnerj.gen.ui.UIText;
import daschnerj.gen.ui.UITextField;
import daschnerj.gen.utils.Utils;

public class CreateState extends State {

	private BufferedImage img;
	private final int centerX, centerY;
	private final UIManager uiManager;

	private final BufferedImage createButton;

	public CreateState(final Handler handler) {
		super(handler);

		img = DataObjects.textures.get("MainMenu").getImage();
		img = Utils.resizeImage(img, handler.getWidth(), handler.getHeight());

		createButton = DataObjects.textures.get("button_create-new-world").getImage();

		final Dimension dim = handler.getGame().getDisplay().getCanvas().getSize();
		centerX = ((int) dim.getWidth() - img.getWidth()) / 2;
		centerY = ((int) dim.getHeight() - img.getHeight()) / 2;

		uiManager = new UIManager(handler);

		uiManager.addObject(new UIImageButton(handler.getWidth() - createButton.getWidth() - 10,
				handler.getHeight() - createButton.getHeight() - 10, 256, 46, createButton, () -> {
					handler.getMouseManager().setUIManager(null);
					State.setState(handler.getGame().gameState);
				}));

		uiManager.addObject(
				new UIText(20, 40, 100, 100, "World Name: ", DataObjects.fonts.get("slkscr"), 24, Color.WHITE));

		final UITextField name = new UITextField(200, 40, 100, 100, "", DataObjects.fonts.get("slkscr"), 24, uiManager,
				Color.WHITE, e -> 
		{
		System.out.println("Key pressed was: " + e.getKeyCode());
		});
		name.setActive(true);

		uiManager.addObject(name);

	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

		g.drawImage(img, centerX, centerY, null);
		uiManager.render(g);

	}

	@Override
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
		handler.getKeyManager().setUIManager(uiManager);

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
