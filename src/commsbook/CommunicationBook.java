package commsbook;

import java.awt.EventQueue;
import java.io.File;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import commsbook.ui.MainWindow;
import commsbook.ui.Speech;

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
			// Create & Show the UI
			MainWindow window = new MainWindow();

			// Load the engine
			Engine engine = new Engine();
			
			// TODO: connect UI to Engine & Speech

			// Load the library specified in the command line if any
			if (libraryPathArg != null) {
				File path = new File(libraryPathArg);
				engine.loadCategory(path);
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
}
