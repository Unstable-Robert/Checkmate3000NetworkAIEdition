package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
				
		//backButton returns to previous screen
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

		//hostButton starts the game with given settings
		JButton hostButton = new JButton("Host Game");
		hostButton.setSize(100,50);
		hostButton.setLocation((int)(c.getWidth() * 0.78), (int)(c.getHeight() * 0.05));
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
		
		//Checkbox whether game is timed or not
		this.checkbox = new JCheckBox();
		checkbox.setSize(100,40);
		checkbox.setLocation((int) (c.getWidth() * 0.28), (int) (c.getHeight() * 0.25));
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
		JLabel timedGame = new JLabel("Timed Game?");
		timedGame.setSize(100,40);
		timedGame.setLocation((int)(c.getWidth() * 0.1),(int)(c.getHeight() * 0.25));
		add(timedGame);
		
		//if game is timed on is display timer settings
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setSize(40,20);
		timeLabel.setLocation((int)(c.getWidth() * 0.1),(int)(c.getHeight() * 0.42));
		add(timeLabel);
		timeSpinner = new JSpinner(new SpinnerNumberModel(TIME_MIN, TIME_MIN, TIME_MAX, 1));
		timeSpinner.setSize(40,20);
		timeSpinner.setLocation((int)(c.getWidth() * 0.18),(int)(c.getHeight() * 0.42));
		timeSpinner.setEnabled(false);
		add(timeSpinner);
		JLabel minLabel = new JLabel("Minutes");
		minLabel.setSize(55,20);
		minLabel.setLocation((int)(c.getWidth() * 0.3),(int)(c.getHeight() * 0.42));
		add(minLabel);

		//displays your ip
		String ipAddress = "UNKNOWN";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e){}
		JLabel ipLabel = new JLabel("Your IP: " + ipAddress);
		ipLabel.setSize(140,40);
		ipLabel.setLocation((int)((c.getWidth() * 0.5) - ipLabel.getWidth()/2), (int) (c.getHeight() * 0.75));
		add(ipLabel);
		
		JLabel enemyIpLabel = new JLabel("Opponent's IP Address:");
		enemyIpLabel.setSize(250, 20);
		enemyIpLabel.setLocation((int)(c.getWidth() * 0.25),(int)(c.getHeight() * 0.63));
		add(enemyIpLabel);
		this.ipTextField = new JTextField();
		ipTextField.setSize(250, 20);
		ipTextField.setLocation((int)(c.getWidth() * 0.35),(int)(c.getHeight() * 0.68));
		add(ipTextField);
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
