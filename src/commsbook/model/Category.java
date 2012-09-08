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
	private File folder;
	private Category parent;

	public Category(String name, File folder, Category parent) {
		this.name = name;
		this.folder = folder;
		this.parent = parent;
	}

	/**
	 * @return the symbols
	 */
	public List<CategoryItem> getSymbols() {
		return items;
	}

	public static Category load(File folder) {
		return load(folder, true, null);
	}

	public static Category load(File folder, boolean scanForItems, Category parent) {
		Category category = new Category(folder.getName(), folder, parent);

		File iconFile = new File(folder, "category-symbol.png");
		if (iconFile.exists()) {
			category.iconPath = iconFile.getPath();
		}
		if (scanForItems) {
			category.loadContents();
		}
		return category;
	}


	/**
	 * Scan folder for items, if not already loaded.
	 */
	public void loadContents() {
		if (!items.isEmpty()){
			return; // already loaded
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
					items.add(Symbol.Load(file));
				} else if (file.isDirectory()) {
					 // Load only basic category info to avoid recursive load, loading subcategories will be on demand.
					items.add(Category.load(file, false, this));
				}
			}
		}
		System.out.println(String.format("Loaded %s (%d items)", this, items.size()));
		Collections.sort(items);
	}

	public String getPath() {
		return folder.getPath();
	}

	@Override
	public boolean getIsCategory() {
		return true;
	}

	@Override
	public String toString() {
		return "Category: " + getName();
	}

	public Category getParent() {
		return parent;
	}
}
