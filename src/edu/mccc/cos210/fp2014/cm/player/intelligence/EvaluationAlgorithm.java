package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * A prototype evaluation algorithm. It's used by the intelligence prototypes.
 */
public abstract class EvaluationAlgorithm implements Runnable {
	protected ExecutorService threadPool;
    protected boolean isFinished;

	protected abstract void evaluate();
	public boolean isFinished() {
		return this.isFinished;
	}
	public abstract Board getBest();
}
