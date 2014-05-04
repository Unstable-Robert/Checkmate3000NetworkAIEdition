package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

/**
 * A prototype search algorithm. It's used by the intelligence prototype.
 */
public abstract class SearchAlgorithm implements Runnable{
	protected ExecutorService threadPool;
    protected boolean isFinished;

	protected abstract void search();
	public boolean isFinished() {
		return this.isFinished;
	}
}
