package daschnerj.gen.data.config;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import daschnerj.gen.utils.Utils;

public class Config {
		
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
	
	/**
	 * Allows programmatically get an attribute from the configuration file.
	 * @param a The attribute desired.
	 * @return The string value of an attribute.
	 */
	public String getAttribute(String a)
	{
		return attributes.get(a);
	}
	
	/**
	 * Allows programmatically set an attribute to the configuration file.
	 * @param a The attribute desired to be added.
	 * @param b The value for the desired attribute.
	 */
	public void addAttribute(String a, String b)
	{
		attributes.put(a, b);
	}
	

}
