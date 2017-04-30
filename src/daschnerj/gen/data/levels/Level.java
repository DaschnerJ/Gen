package daschnerj.gen.data.levels;

import java.io.File;
import java.util.HashMap;

import daschnerj.gen.data.files.Lines;

public class Level extends Lines{
	
	private int[] d;
	private String[][] tiles;
	private String[][] temperature;
	private String[][] rainfall;
	private HashMap<String, String[]> entityData = new HashMap<String, String[]>();

	public Level(File f) {
		super(f);
		d = getLevelDimensions();
		tiles = new String[d[0]][d[1]];
		loadTiles();
		loadTemperature();
		loadRainfall();
		loadEntityData();
	}
	
	public int[] getLevelPosition()
	{
		return getArray(lines.get(0), ":");
	}
	
	public int[] getLevelDimensions()
	{
		return getArray(lines.get(0), ":");
	}
	
	public boolean hasPlayer()
	{
		return entityData.containsKey("Player");
	}
	
	private boolean loadTiles()
	{
		int diff = 2;
		int end = getLevelDimensions()[0]+2;
		for(int i = diff; i < end; i++)
		{
			tiles[i-2] = lines.get(i).split(" ");
		}
		return true;
	}
	
	private boolean loadTemperature()
	{
		int diff = d[0] + 2;
		int end = d[0] + d[0]+2;
		for(int i = diff; i < end; i++)
		{
			temperature[i - diff] = lines.get(i).split(" ");
		}
		return true;
	}
	
	private boolean loadRainfall()
	{
		int diff = 2*d[0] + 2;
		int end = 2*d[0] + d[0]+2;
		for(int i = diff; i < end; i++)
		{
			rainfall[i - diff] = lines.get(i).split(" ");
		}
		return true;
	}
	
	private boolean loadEntityData()
	{
		int diff = 2*d[0] + d[0]+2;
		int end = lines.size();
		if(diff >= end)
			return true;
		for(int i = diff; i < end; i++)
		{
			String[] s = lines.get(i).split(":");
			entityData.put(s[0], s);
		}
		return true;
	}
	
	private int[] getArray(String l, String del)
	{
		int[] d = new int[l.split(del).length];
		String[] s = l.split(del);
		for(int i = 0; i < s.length; i++)
		{
			d[i] = Integer.valueOf(s[i]);
		}
		return d;
	}
	

}
