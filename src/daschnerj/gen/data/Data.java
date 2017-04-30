package daschnerj.gen.data;

import java.io.File;
import java.util.ArrayList;

public class Data implements IData{
	
	public ArrayList<File> files = new ArrayList<File>();
	
	public Data()
	{
		loadData();
	}
	
	/**
	 * Gets all the data and loads them.
	 */
	private void loadData()
	{
		files = getAllFiles();
		
	}
	
	@Override
	public ArrayList<File> getAllFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadAllFiles() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFileEnding(File file) {
		// TODO Auto-generated method stub
		return null;
	} 

}
