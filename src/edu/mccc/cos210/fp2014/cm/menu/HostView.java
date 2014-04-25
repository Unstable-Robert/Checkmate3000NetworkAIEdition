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
import edu.mccc.cos210.fp2014.cm.util.Difficulty;
import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * Host network game menu.
 * Menu allows user to create settings for a hosted game.
 */
public class HostView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GameType gameType;
	private int time;
	private String address;
    private final int TIME_MIN = 0;
	private final int TIME_MAX = 180;
	private JSpinner timeSpinner;
	private JCheckBox checkbox;
	private JTextField ipTextField;
	public HostView(Checkmate c) {
		super(c);
		
		JLabel titleLabel = new JLabel("HOST GAME");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		titleLabel.setSize(240, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.30)
		);
		add(titleLabel);
		
		
		JLabel timedGame = new JLabel("Timed Game?");
		timedGame.setSize(80,10);
		timedGame.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.45)
		);
		add(timedGame);	
		
		//Checkbox whether game is timed or not
		this.checkbox = new JCheckBox();
		checkbox.setSize(14,13);
		checkbox.setLocation(
			c.getWidth() / 3 + timedGame.getWidth() + 10,
			(int)(c.getHeight() * 0.45)
		);
		checkbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (gameType == GameType.TIMED_GAME){
					gameType = GameType.NORMAL;
				} else {
					gameType = GameType.TIMED_GAME;
				}
				
				timeSpinner.setEnabled(gameType == GameType.TIMED_GAME);
						
			}
		});
		add(checkbox);
		
		//if game is timed on is display timer settings
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setSize(40,20);
		timeLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.50)
		);
		add(timeLabel);
		this.timeSpinner = new JSpinner(new SpinnerNumberModel(TIME_MIN, TIME_MIN, TIME_MAX, 1));
		timeSpinner.setSize(40,20);
		timeSpinner.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2
			+ timeLabel.getWidth() + 20,
			(int)(c.getHeight() * 0.50)
		);
		timeSpinner.setEnabled(false);
		add(timeSpinner);
		JLabel minLabel = new JLabel("Minutes");
		minLabel.setSize(55,20);
		minLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2 
			+ timeLabel.getWidth() + 20 
			+ timeSpinner.getWidth() + 20,
			(int)(c.getHeight() * 0.50)	
		);		
		add(minLabel);

		//displays your ip
		String ipAddress = "UNKNOWN";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e){}
		JLabel ipLabel = new JLabel("Your IP: " + ipAddress);
		ipLabel.setSize(140,40);
		ipLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int) (c.getHeight() * 0.55)
		);
		add(ipLabel);
		
		JLabel enemyIpLabel = new JLabel("Opponent's IP Address:");
		enemyIpLabel.setSize(225, 20);
		enemyIpLabel.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.60)
		);
		add(enemyIpLabel);
		this.ipTextField = new JTextField();
		ipTextField.setSize(250, 20);
		ipTextField.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.65)
		);
		add(ipTextField);

		//backButton returns to previous screen
		JButton backButton = new JButton("Back");
		backButton.setSize(150,50);
		backButton.setLocation(
			c.getWidth() / 3 - timedGame.getWidth() / 2,
			(int)(c.getHeight() * 0.75)
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
			(int)(c.getHeight() * 0.75)
		);
		hostButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
			try {
				setSettings();
				InetAddress inetAddress = InetAddress.getByName(address);
				GameBuilder.buildHostGame(myCheckmate, gameType, time, inetAddress);
				myCheckmate.setView(Checkmate.GAME);
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		});
		add(hostButton);

		
	}
	protected void setSettings() {
		if (this.checkbox.isSelected()){
			this.gameType = GameType.TIMED_GAME;
			this.time = (int)this.timeSpinner.getValue();
		} else {
			this.gameType = GameType.NORMAL;
			this.time = 0;
		}
		this.address = ipTextField.getText();
	}
	/**
	 * Allows user to change settings, start a hosted game, and return to the main menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
