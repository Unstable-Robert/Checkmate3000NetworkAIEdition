package edu.mccc.cos210.fp2014.cm.menu;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.game.GameView;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
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
	private CardLayout cards;
	private JPanel cardPanel;
	private GameModel gm;
	private Checkmate() {
		super("Checkmate 3000 Network AI Edition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 640);
		setResizable(false);
		cards = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cards);
		cardPanel.add(new MainMenuView(this), MAIN_MENU);
		cardPanel.add(new LocalView(this), LOCAL);
		cardPanel.add(new HostView(this), HOST);
		cardPanel.add(new JoinView(this), JOIN);
		cardPanel.add(new GameView(this), GAME);
		add(cardPanel);
		cards.show(cardPanel, MAIN_MENU);
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
	@Override
	public void update(Observable o, Object arg) {
		if (this.gm.isCheckMate()){
			//setGameView (new GameOverView(!gm.getBoard().isWhiteTurn()));
		}
	}
}
