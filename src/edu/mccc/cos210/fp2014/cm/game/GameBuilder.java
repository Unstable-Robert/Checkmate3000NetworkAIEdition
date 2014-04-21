package edu.mccc.cos210.fp2014.cm.game;

import java.net.InetAddress;

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
		
	}
	/**
	 * Builds a local game for one human player and one AI player.	 
	 * @param g The gametype of the game
	 * @param d The difficulty of the AI.
	 * @param t The time, if a timed game.
	 */
	public static void buildAiGame(GameType g, Difficulty d, int t) {
	}
	/**
	 * Builds a local game for two AI players.
	 * @param g The gametype of the game
	 * @param d1 The difficulty of the AI.
	 * @param d2 The difficulty of the AI.
	 * @param t The time, if a timed game.
	 */
	public static void buildDoublyAiGame(GameType g, Difficulty d1, Difficulty d2, int t) {
	}
	/**
	 * Builds a networked game for the host player.
	 * @param g The gametype of the game
	 * @param t The time, if a timed game.
	 */
	public static void buildHostGame(GameType g, int t) {
	}
	/**
	 * Builds a networked game for the joining player.
	 * @param a The internet address of the host
	 * @param t The time, if a timed game.
	 */
	public static void buildJoinGame(InetAddress a, int t) {
	}
}
