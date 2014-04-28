package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observable;
import java.util.TimerTask;

/**
 * The TimerEvent used when players track their turn time.
 */
public class TurnTimer extends TimerTask {
	private GameModel model;
	private int blackTime, whiteTime;
	public TurnTimer(GameModel gm, int i) {
		this.model = gm;
		this.blackTime = i;// * 60;
		this.whiteTime = i;// * 60;
	}
	@Override
	public void run() {
		Board b = model.getBoard();
		if ( b.isWhiteTurn() ) {
			whiteTime--;
			b.updateTime(whiteTime);
			if (whiteTime <= 0) {
				model.gameExpired();
			}
		} else {
			blackTime--;
			b.updateTime(blackTime);
			if (blackTime <= 0) {
				model.gameExpired();
			}
		}
	}
}
