package daschnerj.gen.data;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import daschnerj.gen.data.config.Config;
import daschnerj.gen.level.Level;

public class DataObjects {
	
	public static HashMap<String, File> bin = new HashMap<String, File>();
	
	//Base Files
	public static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	public static HashMap<String, Config> configs = new HashMap<String, Config>();
	public static HashMap<String, Level> worlds = new HashMap<String, Level>();
	public static HashMap<String, Font> fonts = new HashMap<String, Font>();
	

}
