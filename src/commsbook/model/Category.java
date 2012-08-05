package commsbook.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author tim
 */
public class Category extends CategoryItem {
	private List<CategoryItem> items = new ArrayList<CategoryItem>();
	protected String name;
	protected String iconPath;
	private String path;

	/**
	 * @return the symbols
	 */
	public List<CategoryItem> getSymbols() {
		return items;
	}

	public static Category load(File folder) {
		return load(folder, true);
	}

	public static Category load(File folder, boolean scanForItems) {
		Category category = new Category();
		category.name = folder.getName();
		category.path = folder.getPath();

		File iconFile = new File(folder, "category-symbol.png");
		if (iconFile.exists()) {
			category.iconPath = iconFile.getPath();
		}
		if (!scanForItems) {
			return category;
		}
		// load all the symbols & categories contained in the folder
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					// process symbols in this category
					if (file.getName().equals("category-symbol.png")) {
						continue; // skip category symbol
					}
					category.items.add(Symbol.Load(file));
				} else if (file.isDirectory()) {
					category.items.add(Category.load(file, false)); // avoid
																	// recursive
																	// load
				}
			}
		}
		System.out.println(String.format("Loaded %s (%d items)", category,
				category.items.size()));
		Collections.sort(category.items);
		return category;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getIconPath() {
		return iconPath;
	}

	@Override
	public boolean getIsCategory() {
		return true;
	}

	@Override
	public String toString() {
		return "Category: " + getName();
	}
}
