package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;

import edu.mccc.cos210.fp2014.cm.game.GameBuilder;
import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * Join network game menu.
 * Menu allows user to join a local game.
 */
public class JoinView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String address;
	private JTextField ipTextField;
	public JoinView(Checkmate c) {
		super(c);
		
		JLabel titleLabel = new JLabel("JOIN GAME");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		titleLabel.setSize(240, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.30)
		);
		add(titleLabel);
					
		JLabel enemyIpLabel = new JLabel("Opponent's IP Address:");
		enemyIpLabel.setSize(140, 20);
		enemyIpLabel.setLocation(
			c.getWidth() / 2 - enemyIpLabel.getWidth() / 2,
			(int)(c.getHeight() * 0.60)
		);
		add(enemyIpLabel);
		this.ipTextField = new JTextField();
		ipTextField.setSize(250, 20);
		ipTextField.setLocation(
			c.getWidth() / 2 - ipTextField.getWidth() / 2,
			(int)(c.getHeight() * 0.65)
		);
		add(ipTextField);
		
		//displays your ip
		String ipAddress = "UNKNOWN";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e){}
		JLabel ipLabel = new JLabel("Your IP: " + ipAddress);
		ipLabel.setSize(140,40);
		ipLabel.setLocation(
			c.getWidth() / 2 
			- ipLabel.getWidth()/2,
			 (int) (c.getHeight() * 0.55)
		);
		add(ipLabel);
		
		//backButton returns to previous screen
		JButton backButton = new JButton("Back");
		backButton.setSize(150,50);
		backButton.setLocation(
			c.getWidth() / 3 - backButton.getWidth() / 2,
			(int)(c.getHeight() * 0.75)
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
			c.getWidth() * 2 / 3 - joinButton.getWidth() / 2
			+ 100
			- joinButton.getWidth(), 
			(int)(c.getHeight() * 0.75)
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
	/**
	 * Allows user to enter the host's IP and return to the main menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
