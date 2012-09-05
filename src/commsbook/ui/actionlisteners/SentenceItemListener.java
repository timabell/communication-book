package commsbook.ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commsbook.Engine;
import commsbook.model.Symbol;

public class SentenceItemListener implements ActionListener {
	private final Symbol symbol;
	private final Engine engine;

	public SentenceItemListener(Symbol symbol, Engine engine) {
		this.symbol = symbol;
		this.engine = engine;
	}

	public void actionPerformed(ActionEvent ae) {
		// remove from sentence
		engine.removeFromSentence(symbol);
	}
}
