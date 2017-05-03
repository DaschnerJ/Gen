package daschnerj.gen.data.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class GameFont {

	private final String name;
	private final File file;

	public GameFont(final File file) {
		this.file = file;
		this.name = file.getName();
	}

	public GameFont(final String name, final File file) {
		this.file = file;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private Font loadFont() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, file);
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}
		return null;
	}

	public Font loadFont(final float size) {
		return loadFont().deriveFont(Font.PLAIN, size);
	}

}
