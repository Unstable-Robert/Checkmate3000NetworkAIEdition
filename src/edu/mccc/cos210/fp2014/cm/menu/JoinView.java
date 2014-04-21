package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Join network game menu.
 * Menu allows user to join a local game.
 */
public class JoinView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	public JoinView(Checkmate c) {
		super(c);
		
		JButton backButton = new JButton("Back");
		backButton.setSize(100,50);
		backButton.setLocation((int)(c.getWidth() * 0.03),(int)(c.getHeight() * 0.05));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(backButton);
		
		//startButton starts the game with given settings
		JButton joinButton = new JButton("Join Game");
		joinButton.setSize(100,50);
		joinButton.setLocation((int)(c.getWidth() * 0.78), (int)(c.getHeight() * 0.05));
		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Join Button Clicked");
			}
		});
		add(joinButton);
		
		JLabel ipLabel = new JLabel("Opponent's IP Address:");
		ipLabel.setSize(250, 20);
		ipLabel.setLocation((int)(c.getWidth() * 0.25),(int)(c.getHeight() * 0.42));
		add(ipLabel);
		
		JTextField ipTextField = new JTextField();
		ipTextField.setSize(250, 20);
		ipTextField.setLocation((int)(c.getWidth() * 0.35),(int)(c.getHeight() * 0.47));
		add(ipTextField);
	}
	/**
	 * Allows user to enter the host's IP and return to the main menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
