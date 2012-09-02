package commsbook.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import commsbook.model.*;

public class MainWindow {

	private JFrame frame;

	// I know JFileChooser looks crap, but we need folder selection.
	// Kick Sun's Oracle 0wned arse if you want this to get better
	// http://bugs.sun.com/view_bug.do?bug_id=6192906
	// or find some other library. :'-(
	JFileChooser libraryFolderChooser = new JFileChooser();
	private JPanel panel_category;
	private JPanel panel_sentence;
	private JPanel panel_path;
	private final Insets symbolInsets = new Insets(1, 2, 1, 2);

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();

		// set up the library selection dialog
		libraryFolderChooser
				.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		libraryFolderChooser.setDialogTitle("Select a library folder");
		// maximise the window - ref http://stackoverflow.com/a/5207711/10245
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Communication Book");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadLibrary = new JMenuItem("Load Library...");
		mntmLoadLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: call back to engine to load library
				// loadLibrary();
			}
		});
		mntmLoadLibrary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmLoadLibrary);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); //close the app
			}
		});
		mntmQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmQuit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_container = new JPanel();
		frame.getContentPane().add(panel_container, BorderLayout.CENTER);
		GridBagLayout gbl_panel_container = new GridBagLayout();
		gbl_panel_container.columnWidths = new int[] { 120, 300 };
		gbl_panel_container.rowHeights = new int[] { 10, 110, 10, 300 };
		gbl_panel_container.columnWeights = new double[] { Double.MIN_VALUE, 1.0 };
		gbl_panel_container.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		panel_container.setLayout(gbl_panel_container);

		JLabel lblNewLabel = new JLabel(
				"Sentence:   (click to remove a symbol)");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(5, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_container.add(lblNewLabel, gbc_lblNewLabel);
		
				JButton btnNewButton = new JButton("Speak");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Component[] components = panel_sentence.getComponents();
						String sentence = "";
						for (Component component : components){
							sentence = sentence + ((JButton)component).getText() + " ";
						}
						// Speech.speakSentence(sentence); // TODO: trigger speech
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.gridheight = 2;
				gbc_btnNewButton.fill = GridBagConstraints.BOTH;
				gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.gridy = 0;
				panel_container.add(btnNewButton, gbc_btnNewButton);
		
				panel_sentence = new JPanel();
				FlowLayout flowLayout_1 = (FlowLayout) panel_sentence.getLayout();
				flowLayout_1.setAlignment(FlowLayout.LEADING);
				panel_sentence.setBackground(Color.WHITE);
				panel_sentence.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				GridBagConstraints gbc_panel_sentence = new GridBagConstraints();
				gbc_panel_sentence.insets = new Insets(0, 0, 5, 5);
				gbc_panel_sentence.fill = GridBagConstraints.BOTH;
				gbc_panel_sentence.gridx = 1;
				gbc_panel_sentence.gridy = 1;
				panel_container.add(panel_sentence, gbc_panel_sentence);
		
		JLabel lblUp = new JLabel("Back to:");
		GridBagConstraints gbc_lblUp = new GridBagConstraints();
		gbc_lblUp.anchor = GridBagConstraints.WEST;
		gbc_lblUp.insets = new Insets(0, 5, 5, 5);
		gbc_lblUp.gridx = 0;
		gbc_lblUp.gridy = 2;
		panel_container.add(lblUp, gbc_lblUp);
		
		JLabel lblLib = new JLabel("Symbols and categories, click to select:");
		GridBagConstraints gbc_lblLib = new GridBagConstraints();
		gbc_lblLib.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLib.insets = new Insets(0, 0, 5, 5);
		gbc_lblLib.gridx = 1;
		gbc_lblLib.gridy = 2;
		panel_container.add(lblLib, gbc_lblLib);
		
				panel_category = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_category.getLayout();
				flowLayout.setAlignment(FlowLayout.LEADING);
				panel_category.setBackground(Color.WHITE);
				panel_category.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				GridBagConstraints gbc_panel_library = new GridBagConstraints();
				gbc_panel_library.insets = new Insets(0, 0, 5, 5);
				gbc_panel_library.fill = GridBagConstraints.BOTH;
				gbc_panel_library.gridx = 1;
				gbc_panel_library.gridy = 3;
				panel_container.add(panel_category, gbc_panel_library);
		
		panel_path = new JPanel();
		panel_path.setBackground(Color.WHITE);
		panel_path.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_panel_path = new GridBagConstraints();
		gbc_panel_path.insets = new Insets(0, 5, 5, 5);
		gbc_panel_path.fill = GridBagConstraints.BOTH;
		gbc_panel_path.gridx = 0;
		gbc_panel_path.gridy = 3;
		panel_container.add(panel_path, gbc_panel_path);
		panel_path.setLayout(new BoxLayout(panel_path, BoxLayout.Y_AXIS));
	}

	private void addCategoryToPathPanel(Category category) {
		ImageIcon icon;
		String name = category.getName();
		String iconPath = category.getIconPath();
		if (iconPath == null) {
			URL resource = getClass().getResource(
					"/commsbook/resources/folder.png");
			icon = new ImageIcon(resource, name);
		} else {
			icon = new ImageIcon(iconPath, name);
		}
		JButton pathItemButton = new JButton(icon);
		pathItemButton.setText(name);
		pathItemButton.setBackground(Color.WHITE); // TODO: doesn't seem to
												// work in ubuntu. hrmm.
		pathItemButton.setVerticalTextPosition(JButton.BOTTOM);
		pathItemButton.setHorizontalTextPosition(JButton.CENTER);
		pathItemButton.setVerticalAlignment(JButton.BOTTOM);
		pathItemButton.setHorizontalAlignment(JButton.CENTER);
		pathItemButton.addActionListener(new PathPanelItemListener(category.getPath(), panel_path, pathItemButton, this));
		pathItemButton.setMargin(symbolInsets);
		panel_path.add(pathItemButton);
		panel_path.revalidate();
		panel_path.repaint();
	}

	private void showCategory(Category category) {
		ImageIcon icon;
		String name;
		String iconPath;
		panel_category.removeAll(); // clear the existing category
		// display (or initial prompt)
		// load the images into the visual library
		for (CategoryItem item : category.getSymbols()) {
			name = item.getName();
			iconPath = item.getIconPath();
			if (iconPath == null) {
				URL resource = getClass().getResource(
						"/commsbook/resources/folder.png");
				icon = new ImageIcon(resource, name);
			} else {
				icon = new ImageIcon(iconPath, name);
			}
			JButton libraryItem = new JButton(icon);
			libraryItem.setText(name);
			libraryItem.setBackground(Color.WHITE); // TODO: doesn't seem to
													// work in ubuntu. hrmm.
			if (item.getIsCategory()){
				// highlight categories visually as being different
				libraryItem.setBorder(new BevelBorder(BevelBorder.RAISED));
				libraryItem.setForeground(Color.BLUE);
			}
			libraryItem.setVerticalTextPosition(JButton.BOTTOM);
			libraryItem.setHorizontalTextPosition(JButton.CENTER);
			libraryItem.setVerticalAlignment(JButton.BOTTOM);
			libraryItem.setHorizontalAlignment(JButton.CENTER);
			libraryItem.addActionListener(new CategoryItemListener(item, this));
			libraryItem.setMargin(symbolInsets);
			panel_category.add(libraryItem);
		}
		panel_category.revalidate();
		panel_category.repaint();
	}
	
	public void addToSentence(Symbol symbol){
		ImageIcon icon = new ImageIcon(symbol.getIconPath(), symbol.getName());
		JButton libraryItem = new JButton(icon);
		libraryItem.setText(symbol.getName());
		libraryItem.setBackground(Color.WHITE); // TODO: doesn't seem to
												// work in ubuntu. hrmm.
		libraryItem.setVerticalTextPosition(JButton.BOTTOM);
		libraryItem.setHorizontalTextPosition(JButton.CENTER);
		libraryItem.setVerticalAlignment(JButton.BOTTOM);
		libraryItem.setHorizontalAlignment(JButton.CENTER);
		libraryItem.addActionListener(new SentenceItemListener(panel_sentence, libraryItem));
		libraryItem.setMargin(symbolInsets);
		panel_sentence.add(libraryItem);
		panel_sentence.revalidate();
		panel_sentence.repaint();
	}

	public void loadLibrary() {
		if (libraryFolderChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File libraryFolder = libraryFolderChooser.getSelectedFile();
		panel_path.removeAll();
		panel_path.revalidate();
		panel_path.repaint();
		//loadCategory(libraryFolder);
	}
}

