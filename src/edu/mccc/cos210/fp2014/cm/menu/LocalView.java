package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.*;

import edu.mccc.cos210.fp2014.cm.game.GameBuilder;
import edu.mccc.cos210.fp2014.cm.util.Difficulty;
import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * Local game menu.
 */
public class LocalView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GameType gameType;
	private int time;
	private Difficulty difficulty1;
	private Difficulty difficulty2;
	private boolean color;
    private final int TIME_MIN = 0;
	private final int TIME_MAX = 180;
	private JSpinner timeSpinner;
	private JCheckBox checkbox;
	private JRadioButton whiteRadio;
	private JComboBox<String> p1;
	private JComboBox<String> p2;
	public LocalView(Checkmate c) {
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

		//startButton starts the game with given settings
		JButton startButton = new JButton("Start Game");
		startButton.setSize(100,50);
		startButton.setLocation((int)(c.getWidth() * 0.78), (int)(c.getHeight() * 0.05));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setSettings();
				GameBuilder.buildLocalGame(myCheckmate, gameType, time, color, difficulty1, difficulty2);
				myCheckmate.setView(Checkmate.GAME);
			}
		});
		add(startButton);
		
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
		
		//dropdown for AIs
		this.p1 = new JComboBox<String>();
		p1.addItem("Human");
		p1.addItem("Easy AI");
		p1.addItem("Medium AI");
		p1.addItem("Hard AI");
		p1.setSize(100, 30);
		p1.setLocation((int)(c.getWidth() * .1), (int) (c.getHeight() * .2));
		add(p1);
		this.p2 = new JComboBox<String>();
		p2.addItem("Human");
		p2.addItem("Easy AI");
		p2.addItem("Medium AI");
		p2.addItem("Hard AI");
		p2.setSize(100, 30);
		p2.setLocation((int)(c.getWidth() * .3), (int) (c.getHeight() * .2));
		add(p2);
		JLabel player1 = new JLabel("Player One");
		player1.setSize(100,40);
		player1.setLocation((int)(c.getWidth() * 0.1),(int)(c.getHeight() * 0.15));
		add(player1);
		JLabel player2 = new JLabel("Player Two");
		player2.setSize(100,40);
		player2.setLocation((int)(c.getWidth() * 0.3),(int)(c.getHeight() * 0.15));
		add(player2);
		
		//if game is timed on is display timer settings
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setSize(40,20);
		timeLabel.setLocation((int)(c.getWidth() * 0.1),(int)(c.getHeight() * 0.42));
		add(timeLabel);
		this.timeSpinner = new JSpinner(new SpinnerNumberModel(TIME_MIN, TIME_MIN, TIME_MAX, 1));
		timeSpinner.setSize(40,20);
		timeSpinner.setLocation((int)(c.getWidth() * 0.18),(int)(c.getHeight() * 0.42));
		add(timeSpinner);
		JLabel minLabel = new JLabel("Minutes");
		minLabel.setSize(55,20);
		minLabel.setLocation((int)(c.getWidth() * 0.3),(int)(c.getHeight() * 0.42));
		add(minLabel);

		//items to pick color
		JLabel color = new JLabel("Player One Color: ");
		color.setSize(100,20);
		color.setLocation((int) (c.getWidth() * 0.1), (int) (c.getHeight() * 0.55));
		add(color);
		ButtonGroup colorPickerButtons = new ButtonGroup();
		this.whiteRadio = new JRadioButton("White");
		whiteRadio.setLocation((int) (c.getWidth() * 0.25), (int) (c.getHeight() * 0.55));
		whiteRadio.setSize(70,20);
		whiteRadio.setSelected(true);
		colorPickerButtons.add(whiteRadio);
		add(whiteRadio);
		JRadioButton blackRadio = new JRadioButton("Black");
		blackRadio.setLocation((int) (c.getWidth() * 0.4), (int) (c.getHeight() * 0.55));
		blackRadio.setSize(70,20);
		colorPickerButtons.add(blackRadio);
		add(blackRadio);
	}
	protected void setSettings() {
		if (this.checkbox.isSelected()){
			this.gameType = GameType.TIMED_GAME;
			this.time = (int)this.timeSpinner.getValue();
		} else {
			this.gameType = GameType.NORMAL;
			this.time = 0;
		}
		if (this.p1.getSelectedItem().equals("Human")){
			this.difficulty1 = Difficulty.HUMAN;
		} else if (this.p1.getSelectedItem().equals("Easy AI")){
			this.difficulty1 = Difficulty.EASY;
		} else if (this.p1.getSelectedItem().equals("Medium AI")){
			this.difficulty1 = Difficulty.MEDIUM;
		} else {
			this.difficulty1 = Difficulty.HARD;
		} 
		if (this.p2.getSelectedItem().equals("Human")){
			this.difficulty2 = Difficulty.HUMAN;
		} else if (this.p2.getSelectedItem().equals("Easy AI")){
			this.difficulty2 = Difficulty.EASY;
		} else if (this.p2.getSelectedItem().equals("Medium AI")){
			this.difficulty2 = Difficulty.MEDIUM;
		} else {
			this.difficulty2 = Difficulty.HARD;
		}
		this.color = this.whiteRadio.isSelected();
	}
	/**
	 * Changes settings and allows user to start the game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
