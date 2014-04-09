package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Tree;

/**
 * 
 */
public abstract class Intelligence {
	
	protected SearchAlgorithm search;
	protected EvaluationAlgorithm eval;
	protected Tree<Board> possibleBoards;
	protected Board bestBoard;
	protected Board currentBoard;
	protected boolean bestBoardUpToDate;
	
	/**
	 * 
	 */
	public abstract Board getBest();
	/**
	 * 
	 */
	public void setCurrentBoard() {
		
	}
	/**
	 * 
	 */
	public void searchAndEval(){
		
	}
	/**
	 * 
	 */
	protected void trimBoardTree() {
		
	}
}
