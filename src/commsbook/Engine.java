package commsbook;

import java.io.File;
import java.util.ArrayList;
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
	
	public Engine(){
		initialiseState();
	}
	
	private void initialiseState() {
		sentence = new ArrayList<Symbol>();
	}

	public void loadLibrary(File folder){
		this.libraryFolder = folder;

		// clear previous state
		initialiseState();
		
		//TODO: fire event to reload whole UI
	}

	public void loadCategory(File folder) {
		selectedCategory = Category.load(folder);
		
		// TODO update display with new location in library (i.e. the breadcrumb trail navigation)
		//addCategoryToPathPanel(category);
		
		// TODO update the list of symbols with this category
		//showCategory(category);
	}

	public List<Symbol> getSentence(){
		return sentence;
	}
	
	public void addToSentence(Symbol sybmol){
		sentence.add(sybmol);
		// TODO: trigger update of sentence display
	}
	
	public List<CategoryItem> getCurrentCategoryItems(){
		if (selectedCategory == null){
			return new ArrayList<CategoryItem>();
		}
		return selectedCategory.getSymbols();
	}
}
