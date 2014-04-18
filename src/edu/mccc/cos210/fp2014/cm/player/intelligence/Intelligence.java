package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Tree;

/**
 * This prototype class provides the required attributes and methods for game intelligence.
 * Depending on which attributes (especially the search and evaluation classes) are plugged in,
 * it will determine the best board differently.
 * This will be useful to differentiate beginning, middle, and late game strategies.
 */
public abstract class Intelligence {
	protected SearchAlgorithm search;
	protected EvaluationAlgorithm eval;
	protected Tree<Board> possibleBoards;
	protected Board bestBoard;
	protected Board currentBoard;
	protected boolean bestBoardUpToDate;
	/**
	 * Gets the best board given the search and eval classes.
	 */
	public abstract Board getBest();
	public void setCurrentBoard() {
	}
	/**
	 * This method searches and evaluates the current board in order to determine the best move.
	 * After doing so, it makes sure that the current board is up to date and either:
	 * sets the best board 
	 * or trims the tree.
	 * It then loops.
	 */
	public void searchAndEval(){
	}
	/**
	 * When a user makes a move, the possibilities that did not happen need to be removed.
	 * This does so.
	 */
	protected void trimBoardTree(Board b) {
	}
}
