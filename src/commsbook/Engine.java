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
	private File libraryFolder;
	private List<Symbol> sentence = new ArrayList<Symbol>();
	private Category selectedCategory;
	private List<Category> categoryPath = new ArrayList<Category>();
	private StateChangeListener sentenceListener;
	private StateChangeListener categoryListener;
	
	public void loadLibrary(File folder){
		// clear path for previous library if any
		categoryPath.clear();

		// load the new library's top level category for display
		libraryFolder = folder;
		loadCategory(folder);
		
		// fire events to reload whole UI
		categoryListener.stateChanged();
	}

	public void loadCategory(Category category) {
		selectedCategory = category;

		// update path with new location in library (i.e. the breadcrumb trail navigation)
		if (categoryPath.contains(category)){
			// remove everything after selected category (starting at the end first)
			for (int i = categoryPath.size() - 1; i >= 0; i--){
				if (categoryPath.get(i) == category){
					break;
				}
				categoryPath.remove(i);
			}
		} else {
			categoryPath.add(selectedCategory);
		}
		
		// update the list of symbols with this category
		categoryListener.stateChanged();
	}

	public void loadCategory(File folder) {
		loadCategory(Category.load(folder));
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
		if (selectedCategory == null){
			return new ArrayList<CategoryItem>();
		}
		return selectedCategory.getSymbols();
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

	public interface StateChangeListener{
		public void stateChanged();
	}
}
