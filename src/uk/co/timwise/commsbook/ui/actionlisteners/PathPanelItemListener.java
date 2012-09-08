package uk.co.timwise.commsbook.ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uk.co.timwise.commsbook.Engine;
import uk.co.timwise.commsbook.model.Category;


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
