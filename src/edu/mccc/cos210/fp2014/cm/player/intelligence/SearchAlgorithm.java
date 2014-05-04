package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Tree;

/**
 * A prototype search algorithm. It's used by the intelligence prototype.
 */
public abstract class SearchAlgorithm implements Runnable{
	protected ExecutorService threadPool;
    protected boolean isFinished;
    protected Tree<Board> tree;

	protected abstract void search();
	public boolean isFinished() {
		return this.isFinished;
	}
    public void setRoot(Tree<Board> t){
    	this.tree = t;
    }
    public Tree<Board> getTree(){
    	return this.tree;
    }
}
