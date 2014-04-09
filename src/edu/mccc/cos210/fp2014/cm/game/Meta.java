package edu.mccc.cos210.fp2014.cm.game;

import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * A list of information relevant to each board, including gametype, time left over,
 * scores (for the AI).
 */
public class Meta {
	private GameType gameType;
	private int p1Time;
	private int p2Time;
	private double whiteScore;
	private double blackScore;
	private int numPossibleMoves;
}
