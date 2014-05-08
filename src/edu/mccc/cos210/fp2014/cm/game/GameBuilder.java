package edu.mccc.cos210.fp2014.cm.game;

import java.awt.GridLayout;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.player.*;
import edu.mccc.cos210.fp2014.cm.util.Difficulty;
import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * Builds a game given different options.
 * This class is called by the various menus and is used to build and wire all of the components 
 * for the chess engine.
 */
public class GameBuilder {
	/**
	 * Builds a local game for two human players.
	 * @param g The gametype of the game
	 * @param t The time, if a timed game.
	 */
	public static void buildLocalGame(Checkmate c, GameType g, int t, Difficulty d1, Difficulty d2) {
		GameModel gm = setupGameType(g,t);
		c.setGameModel(gm);
		gm.addObserver(c);
		if (d1 == Difficulty.HUMAN && d2 == Difficulty.HUMAN) {
			LocalPlayer lp1 = new LocalPlayer(gm, c, true);
			LocalPlayer lp2 = new LocalPlayer(gm, c, false);
			c.setGameView(setupGameView(c, gm, lp1, lp2));
		} else if (d1 == Difficulty.HUMAN && d2 != Difficulty.HUMAN){
			LocalPlayer lp = new LocalPlayer(gm, c, true);
			AiPlayer aip = new AiPlayer(gm, c, false, d2);
			c.setGameView(setupGameView(c, gm, lp, aip));
		} else if (d1 != Difficulty.HUMAN && d2 == Difficulty.HUMAN){
			LocalPlayer lp = new LocalPlayer(gm, c, false);
			AiPlayer aip = new AiPlayer(gm, c, true, d1);
			c.setGameView(setupGameView(c, gm, lp, aip));
			aip.update(null, null);
		}else {
			AiPlayer aip1 = new AiPlayer(gm, c, true, d1);
			AiPlayer aip2 = new AiPlayer(gm, c, false, d2);
			c.setGameView(setupGameView(c, gm, aip1, aip2));
			aip1.update(null, null);
		}
		gm.startTimer();
	}
	/**
	 * Builds a networked game for the host player.
	 * @param g The gametype of the game
	 * @param t The time, if a timed game.
	 */
	public static void buildHostGame(Checkmate c, GameType g, int t) {
		GameModel gm = setupGameType(g,t);
		c.setGameModel(gm);
		gm.addObserver(c);
		NetworkPlayer np;
		try {
			np = NetworkPlayer.GetHostNetwork(gm, c);
			c.setGameView(setupGameView(c, gm, np));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Builds a networked game for the joining player.
	 * @param a The internet address of the host
	 * @param t The time, if a timed game.
	 */
	public static void buildJoinGame(Checkmate c, InetAddress a) {
		GameModel gm = setupGameType();
		c.setGameModel(gm);
		gm.addObserver(c);
		NetworkPlayer np;
		try {
			np = NetworkPlayer.GetJoinNetwork(gm, c, a);
			c.setGameView(setupGameView(c, gm, np));
		} catch (IOException e) {
			JPanel panel = new JPanel(new GridLayout(2, 1));
			JLabel label = new JLabel("It looks like your host isn't ready, please wait.");
			String[] options = new String[]{"OK"};
			panel.add(label);
			JOptionPane.showOptionDialog(null, panel, "Waiting for Another Player",
						  JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
						  null, options, options[0]);
		}
	}
	private static GameModel setupGameType(GameType g, int t) {
		GameModel gm;
		Board b;
		if (g.equals(GameType.TIMED_GAME)){
			b = new Board(g, t);
			gm = new GameModel(t, true, b);
		} else {
			b = new Board(g);
			gm = new GameModel(b);
		}
		gm.updateBoard(b, false);
		return gm;
	}
	private static GameModel setupGameType(){
		Board b = new Board();
		GameModel gm = new GameModel(b);
		gm.updateBoard(b, false);
		return gm;
	}
	private static GameView setupGameView(Checkmate c, GameModel gm, Player p1, Player p2){
		GameView gv = new GameView(c, gm, false);
		gm.addObserver(gv);
		gm.addObserver(p1);
		gm.addObserver(p2);
		gv.addPlayer(p1);
		gv.addPlayer(p2);
		return gv;
	}
	private static GameView setupGameView(Checkmate c, GameModel gm, Player p1){
		GameView gv = new GameView(c, gm, true);
		gm.addObserver(gv);
		gm.addObserver(p1);
		gv.addPlayer(p1);
		return gv;
	}
}
