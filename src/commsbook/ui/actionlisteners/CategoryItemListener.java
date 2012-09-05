package commsbook.ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commsbook.Engine;
import commsbook.model.Category;
import commsbook.model.CategoryItem;
import commsbook.model.Symbol;

public class CategoryItemListener implements ActionListener {
	private final CategoryItem item;
	private final Engine engine;

	public CategoryItemListener(CategoryItem item, Engine engine) {
		this.item = item;
		this.engine = engine;
	}

	public void actionPerformed(ActionEvent ae) {
		if (item.getIsCategory()) {
			// load another category
			engine.switchCategory((Category) item);
		} else {
			engine.addToSentence((Symbol)item);
		}
	}
}
