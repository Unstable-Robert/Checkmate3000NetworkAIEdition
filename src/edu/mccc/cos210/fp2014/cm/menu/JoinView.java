package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.*;

import edu.mccc.cos210.fp2014.cm.game.GameBuilder;

/**
 * Join network game menu.
 * Menu allows user to join a local game.
 */
public class JoinView extends SettingsView {
	private static final long serialVersionUID = 1L;
	private String address;
	private JTextField ipTextField;
	public JoinView(Checkmate c) {
		super(c);
		
		JLabel titleLabel = new JLabel("JOIN GAME");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		titleLabel.setSize(240, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.275)
		);
		add(titleLabel);
					
		JLabel enemyIpLabel = new JLabel("Enter Opponent's IP Address:");
		enemyIpLabel.setForeground(Color.WHITE);
		enemyIpLabel.setSize(170, 20);
		enemyIpLabel.setLocation(
			c.getWidth() / 2 - enemyIpLabel.getWidth() / 2,
			(int)(c.getHeight() * 0.415)
		);
		add(enemyIpLabel);
		this.ipTextField = new JTextField();
		ipTextField.setSize(250, 20);
		ipTextField.setLocation(
			c.getWidth() / 2 - ipTextField.getWidth() / 2,
			(int)(c.getHeight() * 0.455)
		);
		add(ipTextField);
		
		JLabel colorLabel = new JLabel("Guest Plays Black");
		colorLabel.setForeground(Color.WHITE);
		colorLabel.setSize(105, 20);
		colorLabel.setLocation(
				c.getWidth() / 2
				- colorLabel.getWidth()/2,
				 (int) (c.getHeight() * 0.560)
			);
		add(colorLabel);
		//backButton returns to previous screen
		JButton backButton = new JButton("Back");
		backButton.setSize(150,50);
		backButton.setLocation(
			c.getWidth() / 3 - backButton.getWidth() / 2 + 25,
			(int)(c.getHeight() * 0.645)
		);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(backButton);

		//joinButton starts the game with given settings
		JButton joinButton = new JButton("Join Game");
		joinButton.setSize(150,50);
		joinButton.setLocation(
			c.getWidth() * 2 / 3 - joinButton.getWidth() / 2 - 25, 
			(int)(c.getHeight() * 0.645)
		);
		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					setSettings();
					InetAddress inetAddress = InetAddress.getByName(address);
					GameBuilder.buildJoinGame(myCheckmate, inetAddress);
					myCheckmate.setView(Checkmate.GAME);
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		});
		add(joinButton);
	}
	protected void setSettings() {
		this.address = ipTextField.getText();
	}
}
