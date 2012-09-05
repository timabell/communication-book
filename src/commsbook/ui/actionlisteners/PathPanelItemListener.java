package commsbook.ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commsbook.Engine;
import commsbook.model.Category;

public class PathPanelItemListener implements ActionListener {
	private final Category category;
	private final Engine engine;

	public PathPanelItemListener(Category category, Engine engine) {
		this.category = category;
		this.engine = engine;
	}

	public void actionPerformed(ActionEvent ae) {
		engine.switchCategory(category);
	}
}
