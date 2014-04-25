package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.font.FontRenderContext;
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
	private JLabel timeLabel;
	private JLabel minLabel;
	private JRadioButton whiteRadio;
	private JComboBox<String> p1;
	private JComboBox<String> p2;
	public LocalView(Checkmate c) {
		super(c);

		JLabel titleLabel = new JLabel("LOCAL GAME");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		titleLabel.setSize(275, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.30)
		);
		add(titleLabel);
		
		JLabel player1 = new JLabel("White Player");
		player1.setSize(100,40);
		player1.setLocation(
			c.getWidth() / 3 - player1.getWidth() / 2,
			(int)(c.getHeight() * 0.40)
		);
		add(player1);
		JLabel player2 = new JLabel("Black Player");
		player2.setSize(100,40);
		player2.setLocation(
			c.getWidth() * 2 / 3 - player2.getWidth() / 2,
			(int)(c.getHeight() * 0.40)
		);
		add(player2);		
				
		//dropdown for AIs
		this.p1 = new JComboBox<String>();
		p1.addItem("Human");
		p1.addItem("Easy AI");
		p1.addItem("Medium AI");
		p1.addItem("Hard AI");
		p1.setSize(100, 30);
		p1.setLocation(
			c.getWidth() / 3 - player1.getWidth() / 2,
			(int)(c.getHeight() * 0.45)
		);
		add(p1);
		this.p2 = new JComboBox<String>();
		p2.addItem("Human");
		p2.addItem("Easy AI");
		p2.addItem("Medium AI");
		p2.addItem("Hard AI");
		p2.setSize(100, 30);
		p2.setLocation(
			c.getWidth() * 2 / 3 - player2.getWidth() / 2,
			(int)(c.getHeight() * 0.45)
		);
		add(p2);
		
		JLabel timedGame = new JLabel("Timed Game?");
		timedGame.setSize(80,10);
		timedGame.setLocation(
			c.getWidth() / 3 - player1.getWidth() / 2,
			(int)(c.getHeight() * 0.55)
		);
		add(timedGame);	
		
		//Checkbox whether game is timed or not
		this.checkbox = new JCheckBox();
		checkbox.setSize(14,13);
		checkbox.setLocation(
			c.getWidth() / 3 + timedGame.getWidth() + 10,
			(int)(c.getHeight() * 0.55)
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
			c.getWidth() / 3 - player1.getWidth() / 2,
			(int)(c.getHeight() * 0.65)
		);
		add(timeLabel);
		this.timeSpinner = new JSpinner(new SpinnerNumberModel(TIME_MIN, TIME_MIN, TIME_MAX, 1));
		timeSpinner.setSize(40,20);
		timeSpinner.setLocation(
			c.getWidth() / 3 - player1.getWidth() / 2
			+ timeLabel.getWidth() + 20,
			(int)(c.getHeight() * 0.65)
		);
		timeSpinner.setEnabled(false);
		add(timeSpinner);
		JLabel minLabel = new JLabel("Minutes");
		minLabel.setSize(55,20);
		minLabel.setLocation(
			c.getWidth() / 3 - player1.getWidth() / 2 
			+ timeLabel.getWidth() + 20 
			+ timeSpinner.getWidth() + 20,
			(int)(c.getHeight() * 0.65)	
		);		
		add(minLabel);
		
		//backButton returns to previous screen
		JButton backButton = new JButton("Back");
		backButton.setSize(150,50);
		backButton.setLocation(
			c.getWidth() / 3 - player1.getWidth() / 2,
			(int)(c.getHeight() * 0.75)
		);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(backButton);

		//startButton starts the game with given settings
		JButton startButton = new JButton("Start Game");
		startButton.setSize(150,50);
		startButton.setLocation(
			c.getWidth() * 2 / 3 - player2.getWidth() / 2
			+ p2.getWidth()
			- startButton.getWidth(), 
			(int)(c.getHeight() * 0.75)
		);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setSettings();
				GameBuilder.buildLocalGame(myCheckmate, gameType, time, difficulty1, difficulty2);
				myCheckmate.setView(Checkmate.GAME);
			}
		});
		add(startButton);
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
	}
	/**
	 * Changes settings and allows user to start the game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
