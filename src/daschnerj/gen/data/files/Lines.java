package daschnerj.gen.data.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import daschnerj.gen.data.LineFile;

public class Lines implements LineFile {

	protected File file;
	protected ArrayList<String> lines = new ArrayList<>();

	public Lines(final File file) {
		this.file = file;
		readLines();
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getLines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readLines() {
		if (lines != null) {
			lines.clear();
		}
		// Open the file
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(file);
			final BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				lines.add(strLine);
			}

			// Close the input stream
			br.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setFile(final File file) {
		// TODO Auto-generated method stub

	}

}
