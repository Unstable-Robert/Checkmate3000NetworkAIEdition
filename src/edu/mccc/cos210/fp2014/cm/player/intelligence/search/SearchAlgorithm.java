package edu.mccc.cos210.fp2014.cm.player.intelligence.search;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Tree;

/**
 * A prototype search algorithm. It's used by the intelligence prototype.
 */
public abstract class SearchAlgorithm implements Runnable {
	protected ExecutorService threadPool;
	protected boolean isFinished;
	protected Tree<Board> tree;
	protected int maxDepth;
	protected SearchAlgorithm(Board t, int md) {
		this.tree = new Tree<Board>(t);
		this.maxDepth = md;
		this.threadPool = Executors.newFixedThreadPool(10);
		this.isFinished = false;
	}
	protected abstract void search(int depth, Tree<Board> b);
	public abstract void search(Tree<Board> b);
	public boolean isFinished() {
		return this.isFinished;
	}
	public void setRoot(Tree<Board> t) {
		this.tree = t;
	}
	public Tree<Board> getTree() {
		return this.tree;
	}
}
