package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observable;

/**
 * The TimerEvent used when each player times their total game time.
 */
public class GameTimer extends TimerEvent {
	private int ticks2;
	private int maxTicks2;
	private boolean updateTicks2;
	
	/**
	 * When the model updates, it makes sure that it's still the turn of the individual it's timing.
	 * If not, it switches to the other player's time.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	/**
	 * Switches over from updating ticks to ticks2 to keep track of each players individual time.
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
