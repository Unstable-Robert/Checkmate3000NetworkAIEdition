package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.*;

import edu.mccc.cos210.fp2014.cm.game.GameBuilder;
import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * Host network game menu.
 * Menu allows user to create settings for a hosted game.
 */
public class HostView extends SettingsView {
	private static final long serialVersionUID = 1L;
	private GameType gameType;
	private int time;
	private final int TIME_MIN = 1;
	private final int TIME_MAX = 180;
	private JSpinner timeSpinner;
	private JCheckBox checkbox;
	public HostView(Checkmate c) {
		super(c);
		
		JLabel titleLabel = new JLabel("HOST GAME");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		titleLabel.setSize(240, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.225)
		);
		add(titleLabel);
		
		
		JLabel timedGame = new JLabel("Timed Game?");
		timedGame.setForeground(Color.WHITE);
		timedGame.setSize(100,10);
		timedGame.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.350)
		);
		add(timedGame);	
		
		// Checkbox for whether the game is timed or not.
		this.checkbox = new JCheckBox("", true);
		checkbox.setBackground(new Color(10,10,10,0));
		checkbox.setSize(24,15);
		checkbox.setLocation(
			c.getWidth() / 3 + timedGame.getWidth() + 10,
			(int)(c.getHeight() * 0.350)
		);
		checkbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (gameType == GameType.NORMAL) {
					gameType = GameType.TIMED_GAME;
				} else {
					gameType = GameType.NORMAL;
				}
				timeSpinner.setEnabled(gameType == GameType.TIMED_GAME);	
			}
		});
		add(checkbox);
		
		//if game is timed on is display timer settings
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setSize(40,20);
		timeLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.425)
		);
		add(timeLabel);
		
		this.timeSpinner = new JSpinner(new SpinnerNumberModel(5, TIME_MIN, TIME_MAX, 1));
		timeSpinner.setSize(40,20);
		timeSpinner.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2
			+ timeLabel.getWidth() + 20,
			(int)(c.getHeight() * 0.425)
		);
		add(timeSpinner);
		
		JLabel minLabel = new JLabel("Minutes");
		minLabel.setForeground(Color.WHITE);
		minLabel.setSize(55,20);
		minLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2 
			+ timeLabel.getWidth() + 20 
			+ timeSpinner.getWidth() + 20,
			(int)(c.getHeight() * 0.425)	
		);		
		add(minLabel);

		// Displays your IP address.
		String ipAddress = "UNKNOWN";
		try {
			URL ip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(
				new InputStreamReader(ip.openStream())
			);
			ipAddress = in.readLine();
		} catch (IOException ex){
		}
		JLabel ipLabel = new JLabel("Your IP: " + ipAddress);
		ipLabel.setSize(160,40);
		ipLabel.setForeground(Color.WHITE);
		ipLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int) (c.getHeight() * 0.500)
		);
		add(ipLabel);
		
		JLabel colorLabel = new JLabel("Host Plays White");
		colorLabel.setForeground(Color.WHITE);
		colorLabel.setSize(200, 20);
		colorLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.600)
		);
		add(colorLabel);
		
		// Clicking backButton returns you to main menu.
		JButton backButton = new JButton("Back");
		backButton.setSize(150,50);
		backButton.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.675)
		);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(backButton);

		//hostButton starts the game with given settings
		JButton hostButton = new JButton("Start Game");
		hostButton.setSize(150,50);
		hostButton.setLocation(
			c.getWidth() * 2 / 3 - timedGame.getWidth() / 2
			+ 100
			- hostButton.getWidth(), 
			(int)(c.getHeight() * 0.675)
		);
		hostButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setSettings();
				GameBuilder.buildHostGame(myCheckmate, gameType, time);
				myCheckmate.setView(Checkmate.GAME);
			}
		});
		add(hostButton);

		
	}
	protected void setSettings() {
		if (this.checkbox.isSelected()) {
			this.gameType = GameType.TIMED_GAME;
			this.time = (int)this.timeSpinner.getValue();
		} else {
			this.gameType = GameType.NORMAL;
			this.time = 0;
		}
	}
}
