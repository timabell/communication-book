package commsbook.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblVersion;

	/**
	 * Create the dialog.
	 */
	public About() {
		setTitle("About");
		setBounds(100, 100, 469, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JLabel lblCommunicationBook = new JLabel("Communication Book");
			contentPanel.add(lblCommunicationBook);
		}
		{
			JLabel lblNewLabel = new JLabel("https://launchpad.net/communication");
			contentPanel.add(lblNewLabel);
		}
		{
			lblVersion = new JLabel("Version: no jar file");
			contentPanel.add(lblVersion);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JTextPane txtpnCommunicationBookAn = new JTextPane();
				scrollPane.setViewportView(txtpnCommunicationBookAn);
				txtpnCommunicationBookAn.setText("Communication Book, an electronic communication aid.\nCopyright (C) 2012 Tim Abell <tim@timwise.co.uk>, John Morton\n\n========\n\nThis program is free software: you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation, either version 3 of the License, or\n(at your option) any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License\nalong with this program.  If not, see <http://www.gnu.org/licenses/>.");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkListener(this));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void ShowVersion(){
		String implementationVersion = About.class.getPackage().getImplementationVersion();
		if (implementationVersion != null){
			lblVersion.setText("Version: " + implementationVersion);
		}
	}

	private class OkListener implements ActionListener{

		private final About panel;

		public OkListener(About panel){
			this.panel = panel;
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
		}
		
	}
}
