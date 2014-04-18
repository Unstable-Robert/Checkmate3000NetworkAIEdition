package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

/**
 * A prototype evaluation algorithm. It's used by the intelligence prototypes.
 */
public abstract class EvaluationAlgorithm implements Runnable {
	protected ExecutorService threadPool;
	protected abstract void evaluate();
}
