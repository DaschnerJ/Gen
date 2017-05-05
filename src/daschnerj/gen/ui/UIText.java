package daschnerj.gen.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import daschnerj.gen.data.fonts.GameFont;

public class UIText extends UIObject{
	
	String label;
	GameFont font;
	Integer size;
	Color c = null;

	public UIText(float x, float y, int width, int height, String label, GameFont font, Integer size) {
		super(x, y, width, height);
		this.label = label;
		this.font = font;
		this.size = size;
		// TODO Auto-generated constructor stub
	}
	
	public UIText(float x, float y, int width, int height, String label, GameFont font, Integer size, Color c) {
		super(x, y, width, height);
		this.label = label;
		this.font = font;
		this.size = size;
		this.c = c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(c != null)
			g.setColor(c);
		g.setFont(font.loadFont(size));
		g.drawString(label, (int)x, (int)y);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onType(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
