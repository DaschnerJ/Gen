package daschnerj.gen.data;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import daschnerj.gen.data.config.Config;
import daschnerj.gen.entities.Entity;
import daschnerj.gen.entities.creatures.Creature;
import daschnerj.gen.entities.statics.StaticEntity;
import daschnerj.gen.items.Item;
import daschnerj.gen.level.Level;
import daschnerj.gen.tile.Tile;

public class DataObjects {
	
	public static HashMap<String, File> bin = new HashMap<String, File>();
	
	//Base Files
	public static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	public static HashMap<String, Config> configs = new HashMap<String, Config>();
	public static HashMap<String, Level> worlds = new HashMap<String, Level>();
	public static HashMap<String, Font> fonts = new HashMap<String, Font>();
	
	
	//Lists of Game Objects
	public static HashMap<String, Tile> tiles = new HashMap<>();
	
	public static HashMap<String, Entity> entities = new HashMap<>();
	
	public static HashMap<String, StaticEntity> staticEntities = new HashMap<>();
	public static HashMap<String, Item> items = new HashMap<>();
	
	public static HashMap<String, Creature> creatures = new HashMap<>();
	

}
