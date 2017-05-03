package daschnerj.gen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {

	public static void folderCreate(final String path) {
		final File theDir = new File(path);
		System.out.println("Checking for " + theDir + "...");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("Creating Directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (final SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("Directory created.");
			}
		}
	}

	public static String getDirectory() {
		final File f = new File(System.getProperty("java.class.path"));
		final File dir2 = f.getAbsoluteFile().getParentFile();
		final String path2 = dir2.toString();
		final String[] split = path2.split(";");
		if (split.length > 1)
			return split[1];
		else
			return split[0];
	}

	public static String getJarDirectory() {
		return new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
	}

	public static String loadFileAsString(final String path) {
		final StringBuilder builder = new StringBuilder();

		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static int parseInt(final String number) {
		try {
			return Integer.parseInt(number);
		} catch (final NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * /** Export a resource embedded into a Jar file to the local file path.
	 *
	 * @param resourceName ie.: "/SmartLibrary.dll"
	 *
	 * @return The path to the exported resource
	 *
	 * @throws Exception
	 *
	 * static public String ExportResource(String resourceName, String path)
	 * throws Exception { InputStream stream = null; OutputStream resStreamOut =
	 * null; String jarFolder; try { stream =
	 * Utils.class.getResourceAsStream(resourceName);//note that each / is a
	 * directory down in the "jar tree" been the jar the root of the tree
	 * if(stream == null) { throw new Exception("Cannot get resource \"" +
	 * resourceName + "\" from Jar file."); }
	 *
	 * int readBytes; byte[] buffer = new byte[4096]; jarFolder = new
	 * File(Utils.class.getProtectionDomain().getCodeSource().getLocation().
	 * toURI().getPath()).getParentFile().getPath().replace('\\', '/');
	 * resStreamOut = new FileOutputStream(jarFolder+ path +"\\" +
	 * resourceName); while ((readBytes = stream.read(buffer)) > 0) {
	 * resStreamOut.write(buffer, 0, readBytes); } } catch (Exception ex) {
	 * throw ex; } finally { stream.close(); resStreamOut.close(); }
	 *
	 * return jarFolder + resourceName; }
	 *
	 * public static void exportResource(String resourceName, String path) {
	 * File f = new File(System.getProperty("java.class.path")); File dir2 =
	 * f.getAbsoluteFile().getParentFile(); String path2 = dir2.toString(); URL
	 * url = Initialization.class.getResource(resourceName); try { File dir =
	 * new File(url.getPath().replaceFirst("/", "").replaceAll("%20",
	 * " ").replaceFirst("file:","")); String[] preDir = path2.split(";");
	 *
	 * String des = path +"\\"+ dir.getName(); if(preDir.length > 1) des =
	 * preDir[1] + des; else des = preDir[0]; File outDes = new File(des);
	 * Files.copy(dir.toPath(), outDes.toPath(),
	 * StandardCopyOption.REPLACE_EXISTING); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	public static List<String> readFile(final File file) {
		final String fullPath = file.getAbsolutePath();
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fullPath), Charset.forName("UTF-8"));
		} catch (final IOException e) {
			System.out.println("Failed to load file.");
			e.printStackTrace();
		}
		return lines;
	}

	public static List<String> readFile(final String path, final Charset encoding) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(path), encoding);
		} catch (final IOException e) {
			System.out.println("Failed to load file.");
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * Loads a file in a List line by line.
	 *
	 * @param path
	 *            Path to the file from the jar folder.
	 * @param name
	 *            The name of the file.
	 * @param encoding
	 *            The encoding of a file.
	 * @return Returns the file.
	 */
	public static List<String> readFile(final String path, final String name, final Charset encoding) {
		final String fullPath = getDirectory() + "\\" + path + "\\" + name;
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fullPath), encoding);
		} catch (final IOException e) {
			System.out.println("Failed to load file.");
			e.printStackTrace();
		}
		return lines;
	}

	public static boolean runJar(final String loc) {
		final ProcessBuilder builder = new ProcessBuilder("java", "-jar", loc);
		try {
			final Process ps = builder.start();
			System.out.println("Running from: " + loc);
			ps.waitFor();
			final java.io.InputStream is = ps.getInputStream();
			final byte b[] = new byte[is.available()];
			is.read(b, 0, b.length);
			System.out.println("Test: " + new String(b));
			is.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

}
