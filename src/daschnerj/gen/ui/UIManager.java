package daschnerj.gen.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import daschnerj.gen.Handler;

public class UIManager {

	private Handler handler;
	private ArrayList<UIObject> objects;

	public UIManager(final Handler handler) {
		this.handler = handler;
		objects = new ArrayList<>();
	}

	public void addObject(final UIObject o) {
		objects.add(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void onMouseMove(final MouseEvent e) {
		for (final UIObject o : objects) {
			o.onMouseMove(e);
		}
	}

	public void onMouseRelease(final MouseEvent e) {
		for (final UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}
	
	public void onKeyRelease(final KeyEvent e)
	{
		for(final UIObject o : objects)
		{
			if(o.isActive())
			{
				o.onKeyPress(e);
				o.render(handler.getGame().getG());
			}
		}
	}

	public void removeOBject(final UIObject o) {
		objects.remove(o);
	}

	public void render(final Graphics g) {
		for (final UIObject o : objects) {
			o.render(g);
		}
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}

	public void setObjects(final ArrayList<UIObject> objects) {
		this.objects = objects;
	}

	public void tick() {
		for (final UIObject o : objects) {
			o.tick();
		}
	}

}
