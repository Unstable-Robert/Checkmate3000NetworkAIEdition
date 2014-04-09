package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * This class represents the piece centric board of the chess engine.
 * It contains meta information about itself, the position of pieces, and turn info.
 * It also keeps track of information about draw scenarios.
 */
public class Board {

	private ArrayList<PossibleTile> possibleTiles;
	private int movesSincePeiceTaken;
	private Color turn;
	private Meta metaInfo;
	
	/**
	 * This updates the timer that will be painted by GameView
	 * @param i The number of seconds remaining on the clock for the current player's turn
	 */
	public void updateTimer(int i) {
		
	}
	/**
	 * This changes which player is taking their turn.
	 */
	public void nextTurn() {
		
	}
	/**
	 * This restarts the countdown used to enact a draw when there have been 
	 * no taken pieces for 50 turns.
	 */
	public void peiceTaken() {
		
	}
}
