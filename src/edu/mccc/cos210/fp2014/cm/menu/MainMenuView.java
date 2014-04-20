package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * The main menu.
 * This menu allows a user to chose what type of game they want to play.
 */
public class MainMenuView extends SettingsView implements ActionListener  {
	private static final long serialVersionUID = 1L;
	public MainMenuView(Checkmate c) {
		super(c);
		
		// Use Font class to make this bigger.
		JLabel titleLabel = new JLabel("Checkmate 3000 Network AI Edition");
		titleLabel.setSize(225, 50);
		titleLabel.setLocation((int)(c.getWidth() * 0.41), (int)(c.getHeight() * 0.25));
		add(titleLabel);
		
		JButton localButton = new JButton("Local Game");
		localButton.setSize(150, 50);
		localButton.setLocation((int)(c.getWidth() * 0.45), (int)(c.getHeight() * 0.45));
		localButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.LOCAL);
			}
		});
		add(localButton);
		
		JButton hostButton = new JButton("Host Game");
		hostButton.setSize(150, 50);
		hostButton.setLocation((int)(c.getWidth() * 0.45), (int)(c.getHeight() * 0.55));
		hostButton.setEnabled(false);
		add(hostButton);
		
		JButton joinButton = new JButton("Join Game");
		joinButton.setSize(150, 50);
		joinButton.setLocation((int)(c.getWidth() * 0.45), (int)(c.getHeight() * 0.65));
		joinButton.setEnabled(false);
		add(joinButton);
		
		JButton quitButton = new JButton("Quit Game");
		quitButton.setSize(150, 50);
		quitButton.setLocation((int)(c.getWidth() * 0.45), (int)(c.getHeight() * 0.75));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int wantsExit = JOptionPane.showConfirmDialog(
					myCheckmate,
					"Are you sure you want to exit the program?",
					"Exit Program?",
					JOptionPane.YES_NO_OPTION
				);
				if (wantsExit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		add(quitButton);
	}
	/**
	 * Changes the view based on what button you've pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
