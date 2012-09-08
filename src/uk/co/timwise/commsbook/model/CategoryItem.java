package uk.co.timwise.commsbook.model;

/**
 * Base class to allow a mixed display of categories and symbols
 * 
 * @author tim
 */
public abstract class CategoryItem implements Comparable<CategoryItem> {
	protected String name;
	protected String iconPath;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iconPath
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * differentiator for handling mixed list of categories and items.
	 * 
	 * @return
	 */
	public abstract boolean getIsCategory();

	@Override
	public int compareTo(CategoryItem o) {
		// put categories before symbols
		if (getIsCategory() != o.getIsCategory()) {
			if (getIsCategory()) {
				return -1;
			} else {
				return 1;
			}
		}
		// sort each section by name
		return getName().compareToIgnoreCase(o.getName());
	}
}
