package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Tree;

/**
 * A prototype evaluation algorithm. It's used by the intelligence prototypes.
 */
public abstract class EvaluationAlgorithm implements Runnable {
	protected ExecutorService threadPool;
	protected boolean isWhite;
    protected boolean isFinished;
    protected Tree<Board> tree;

	protected abstract double evaluate(Tree<Board> tree, boolean maxPlayer);
	
	public boolean isFinished() {
		return this.isFinished;
	}
	public void setRoot(Tree<Board> t){
    	this.tree = t;
    }
	public Board getBest() {
		Board best = null;
		double bestScore = Integer.MIN_VALUE;
		for (Tree<Board> b : this.tree.getLeaves()){
			if (b.getScore() > bestScore){
				best = b.getRoot();
				bestScore = b.getScore();
			}
		}
		return best;
	}	public abstract double getBoardValue(Board b);
}
