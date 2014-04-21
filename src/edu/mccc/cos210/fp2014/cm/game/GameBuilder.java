package edu.mccc.cos210.fp2014.cm.game;

import java.net.InetAddress;

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
	public static void buildLocalGame(GameType g, int t) {
		GameModel gm = setupGameType(g,t);
		LocalPlayer lp1 = new LocalPlayer(gm, true);
		LocalPlayer lp2 = new LocalPlayer(gm, false);
		GameView gv = setupGameView(gm, lp1, lp2);
		gm.addObserver(lp1);
		gm.addObserver(lp2);
	}
	/**
	 * Builds a local game for one human player and one AI player.	 
	 * @param g The gametype of the game
	 * @param d The difficulty of the AI.
	 * @param t The time, if a timed game.
	 */
	public static void buildAiGame(GameType g, Difficulty d, int t, boolean localColor) {
		GameModel gm = setupGameType(g,t);
		LocalPlayer lp = new LocalPlayer(gm, localColor);
		AiPlayer aip = new AiPlayer(gm, !localColor, d);
		GameView gv = setupGameView(gm, lp, aip);
		gm.addObserver(lp);
		gm.addObserver(aip);
	}
	/**
	 * Builds a local game for two AI players.
	 * @param g The gametype of the game
	 * @param d1 The difficulty of the AI.
	 * @param d2 The difficulty of the AI.
	 * @param t The time, if a timed game.
	 */
	public static void buildDoublyAiGame(GameType g, Difficulty d1, Difficulty d2, int t) {
		GameModel gm = setupGameType(g,t);
		AiPlayer aip1 = new AiPlayer(gm, true, d1);
		AiPlayer aip2 = new AiPlayer(gm, false, d2);
		setupGameView(gm, aip1, aip2);
		gm.addObserver(aip1);
		gm.addObserver(aip2);
	}
	/**
	 * Builds a networked game for the host player.
	 * @param g The gametype of the game
	 * @param t The time, if a timed game.
	 */
	public static void buildHostGame(GameType g, int t) {
		GameModel gm = setupGameType(g,t);
		NetworkPlayer np = new NetworkPlayer(gm, true);
		setupGameView(gm, np);
		gm.addObserver(np);
	}
	/**
	 * Builds a networked game for the joining player.
	 * @param a The internet address of the host
	 * @param t The time, if a timed game.
	 */
	public static void buildJoinGame(InetAddress a) {
		GameModel gm = setupGameType(null, 0);
		NetworkPlayer np = new NetworkPlayer(a);
		setupGameView(gm,np);
		gm.addObserver(np);
	}
	private static GameModel setupGameType(GameType g, int t) {
		GameModel gm;
		Board b;
		if (g.equals(GameType.TIMED_GAME)){
			GameTimer gt = new GameTimer();
			gm = new GameModel(t, gt);
			gm.addObserver(gt);
			b = new Board(g, t);
		} else if (g.equals(GameType.TIMED_TURN)){
			TurnTimer tt = new TurnTimer();
			gm = new GameModel(t, tt);
			gm.addObserver(tt);
			b = new Board(g, t);
		} else {
			gm = new GameModel();
			b = new Board(g);
		}
		gm.updateBoard(b);
		return gm;
	}
	private static GameView setupGameView(GameModel gm, Player p1, Player p2){
		GameView gv = new GameView();
		gm.addObserver(gv);
		gv.addPlayer(p1);
		gv.addPlayer(p2);
		return gv;
	}
	private static GameView setupGameView(GameModel gm, Player p1){
		GameView gv = new GameView();
		gm.addObserver(gv);
		gv.addPlayer(p1);
		return gv;
	}
}
