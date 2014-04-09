package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * 
 */
public abstract class TimerEvent extends TimerTask implements Observer, Runnable {

	protected GameModel gm;
	protected Timer timer;
	protected int ticks;
	protected int maxTicks;
	protected Color color;
	
	/**
	 * 
	 */
	protected abstract void resetTimer();

}
