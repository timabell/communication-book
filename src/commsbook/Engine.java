package commsbook;

import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import commsbook.model.*;

/**
 * This class manages the state of the library / sentence / display,
 * but is separated from the UI so is more or less a Presentation Model.
 * The can be show
 */
public class Engine {
	private List<Symbol> sentence = new ArrayList<Symbol>();
	private Category selectedCategory;
	private List<Category> categoryPath = new ArrayList<Category>();
	private StateChangeListener sentenceListener;
	private StateChangeListener categoryListener;
	private Category topCategory;
	
	public void loadLibrary(File folder){
		// clear path for previous library if any
		categoryPath.clear();

		topCategory = Category.load(folder);
		topCategory.setName("Back to start"); // Make it clearer how to get back to top.

		selectedCategory = topCategory; // TODO, differentiate between back to start and home

		// fire events to reload whole UI
		categoryListener.stateChanged();
	}

	public void switchCategory(Category category) {
		selectedCategory = category;

		Category parent = category.getParent();

		// update path with new location in library (i.e. the breadcrumb trail navigation)
		if (parent == null) {
			// at the top level, just clear everything
			categoryPath.clear();
		} else if (categoryPath.contains(parent)){
			// everything after the current parent
			for (int i = categoryPath.size() - 1; i >= 0; i--){
				if (categoryPath.get(i) == parent){
					break;
				}
				categoryPath.remove(i);
			}
		} else {
			// new category selected, add the parent to the path
			categoryPath.add(parent);
		}

		category.loadContents();

		// update the list of symbols with this category
		categoryListener.stateChanged();
	}

	public List<Symbol> getSentence(){
		return sentence;
	}
	
	public void addToSentence(Symbol sybmol){
		sentence.add(sybmol);

		// trigger update of sentence display
		sentenceListener.stateChanged();
	}

	public void removeFromSentence(Symbol symbol) {
		sentence.remove(symbol);

		// trigger update of sentence display
		sentenceListener.stateChanged();
	}

	public List<CategoryItem> getCurrentCategoryItems(){
		if (getSelectedCategory() == null){
			return new ArrayList<CategoryItem>();
		}
		return getSelectedCategory().getSymbols();
	}
	
	public void setSentenceListener(StateChangeListener sentenceListener) {
		this.sentenceListener = sentenceListener;
	}

	public void setCategoryListener(StateChangeListener categoryListener) {
		this.categoryListener = categoryListener;
	}

	public List<Category> getCategoryPath() {
		return categoryPath;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public interface StateChangeListener{
		public void stateChanged();
	}
}
