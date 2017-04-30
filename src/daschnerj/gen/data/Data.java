package daschnerj.gen.data;

import java.io.File;
import java.util.ArrayList;

import daschnerj.gen.utils.Utils;

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
		files = getAllFiles(Utils.getDirectory()+"\\");
		
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
	    System.out.println(fList);
	    return rList;
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
