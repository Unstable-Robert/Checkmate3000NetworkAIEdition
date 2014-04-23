package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.player.intelligence.Intelligence;
import edu.mccc.cos210.fp2014.cm.util.Difficulty;
import edu.mccc.cos210.fp2014.cm.util.GamePart;

/**
 * Artificial intelligent chess player.
 * It contains an intelligence object which can be defined be the point in the game
 * (beginning, middle, or end) which handles all of the search and evaluation.
 */
public class AiPlayer extends Player implements Runnable{
	private Intelligence intelligence;
	private GamePart gamePart;
	private GameModel gm;
	private Difficulty difficulty;
	public AiPlayer(GameModel gm, boolean b, Difficulty d) {
		super(gm, b);
		this.difficulty = d;
	}
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
	public boolean updateModel(Piece piece, PossibleTile pt){
		return false;
	}
	/**
	 * Updates the intelligence.
	 * This is called when the model changes. It updates the intelligence's current board,
	 * if it differs from the current one.
	 */
	@Override
	public void update(Observable o, Object arg) {
	}
	/**
	 * Called when this object is created in order to start the search-eval loop.
	 */
	@Override
	public void run() {
	}
}
