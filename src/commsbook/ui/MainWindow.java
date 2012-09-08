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
import java.util.List;

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

import uk.co.timwise.wraplayout.WrapLayout;

import commsbook.Engine;
import commsbook.model.*;

import commsbook.ui.actionlisteners.*;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

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

	private final Engine engine;

	/**
	 * Create the application.
	 * @param engine 
	 */
	public MainWindow(Engine engine) {
		this.engine = engine;
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
				loadLibrary();
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
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About about = new About();
				about.ShowVersion();
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_container = new JPanel();
		frame.getContentPane().add(panel_container, BorderLayout.CENTER);
		GridBagLayout gbl_panel_container = new GridBagLayout();
		gbl_panel_container.columnWidths = new int[] { 120, 300 };
		gbl_panel_container.rowHeights = new int[] {10, 120, 10, 200, 10, 110};
		gbl_panel_container.columnWeights = new double[] { Double.MIN_VALUE, 1.0 };
		gbl_panel_container.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
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
						Speech.speakSentence(sentence);
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.gridheight = 2;
				gbc_btnNewButton.fill = GridBagConstraints.BOTH;
				gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.gridy = 0;
				panel_container.add(btnNewButton, gbc_btnNewButton);
				
				JScrollPane scroll_sentence = new JScrollPane();
				scroll_sentence.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				GridBagConstraints gbc_scroll_sentence = new GridBagConstraints();
				gbc_scroll_sentence.fill = GridBagConstraints.BOTH;
				gbc_scroll_sentence.insets = new Insets(0, 0, 5, 0);
				gbc_scroll_sentence.gridx = 1;
				gbc_scroll_sentence.gridy = 1;
				panel_container.add(scroll_sentence, gbc_scroll_sentence);
		
				panel_sentence = new JPanel();
				scroll_sentence.setViewportView(panel_sentence);
				FlowLayout flowLayout_1 = (FlowLayout) panel_sentence.getLayout();
				flowLayout_1.setAlignOnBaseline(true);
				flowLayout_1.setAlignment(FlowLayout.LEADING);
				panel_sentence.setBackground(Color.WHITE);
				panel_sentence.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
		gbc_lblLib.insets = new Insets(0, 0, 5, 0);
		gbc_lblLib.gridx = 1;
		gbc_lblLib.gridy = 2;
		panel_container.add(lblLib, gbc_lblLib);
				
				JScrollPane scroll_path = new JScrollPane();
				scroll_path.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				GridBagConstraints gbc_scroll_path = new GridBagConstraints();
				gbc_scroll_path.weighty = 1.0;
				gbc_scroll_path.fill = GridBagConstraints.BOTH;
				gbc_scroll_path.insets = new Insets(0, 0, 5, 5);
				gbc_scroll_path.gridx = 0;
				gbc_scroll_path.gridy = 3;
				panel_container.add(scroll_path, gbc_scroll_path);
				
				panel_path = new JPanel();
				scroll_path.setViewportView(panel_path);
				panel_path.setBackground(Color.WHITE);
				panel_path.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel_path.setLayout(new BoxLayout(panel_path, BoxLayout.Y_AXIS));
				
				JScrollPane scroll_category = new JScrollPane();
				GridBagConstraints gbc_scroll_category = new GridBagConstraints();
				gbc_scroll_category.gridheight = 3;
				gbc_scroll_category.insets = new Insets(0, 0, 5, 0);
				gbc_scroll_category.fill = GridBagConstraints.BOTH;
				gbc_scroll_category.gridx = 1;
				gbc_scroll_category.gridy = 3;
				panel_container.add(scroll_category, gbc_scroll_category);
		
				panel_category = new JPanel();
				scroll_category.setViewportView(panel_category);
				WrapLayout wl_panel_category = new WrapLayout();
				wl_panel_category.setAlignment(FlowLayout.LEFT);
				panel_category.setLayout(wl_panel_category);
				panel_category.setBackground(Color.WHITE);
				panel_category.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				JLabel lblCurrent = new JLabel("Selected category:");
				GridBagConstraints gbc_lblCurrent = new GridBagConstraints();
				gbc_lblCurrent.fill = GridBagConstraints.BOTH;
				gbc_lblCurrent.insets = new Insets(0, 0, 0, 5);
				gbc_lblCurrent.gridx = 0;
				gbc_lblCurrent.gridy = 4;
				panel_container.add(lblCurrent, gbc_lblCurrent);
				
				JPanel panel_current = new JPanel();
				panel_current.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_current.setBackground(Color.WHITE);
				GridBagConstraints gbc_panel_current = new GridBagConstraints();
				gbc_panel_current.insets = new Insets(0, 0, 5, 5);
				gbc_panel_current.fill = GridBagConstraints.BOTH;
				gbc_panel_current.gridx = 0;
				gbc_panel_current.gridy = 5;
				panel_container.add(panel_current, gbc_panel_current);
	}

	private void repaintPathPanel() {
		panel_path.removeAll();
		for (Category category : engine.getCategoryPath()) {
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
			pathItemButton.addActionListener(new PathPanelItemListener(category, engine));
			pathItemButton.setMargin(symbolInsets);
			panel_path.add(pathItemButton);
		}
		panel_path.revalidate();
		panel_path.repaint();
	}

	public void repaintCategory() {
		ImageIcon icon;
		String name;
		String iconPath;
		panel_category.removeAll(); // clear the existing category
		// display (or initial prompt)
		// load the images into the visual library
		for (CategoryItem item : engine.getCurrentCategoryItems()) {
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
			libraryItem.addActionListener(new CategoryItemListener(item, engine));
			libraryItem.setMargin(symbolInsets);
			panel_category.add(libraryItem);
		}
		panel_category.revalidate();
		panel_category.repaint();
		repaintPathPanel();
	}
	
	private JButton createSentenceButton(Symbol symbol){
		ImageIcon icon = new ImageIcon(symbol.getIconPath(), symbol.getName());
		JButton libraryItem = new JButton(icon);
		libraryItem.setText(symbol.getName());
		libraryItem.setBackground(Color.WHITE); // TODO: doesn't seem to
												// work in ubuntu. hrmm.
		libraryItem.setVerticalTextPosition(JButton.BOTTOM);
		libraryItem.setHorizontalTextPosition(JButton.CENTER);
		libraryItem.setVerticalAlignment(JButton.BOTTOM);
		libraryItem.setHorizontalAlignment(JButton.CENTER);
		libraryItem.addActionListener(new SentenceItemListener(symbol, engine));
		libraryItem.setMargin(symbolInsets);
		return libraryItem;
	}

	public void loadLibrary() {
		if (libraryFolderChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File libraryFolder = libraryFolderChooser.getSelectedFile();
		engine.loadLibrary(libraryFolder);
	}

	public void repaintSentence() {
		panel_sentence.removeAll();
		for (Symbol symbol : engine.getSentence()) {
			panel_sentence.add(createSentenceButton(symbol));
		}
		panel_sentence.revalidate();
		panel_sentence.repaint();
	}
}

