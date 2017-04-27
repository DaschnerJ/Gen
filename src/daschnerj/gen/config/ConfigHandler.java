package daschnerj.gen.config;

import java.io.File;
import java.util.ArrayList;

import daschnerj.gen.utils.Utils;

public class ConfigHandler {
	
	private static ArrayList<Config> configList = new ArrayList<>();
	
	public ConfigHandler()
	{
		//System.out.println("Finding all config files in director "+Utils.getDirectory()+"\\gen"+"...");
		refreshConfig();
		System.out.println(configList.size()+" files were found.");
		
	}
	
	private void findFile(String pathname) {
	    File f = new File(pathname);
	    File[] listfiles = f.listFiles();
	    if(listfiles != null)
	    for (int i = 0; i < listfiles.length; i++) {
	        if (listfiles[i].isDirectory()) {
	            File[] internalFile = listfiles[i].listFiles();
	            for (int j = 0; j < internalFile.length; j++) {
	                
	                if(hasExtension(internalFile[j], ".gcf"))
	                {
	                	//System.out.println(internalFile[j]);
	                	configList.add(new Config(internalFile[j]));
	                }
	                if (internalFile[j].isDirectory()) {
	                    String name = internalFile[j].getAbsolutePath();
	                    findFile(name);
	                }

	            }
	        } else {
	        	if(hasExtension(listfiles[i], ".gcf"))
                {
	        		//System.out.println(listfiles[i]);
                	configList.add(new Config(listfiles[i]));
                }
	        }

	    }

	}
	
	private boolean hasExtension(File file, String extension)
	{
		return file.getAbsolutePath().endsWith(extension);
	}
	
	public void refreshConfig()
	{
		configList.clear();
		findFile(Utils.getDirectory()+"\\gen");
	}
	
	public ArrayList<Config> findConfigsWithAttribute(String type, String attribute)
	{
		ArrayList<Config> typesMatch = new ArrayList<Config>();
		for(Config c : configList)
		{
			if(c.getAttribute(type).equals(attribute))
				typesMatch.add(c);
		}
		
		return typesMatch;
	}
	
}
