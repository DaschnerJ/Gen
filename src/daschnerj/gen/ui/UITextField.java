package daschnerj.gen.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import daschnerj.gen.Handler;
import daschnerj.gen.data.fonts.GameFont;

public class UITextField extends UIObject{
	
	String text;
	GameFont font;
	Integer size;
	private final TypeListener typer;
	UIManager uiManager;
	Color c = null;

	public UITextField(float x, float y, int width, int height, String text, GameFont font, Integer size, UIManager uiManager, TypeListener typer) {
		super(x, y, width, height);
		this.typer = typer;
		this.font = font;
		this.size = size;
		this.uiManager = uiManager;
		this.text = text;
		// TODO Auto-generated constructor stub
	}
	
	public UITextField(float x, float y, int width, int height, String text, GameFont font, Integer size, UIManager uiManager, Color c, TypeListener typer) {
		super(x, y, width, height);
		this.typer = typer;
		this.font = font;
		this.size = size;
		this.uiManager = uiManager;
		this.text = text;
		this.c = c;
		// TODO Auto-generated constructor stub
	}
	
	public void setKeyListening(Handler handler, UIManager ui)
	{
		handler.getMouseManager().setUIManager(null);
		handler.getKeyManager().setUIManager(uiManager);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}
	
	public void onType(KeyEvent e)
	{
		if(active)
		{
			typer.onType(e);
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE)
			{
				this.deleteChar();
			}
			else if(key == KeyEvent.VK_UP)
			{
				this.scrollUp();
			}
			else if(key == KeyEvent.VK_DOWN)
			{
				this.scrollDown();
			}
			else if(key == KeyEvent.SHIFT_DOWN_MASK || key == KeyEvent.VK_SHIFT || key == KeyEvent.SHIFT_MASK)
			{
				return;
			}
			else 
			{
				this.addChar(e.getKeyChar());
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(c != null)
			g.setColor(c);
		g.setFont(font.loadFont(size));
		if(active)
			g.drawString(text + " <-", (int) x, (int) y);
		else
			g.drawString(text, (int) x, (int) y);
		
	}

	@Override
	public void tick() {
		
	}
	
	private void deleteChar()
	{
		//System.out.println(text.length());
		if(text.length() == 0 || text.equals(""))
			return;
		if(text.length() > 1);
			text = text.substring(0, text.length()-1);
	}
	
	private void addChar(char c)
	{
		text = text + c;
	}
	
	private ArrayList<UIObject> getTextFields()
	{
		ArrayList<UIObject> fields = new ArrayList<>();
		uiManager.getObjects().stream().filter(o -> o.getClass().equals(this.getClass())).forEach(o -> fields.add(o));
		return fields;
	}
	
	private int getFieldIndex()
	{
		ArrayList<UIObject> fields = getTextFields();
		if(fields.isEmpty())
		{
			return -1;
		}
		else
		{
			return fields.indexOf(fields.stream().filter(o -> o.equals(this)).findFirst().get());
		}
	}
	
	private void scrollDown()
	{
		int index = getFieldIndex();
		ArrayList<UIObject> fields = getTextFields();
		if(index > 0)
		{
			this.setActive(false);
			fields.get(index-1).setActive(true);
		}
		else if(index == 0)
		{
			this.setActive(true);
		}
		else
		{
			return;
		}
	}
	
	private void scrollUp()
	{
		int index = getFieldIndex();
		ArrayList<UIObject> fields = getTextFields();
		if(index < fields.size()-1)
		{
			this.setActive(false);
			fields.get(index+1).setActive(true);
		}
		else if(index == fields.size()-1)
		{
			this.setActive(true);
		}
		else
		{
			return;
		}
	}

}
