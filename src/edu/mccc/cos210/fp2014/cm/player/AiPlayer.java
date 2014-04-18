package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.player.intelligence.Intelligence;
import edu.mccc.cos210.fp2014.cm.util.GamePart;

/**
 * This is an artificially intelligent chess playa.
 * It contains an intelligence object which can be defined be the point in the game
 * (beginning, middle, or end) which handles all of the search and evaluation.
 */
public class AiPlayer extends Player implements Runnable{
	private Intelligence intelligence;
	private GamePart gamePart;
	private GameModel gm;
	
	/**
	 * This takes a board parameter and returns the best move (as determined by the intelligence module).
	 * @param b The board to search for the best move
	 * @return The board with the new best move.
	 */
	public Board getMove(Board b) {
		return null;
	}
	/**
	 * Updates the game model with it's move.
	 */
	@Override
	public void updateModel(Piece p) {
		// TODO Auto-generated method stub

	}
	/**
	 * This is called when the model changes. It updates the intelligence's current board,
	 * if it differs from the current one.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Called when this object is created in order to start the search-eval loop.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
