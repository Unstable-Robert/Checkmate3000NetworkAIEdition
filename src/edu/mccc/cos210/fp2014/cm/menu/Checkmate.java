package edu.mccc.cos210.fp2014.cm.menu;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.game.GameView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Runner class and main JFrame.
 */
public class Checkmate extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	public static final String MAIN_MENU = "main menu";
	public static final String LOCAL = "local";
	public static final String HOST = "host";
	public static final String JOIN = "join";
	public static final String GAME = "game";
	public static final String GAMEOVER = "game over";
	public static final String DRAW = "draw";
	private CardLayout cards;
	private JPanel cardPanel;
	private GameModel gm;
	private Checkmate() {
		super("Checkmate 3000 Network AI Edition");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800, 640);
		setResizable(false);
		cards = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cards);
		cardPanel.add(new MainMenuView(this), MAIN_MENU);
		cardPanel.add(new LocalView(this), LOCAL);
		cardPanel.add(new HostView(this), HOST);
		cardPanel.add(new JoinView(this), JOIN);
		cardPanel.add(new DrawView(this), DRAW);
		//cardPanel.add(new GameOverView(this), GAMEOVER);
		//cardPanel.add(new GameView(this, new GameModel()), GAME);
		add(cardPanel);
		cards.show(cardPanel, MAIN_MENU);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int wantsExit = JOptionPane.showConfirmDialog(
					Checkmate.this,
					"Are you sure you want to exit the program?",
					"Exit Program?",
					JOptionPane.YES_NO_OPTION
				);
				if (wantsExit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setVisible(true);
	}
	public static void main(String[] sa) {
		new Checkmate();
	}
	/**
	 * Set what JPanel you want to see.
	 */
	public void setView(String v) {
		cards.show(cardPanel, v);
	}
	public void setGameView(GameView gv) {
		cardPanel.add(gv, GAME);
	}
	public void setGameModel(GameModel gm) {
		this.gm = gm;
	}
	public GameModel getGameModel() {
		return gm;
	}
	public void endGame(boolean whiteWon) {
		cardPanel.add(new GameOverView(whiteWon, this, gm.getBoard().getMoves()), GAMEOVER);
		cards.show(cardPanel, GAMEOVER);
	}
	@Override
	public void update(Observable o, Object arg) {
		if (this.gm.isCheckMate()){
			boolean isWhiteTurn = this.getGameModel().getBoard().isWhiteTurn();
			endGame(isWhiteTurn);
		}
	}
}