class CategoryItemListener implements ActionListener {
	private final CategoryItem item;
	private final MainWindow view;

	CategoryItemListener(CategoryItem item, MainWindow view) {
		this.item = item;
		this.view = view;
	}

	public void actionPerformed(ActionEvent ae) {
		if (item.getIsCategory()) {
			// load another category
			//view.loadCategory(new File(((Category) item).getPath()));
		} else {
			view.addToSentence((Symbol)item);
		}
	}
}

class SentenceItemListener implements ActionListener {
	private final JButton button;
	private JPanel container;

	SentenceItemListener(JPanel container, JButton button) {
		this.container = container;
		this.button = button;
	}

	public void actionPerformed(ActionEvent ae) {
		// remove from sentence
		container.remove(button);
		container.revalidate();
		container.repaint();
	}
}

class PathPanelItemListener implements ActionListener {
	private final String path;
	private final MainWindow view;
	private JPanel container;
	private JButton button;

	PathPanelItemListener(String path, JPanel container, JButton button, MainWindow view) {
		this.path = path;
		this.container = container;
		this.button = button;
		this.view = view;
	}

	public void actionPerformed(ActionEvent ae) {
		//remove all subsequent buttons
		boolean past = false;
		for (Component component : container.getComponents()){
			if (past) {
				container.remove(component);
			} else if (component==button){
				container.remove(component); // clicked button re-added by load routine. 
				past = true;
			}
		}
		container.revalidate();
		container.repaint();
		//load the selected category
		//view.loadCategory(new File(path));
	}
}

