package daschnerj.gen.data;

import java.io.File;
import java.util.ArrayList;

public interface IData {
	
	/**
	 * Gets all the required files for the game.
	 * @return Returns the array list of files within the jar folder.
	 */
	public ArrayList<File> getAllFiles(String path);
	
	/**
	 * Sorts all the files in the proper data locations.
	 */
	public void loadAllFiles();
	
	/**
	 * Gets the ending of a file.
	 * @param file The file to get the ending of.
	 * @return Returns the ending such as gcf, gwf, txt, png, wav...
	 */
	public String getFileEnding(File file);

}
