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

	public ArrayList<File> files = new ArrayList<File>();

	public Data() {
		loadData();
	}

	/**
	 * Gets all the data and loads them.
	 */
	private void loadData() {
		files = getAllFiles(Utils.getDirectory() +"\\Gen\\");
		loadAllFiles();

	}

	@Override
	public ArrayList<File> getAllFiles(String path) {
		// .............list file
		File directory = new File(path);

		ArrayList<File> rList = new ArrayList<File>();
		// get all the files from a directory
		File[] fList = directory.listFiles();

		for (File file : fList) {
			if (file.isFile()) {
				rList.add(file);
			} else if (file.isDirectory()) {
				rList.addAll(getAllFiles(file.getAbsolutePath()));
			}
		}
		return rList;
	}

	@Override
	public void loadAllFiles() {
		for (File f : files) {
			String s = getFileEnding(f);
			String n = getFileName(f);
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

	@Override
	public String getFileEnding(File file) {
		String[] a = file.getName().split("\\.");
		return a[a.length - 1];
	}
	
	private String getFileName(File f)
	{
		String[] s = f.getName().split("\\.");
		return s[0];
	}

}
