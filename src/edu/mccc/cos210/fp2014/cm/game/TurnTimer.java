package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observable;

/**
 * The TimerEvent used when players track their turn time.
 */
public class TurnTimer extends TimerEvent {
	/**
	 * When the model updates, it makes sure that it's still the turn of the individual it's timing.
	 * If not, it switches to the other player's time.
	 */
	@Override
	public void update(Observable o, Object arg) {
	}
	/**
	 * Sets the timer back to zero.
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
