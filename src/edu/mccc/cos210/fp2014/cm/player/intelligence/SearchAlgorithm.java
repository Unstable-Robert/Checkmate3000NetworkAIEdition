package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

/**
 * 
 */
public abstract class SearchAlgorithm implements Runnable{
	
	protected static ExecutorService threadPool;
	
	public abstract void search();
	
}
