package daschnerj.gen.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import daschnerj.gen.utils.Utils;

public class FontLoader {

	public static Font loadFont(String path, String name, float size) {
		return loadFont(path, name).deriveFont(Font.PLAIN, size);
	}

	private static Font loadFont(String path, String name) {
		String fullPath = Utils.getDirectory() + "\\" + path + "\\" + name;
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(fullPath));
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}
		return null;
	}

}
