package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;

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
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		titleLabel.setSize(635, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.200)
		);
		add(titleLabel);
		
		JButton localButton = new JButton("Local Game");
		localButton.setSize(150, 35);
		localButton.setLocation(
			c.getWidth() / 2 - localButton.getWidth() / 2, 
			(int)(c.getHeight() * 0.500)
		);
		localButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.LOCAL);
			}
		});
		add(localButton);
		
		JButton hostButton = new JButton("Host Game");
		hostButton.setSize(150, 35);
		hostButton.setLocation(
			c.getWidth() / 2 - hostButton.getWidth() / 2, 
			(int)(c.getHeight() * 0.575)
		);
		hostButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.HOST);
			}
		});
		add(hostButton);
		
		JButton joinButton = new JButton("Join Game");
		joinButton.setSize(150, 35);
		joinButton.setLocation(
			c.getWidth() / 2 - joinButton.getWidth() / 2, 
			(int)(c.getHeight() * 0.650)
		);
		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.JOIN);
			}
		});
		add(joinButton);
		
		JButton logButton = new JButton("View Logged Game");
		logButton.setSize(150, 35);
		logButton.setLocation(
			c.getWidth() / 2 - logButton.getWidth() / 2, 
			(int)(c.getHeight() * 0.725)
		);
		logButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.LOG);
			}
		});
		add(logButton);
		
		JButton quitButton = new JButton("Quit Game");
		quitButton.setSize(150, 35);
		quitButton.setLocation(
			c.getWidth() / 2 - quitButton.getWidth() / 2, 
			(int)(c.getHeight() * 0.800)
		);
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
