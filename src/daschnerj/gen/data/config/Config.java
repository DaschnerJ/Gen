package daschnerj.gen.data.config;

import java.io.File;
import java.util.HashMap;

import daschnerj.gen.data.files.Lines;

public class Config extends Lines {

	private final HashMap<String, String> attributes = new HashMap<>();

	public Config(final File file) {
		super(file);
		populateMap();

	}

	/**
	 * Allows programmatically set an attribute to the list, note it is not
	 * saved.
	 * 
	 * @param a
	 *            The attribute desired to be added.
	 * @param b
	 *            The value for the desired attribute.
	 */
	public void addAttribute(final String a, final String b) {
		attributes.put(a, b);
	}

	/**
	 * Allows programmatically get an attribute from the configuration file.
	 * 
	 * @param a
	 *            The attribute desired.
	 * @return The string value of an attribute.
	 */
	public String getAttribute(final String a) {
		return attributes.get(a);
	}

	private void populateMap() {
		attributes.clear();
		for (final String s : lines) {
			final String pair[] = s.split(":");
			attributes.put(pair[0], pair[1]);
		}
	}

}
