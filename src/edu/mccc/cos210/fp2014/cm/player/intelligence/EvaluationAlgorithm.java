package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

/**
 * 
 */
public abstract class EvaluationAlgorithm implements Runnable {

	protected ExecutorService threadPool;
	
	public abstract void evaluate();

}
