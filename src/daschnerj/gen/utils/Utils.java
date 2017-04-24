package daschnerj.gen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import daschnerj.gen.Initialization;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(Utils.class.getClassLoader().getResourceAsStream(path), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String getJarDirectory() {
		try {
			return new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
					.getAbsolutePath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDirectory()
	{
		File f = new File(System.getProperty("java.class.path"));
    	File dir2 = f.getAbsoluteFile().getParentFile();
    	String path2 = dir2.toString();
    	return path2.split(";")[1];
	}

	public static void folderCreate(String path) {
		File theDir = new File(path);
		System.out.println("Checking for " + theDir + "...");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("Creating Directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("Directory created.");
			}
		}
	}
	
	/**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    static public String ExportResource(String resourceName, String path) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = Utils.class.getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if(stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder+ path +"\\" +  resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }
    
    public static void exportResource(String resourceName, String path)
    {
    	File f = new File(System.getProperty("java.class.path"));
    	File dir2 = f.getAbsoluteFile().getParentFile();
    	String path2 = dir2.toString();
    	URL url = Initialization.class.getResource(resourceName);
		try {
			File dir = new File(url.toURI());
			String des = path2.split(";")[1]+path +"\\"+ dir.getName();
			File outDes = new File(des);
			Files.copy(dir.toPath(),
			        outDes.toPath(),
			        StandardCopyOption.REPLACE_EXISTING);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Loads a file in a List line by line.
     * @param path Path to the file from the jar folder.
     * @param name The name of the file.
     * @param encoding The encoding of a file.
     * @return Returns the file.
     */
    public static List<String> readFile(String path, String name, Charset encoding)
    {
    	String fullPath = getDirectory() + "\\" + path + "\\" + name;
    	List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fullPath), encoding);
		} catch (IOException e) {
			System.out.println("Failed to load file.");
			e.printStackTrace();
		}
    	return lines;
    }
    
    public static List<String> readFile(String path, Charset encoding)
    {
    	List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(path), encoding);
		} catch (IOException e) {
			System.out.println("Failed to load file.");
			e.printStackTrace();
		}
    	return lines;
    }
    
    public static List<String> readFile(File file)
    {
    	String fullPath = file.getAbsolutePath();
    	List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fullPath), Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println("Failed to load file.");
			e.printStackTrace();
		}
    	return lines;
    }
    
   

}
