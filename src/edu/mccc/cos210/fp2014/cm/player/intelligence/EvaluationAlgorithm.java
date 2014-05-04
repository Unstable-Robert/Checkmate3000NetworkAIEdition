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

	protected abstract int evaluate(Tree<Board> tree);
	public boolean isFinished() {
		return this.isFinished;
	}
	public void setRoot(Tree<Board> t){
    	this.tree = t;
    }
	public abstract Board getBest();
	public abstract int getBoardValue(Board b);
}
