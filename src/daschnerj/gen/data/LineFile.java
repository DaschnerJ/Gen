package daschnerj.gen.data;

import java.io.File;
import java.util.ArrayList;

public interface LineFile {
	
	public File getFile();
	
	public ArrayList<String> getLines();
	
	public void setFile(File file);
	
	void readLines();

}
