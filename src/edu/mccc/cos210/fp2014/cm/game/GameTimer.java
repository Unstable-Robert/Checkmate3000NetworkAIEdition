package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observable;

/**
 * Custom TimerEvent used for each player's total game time.
 */
public class GameTimer extends TimerEvent {
	private int ticks2;
	private int maxTicks2;
	private boolean updateTicks2;
	/**
	 * Updates the model.
	 * Makes sure that the time is still the turn of the individual it's timing.
	 * If not, it switches to the other player's time.
	 */
	@Override
	public void update(Observable o, Object arg) {
	}
	/**
	 * Resets the timer.
	 * Switches over from updating ticks to ticks2 in order to keep track of both player's individual time.
	 */
	@Override
	protected void resetTimer() {
	}
	/**
	 * Updates the total number of ticks, and updates the game model when it overflows.
	 */
	@Override
	public void run() {
	}
}
