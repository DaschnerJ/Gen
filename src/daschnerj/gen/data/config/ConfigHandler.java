package daschnerj.gen.data.config;

import java.io.File;
import java.util.ArrayList;

import daschnerj.gen.data.DataObjects;
import daschnerj.gen.utils.Utils;

public class ConfigHandler {

	/**
	 * Creates and handles all the configs as one unit.
	 */
	public ConfigHandler() {
		// System.out.println("Finding all config files in director
		// "+Utils.getDirectory()+"\\gen"+"...");
		refreshConfig();
		System.out.println(DataObjects.configs.size() + " files were found.");

	}

	public ArrayList<Config> findConfigsWithAttribute(final String type, final String attribute) {
		final ArrayList<Config> typesMatch = new ArrayList<>();
		for (final Config c : DataObjects.configs.values()) {
			if (c.getAttribute(type).equals(attribute)) {
				typesMatch.add(c);
			}
		}

		return typesMatch;
	}

	/**
	 * Find all files within the given path.
	 * 
	 * @param pathname
	 *            The path to look for config files.
	 */
	private void findFile(final String pathname) {
		final File f = new File(pathname);
		final File[] listfiles = f.listFiles();
		if (listfiles != null) {
			for (final File listfile : listfiles) {
				if (listfile.isDirectory()) {
					final File[] internalFile = listfile.listFiles();
					for (final File element : internalFile) {

						if (hasExtension(element, ".gcf")) {
							// System.out.println(internalFile[j]);
							DataObjects.configs.put(listfile.getName(), new Config(element));
						}
						if (element.isDirectory()) {
							final String name = element.getAbsolutePath();
							findFile(name);
						}

					}
				} else {
					if (hasExtension(listfile, ".gcf")) {
						// System.out.println(listfiles[i]);
						DataObjects.configs.put(listfile.getName(), new Config(listfile));
					}
				}

			}
		}

	}

	private boolean hasExtension(final File file, final String extension) {
		return file.getAbsolutePath().endsWith(extension);
	}

	public void refreshConfig() {
		DataObjects.configs.clear();
		findFile(Utils.getDirectory() + "\\gen");
	}

}
