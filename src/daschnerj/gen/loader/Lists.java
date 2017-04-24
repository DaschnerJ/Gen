package daschnerj.gen.loader;

import java.util.HashMap;

import daschnerj.gen.entities.Entity;
import daschnerj.gen.entities.creatures.Creature;
import daschnerj.gen.entities.statics.StaticEntity;
import daschnerj.gen.items.Item;
import daschnerj.gen.tile.Tile;

public class Lists {
	
	public static HashMap<String, Tile> tiles = new HashMap<>();
	
	public static HashMap<String, Entity> entities = new HashMap<>();
	
	public static HashMap<String, StaticEntity> staticEntities = new HashMap<>();
	public static HashMap<String, Item> items = new HashMap<>();
	
	public static HashMap<String, Creature> creatures = new HashMap<>();	
	
}
