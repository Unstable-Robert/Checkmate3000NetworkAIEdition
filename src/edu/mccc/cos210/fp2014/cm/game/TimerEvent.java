package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Responsible for maintaining the timer and what keeping track of turns.
 */
public abstract class TimerEvent extends TimerTask implements Observer {
	protected GameModel gm;
	protected Timer timer;
	protected int ticks;
	protected int maxTicks;
	protected boolean color;
	protected abstract void resetTimer();
}
