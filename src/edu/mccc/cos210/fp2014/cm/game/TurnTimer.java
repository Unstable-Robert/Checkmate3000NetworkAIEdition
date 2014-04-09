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
		// TODO Auto-generated method stub

	}
	/**
	 * Resets the timer back to zero.
	 */
	@Override
	protected void resetTimer() {
		// TODO Auto-generated method stub

	}
	/**
	 * Updates the total number of ticks, and then when it overflows, updates the game model.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
