package daschnerj.gen.ui;

import java.awt.Dimension;

public class UILayout {
	
	private UIManager m;
	
	public UILayout(UIManager m)
	{
		this.m = m;
	}
	
	public void centerVerticalLayout(int spacing)
	{
		m.getObjects().forEach(x -> {
			int index = m.getObjects().indexOf(x);
			int tHeight = getTotalHeight(m, spacing);
			int height = getIndexHeight(m, index, spacing);
			int center = getCenter()[0];
			int adjHeight = center + height - tHeight/2;
			x.setY(adjHeight);
			System.out.println("At index " + index + " tH: " + tHeight + " h: " + height + " c: " + center + " adj: " + adjHeight);
		});
	}
	
	public void  centerHorizontalLayout()
	{
		m.getObjects().forEach(x -> 
		{
			int center = getCenter()[1]-x.width/2;
			x.setX(center);
		});
	}
	
	public int getTotalHeight(UIManager m, int spacing)
	{
		return m.getObjects().stream().mapToInt(o -> o.getHeight()).sum() + (m.getObjects().size()-1)*spacing;
	}
	
	public int getIndexHeight(UIManager m, int index, int spacing)
	{
		return m.getObjects().stream().filter(o -> m.getObjects().indexOf(o) < index).mapToInt(o -> o.getHeight()).sum()
				+ index*spacing;
	}
	
	private int[] getCenter()
	{
		Dimension dim = m.getHandler().getGame().getDisplay().getFrame().getContentPane().getSize();
		return new int[]{dim.width/2, dim.height/2};
	}

}
