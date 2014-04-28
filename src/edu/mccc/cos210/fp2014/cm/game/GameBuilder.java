package edu.mccc.cos210.fp2014.cm.game;

import java.io.IOException;
import java.net.InetAddress;

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
			LocalPlayer lp1 = new LocalPlayer(gm, true);
			LocalPlayer lp2 = new LocalPlayer(gm, false);
			c.setGameView(setupGameView(c, gm, lp1, lp2));
		} else if (d1 == Difficulty.HUMAN && d2 != Difficulty.HUMAN){
			LocalPlayer lp = new LocalPlayer(gm, true);
			AiPlayer aip = new AiPlayer(gm, false, d2);
			c.setGameView(setupGameView(c, gm, lp, aip));
		} else if (d1 != Difficulty.HUMAN && d2 == Difficulty.HUMAN){
			LocalPlayer lp = new LocalPlayer(gm, false);
			AiPlayer aip = new AiPlayer(gm, true, d1);
			c.setGameView(setupGameView(c, gm, lp, aip));
		}else {
			AiPlayer aip1 = new AiPlayer(gm, true, d1);
			AiPlayer aip2 = new AiPlayer(gm, false, d2);
			c.setGameView(setupGameView(c, gm, aip1, aip2));
		}
	}
	/**
	 * Builds a networked game for the host player.
	 * @param g The gametype of the game
	 * @param t The time, if a timed game.
	 */
	public static void buildHostGame(Checkmate c, GameType g, int t, InetAddress a) {
		GameModel gm = setupGameType(g,t);
		c.setGameModel(gm);
		gm.addObserver(c);
		NetworkPlayer np;
		try {
			np = NetworkPlayer.GetHostNetwork(gm, a);
			c.setGameView(setupGameView(c, gm, np));
			new Thread(np).start();
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
			np = NetworkPlayer.GetJoinNetwork(gm, a);
			c.setGameView(setupGameView(c, gm, np));
			new Thread(np).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static GameModel setupGameType(GameType g, int t) {
		GameModel gm;
		Board b;
		if (g.equals(GameType.TIMED_GAME)){
			b = new Board(g, t);
			GameTimer gt = new GameTimer();
			gm = new GameModel(t, gt, b);
			gm.addObserver(gt);
		} else {
			b = new Board(g);
			gm = new GameModel(b);
		}
		gm.updateBoard(b);
		return gm;
	}
	private static GameModel setupGameType(){
		Board b = new Board();
		GameModel gm = new GameModel(b);
		gm.updateBoard(b);
		return gm;
	}
	private static GameView setupGameView(Checkmate c, GameModel gm, Player p1, Player p2){
		GameView gv = new GameView(c, gm);
		gm.addObserver(gv);
		gv.addPlayer(p1);
		gv.addPlayer(p2);
		return gv;
	}
	private static GameView setupGameView(Checkmate c, GameModel gm, Player p1){
		GameView gv = new GameView(c, gm);
		gm.addObserver(gv);
		gv.addPlayer(p1);
		return gv;
	}
}
