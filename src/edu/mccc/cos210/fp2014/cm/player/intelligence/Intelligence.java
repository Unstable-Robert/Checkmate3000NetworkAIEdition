package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.EvaluationAlgorithm;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.SearchAlgorithm;
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
	protected Board currentBoard;
	protected boolean isWhite;
	protected int depth;
	/**
	 * Gets the best board given the search and eval classes.
	 */
	protected Intelligence(int n, boolean iw){
		this.depth = n;
		this.isWhite = iw;
	}	
	public Board getBest() {
		return this.eval.getBest();
	}
	public void setCurrentBoard(Board b) {
		this.currentBoard = b;
		Tree<Board> t = new Tree<Board>(this.currentBoard);
		this.search.setRoot(t);
		this.eval.setRoot(t);
	}
	/**
	 * This method searches and evaluates the current board in order to determine the best move.
	 * After doing so, it makes sure that the current board is up to date and either:
	 * sets the best board 
	 * or trims the tree.
	 * It then loops.
	 */
	public void searchAndEval(Board b){
		Tree<Board> t = new Tree<Board>(b);
		this.search.search(t);
		this.eval.setRoot(t);
		this.eval.evaluate(t, true);
	}
	/**
	 * When a user makes a move, the possibilities that did not happen need to be removed.
	 * This does so.
	 */
	protected void trimBoardTree(Board b) {
		possibleBoards.trimTree(b);
	}
}
