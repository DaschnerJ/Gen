package daschnerj.gen.data;

import java.io.File;
import java.util.HashMap;

import daschnerj.gen.data.config.Config;
import daschnerj.gen.data.files.Lines;
import daschnerj.gen.data.fonts.GameFont;
import daschnerj.gen.data.levels.Level;
import daschnerj.gen.data.sounds.GameAudio;
import daschnerj.gen.data.textures.Texture;
import daschnerj.gen.entities.Entity;
import daschnerj.gen.entities.creatures.Creature;
import daschnerj.gen.entities.statics.StaticEntity;
import daschnerj.gen.items.Item;
import daschnerj.gen.tile.Tile;

public class DataObjects {
	
	public static HashMap<String, File> bin = new HashMap<String, File>();
	
	//Base Files
	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	public static HashMap<String, Config> configs = new HashMap<String, Config>();
	public static HashMap<String, Level> worlds = new HashMap<String, Level>();
	public static HashMap<String, Lines> files = new HashMap<String, Lines>();
	public static HashMap<String, GameAudio> sounds = new HashMap<String, GameAudio>();
	public static HashMap<String, GameFont> fonts = new HashMap<String, GameFont>();
	
	
	//Lists of Created Instances Game Objects
	public static HashMap<String, Tile> tiles = new HashMap<>();
	
	public static HashMap<String, Entity> entities = new HashMap<>();
	
	public static HashMap<String, StaticEntity> staticEntities = new HashMap<>();
	public static HashMap<String, Item> items = new HashMap<>();
	
	public static HashMap<String, Creature> creatures = new HashMap<>();
	

}
