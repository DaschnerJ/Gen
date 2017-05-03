package daschnerj.gen.data;

import java.io.File;
import java.util.ArrayList;

import daschnerj.gen.data.config.Config;
import daschnerj.gen.data.files.Lines;
import daschnerj.gen.data.fonts.GameFont;
import daschnerj.gen.data.levels.Level;
import daschnerj.gen.data.sounds.GameAudio;
import daschnerj.gen.data.textures.Texture;
import daschnerj.gen.utils.Utils;

public class Data implements IData {

	public ArrayList<File> files = new ArrayList<>();

	public Data() {
		loadData();
	}

	@Override
	public ArrayList<File> getAllFiles(final String path) {
		// .............list file
		final File directory = new File(path);

		final ArrayList<File> rList = new ArrayList<>();
		// get all the files from a directory
		final File[] fList = directory.listFiles();

		for (final File file : fList) {
			if (file.isFile()) {
				rList.add(file);
			} else if (file.isDirectory()) {
				rList.addAll(getAllFiles(file.getAbsolutePath()));
			}
		}
		return rList;
	}

	@Override
	public String getFileEnding(final File file) {
		final String[] a = file.getName().split("\\.");
		return a[a.length - 1];
	}

	private String getFileName(final File f) {
		final String[] s = f.getName().split("\\.");
		return s[0];
	}

	@Override
	public void loadAllFiles() {
		for (final File f : files) {
			final String s = getFileEnding(f);
			final String n = getFileName(f);
			switch (s) {
			case "png":
				DataObjects.textures.put(n, new Texture(f));
				DataObjects.bin.put(n, f);
				break;
			case "gcf":
				DataObjects.configs.put(n, new Config(f));
				DataObjects.bin.put(n, f);
				break;
			case "gwf":
				DataObjects.worlds.put(n, new Level(f));
				DataObjects.bin.put(n, f);
				break;
			case "wav":
				DataObjects.sounds.put(n, new GameAudio(f));
				DataObjects.bin.put(n, f);
				break;
			case "ttf":
				DataObjects.fonts.put(n, new GameFont(f));
				DataObjects.bin.put(n, f);
				break;
			case "txt":
				DataObjects.files.put(n, new Lines(f));
				DataObjects.bin.put(n, f);
				break;
			}
		}

	}

	/**
	 * Gets all the data and loads them.
	 */
	private void loadData() {
		files = getAllFiles(Utils.getDirectory() + "\\Gen\\");
		loadAllFiles();

	}

}
