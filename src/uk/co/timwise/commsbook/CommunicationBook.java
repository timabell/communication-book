package uk.co.timwise.commsbook;

import java.awt.EventQueue;
import java.io.File;

import uk.co.timwise.commsbook.Engine.StateChangeListener;
import uk.co.timwise.commsbook.ui.MainWindow;
import uk.co.timwise.commsbook.ui.Speech;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;


/**
 * This is the startup class for the app. Contains command line argument
 * handling, and fires up the Engine and UI.
 */
public class CommunicationBook implements Runnable {

	private final String[] rawArgs;

	@Parameter(names = { "-library", "-l" }, description = "path to library to open")
	private String libraryPathArg;

	@Parameter(names = { "-test", "-t" }, description = "test voice output")
	private boolean testRun;

	public CommunicationBook(String[] commandLineArguments) {
		this.rawArgs = commandLineArguments;
	}

	public void run() {
		try {
			new JCommander(this, rawArgs);
			if (testRun) {
				Speech.speakSentence("Testing voice output. It's working!");
				return;
			}
			// Load the engine
			Engine engine = new Engine();

			// Create & Show the UI
			MainWindow window = new MainWindow(engine);

			
			// connect UI to Engine & Speech
			engine.setSentenceListener(new SentenceListener(window));
			engine.setCategoryListener(new CategoryListener(window));

			// Load the library specified in the command line if any
			if (libraryPathArg != null) {
				File path = new File(libraryPathArg);
				engine.loadLibrary(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new CommunicationBook(args));
	}

	public class SentenceListener implements StateChangeListener {
		private final MainWindow window;

		public SentenceListener(MainWindow window) {
			this.window = window;
		}

		@Override
		public void stateChanged() {
			window.repaintSentence();
		}
	}

	public class CategoryListener implements StateChangeListener {
		private final MainWindow window;

		public CategoryListener(MainWindow window) {
			this.window = window;
		}

		@Override
		public void stateChanged() {
			window.repaintCategory();
		}
	}
}
