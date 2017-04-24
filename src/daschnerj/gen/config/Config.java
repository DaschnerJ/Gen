package daschnerj.gen.config;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import daschnerj.gen.utils.Utils;

public class Config {
	
	public static HashMap<String, Config> configs = new HashMap<>();
	
	private HashMap<String, String> attributes = new HashMap<>();
	
	public Config(File file)
	{
		List<String> lines = Utils.readFile(file);
		for(String s : lines)
		{
			String[] a = s.split(":");
			attributes.put(a[0], a[1]);
		}
		
	}
	
	public Config(String path, String name)
	{
		
		List<String> lines = Utils.readFile(path + "\\" +name, Charset.forName("UTF-8"));
		for(String s : lines)
		{
			String[] a = s.split(":");
			attributes.put(a[0], a[1]);
		}
	}
	
	public Config(String path)
	{
		
		List<String> lines = Utils.readFile(path, Charset.forName("UTF-8"));
		for(String s : lines)
		{
			String[] a = s.split(":");
			attributes.put(a[0], a[1]);
		}
	}
	
	public String getAttribute(String a)
	{
		return attributes.get(a);
	}
	

}
