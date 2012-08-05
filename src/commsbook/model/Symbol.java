package commsbook.model;

import java.io.File;

/**
 * 
 * @author tim
 */
public class Symbol extends CategoryItem {

	public static Symbol Load(File file) {
		Symbol symbol = new Symbol();
		symbol.iconPath = file.getPath();
		String filename = file.getName();
		if (filename.contains(".")) {
			// strip off the file extension
			filename = filename.substring(0, filename.lastIndexOf('.'));
		}
		symbol.name = filename;
		return symbol;
	}

	@Override
	public String toString() {
		return "Symbol: " + getName();
	}

	@Override
	public boolean getIsCategory() {
		return false;
	}
}
