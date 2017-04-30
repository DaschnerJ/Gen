package daschnerj.gen.data.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class GameFont {
	
	private String name;
	private File file;
	
	public GameFont(String name, File file)
	{
		this.file = file;
		this.name = name;
	}
	
	public GameFont(File file)
	{
		this.file = file;
		this.name = file.getName();
	}
	
	public Font loadFont(float size) {
		return loadFont().deriveFont(Font.PLAIN, size);
	}
	
	private Font loadFont() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, file);
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}
		return null;
	}
	
	public String getName()
	{
		return name;
	}

}
