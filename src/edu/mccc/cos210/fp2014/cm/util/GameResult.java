package edu.mccc.cos210.fp2014.cm.util;

import java.util.EnumSet;

/**
 * An enumeration representing GameResult WhiteWon, BlackWon, DrawGame, GameRunning
 */
public enum GameResult {
	WhiteWon(1), BlackWon(2), DrawGame(3), GameRunning(4);
	private final int value;
	private static final EnumSet<GameResult> list = EnumSet.of(WhiteWon, BlackWon, DrawGame, GameRunning);
	private GameResult(int val) {
		this.value = val;
	}
	public int getValue() {
		return value;
	}
	public static GameResult fromInt(int val) {
		for (GameResult gr : list){
			if (gr.getValue() == val){
				return gr;
			}
		}
		return null;
	}
}
