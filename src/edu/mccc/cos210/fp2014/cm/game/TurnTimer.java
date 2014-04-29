package edu.mccc.cos210.fp2014.cm.game;

import java.util.TimerTask;

import edu.mccc.cos210.fp2014.cm.player.NetworkPlayer;

/**
 * The TimerEvent used when players track their turn time.
 */
public class TurnTimer extends TimerTask {
	private GameModel model;
	private int blackTime, whiteTime;
	private NetworkPlayer player;
	public TurnTimer(GameModel gm, int i) {
		this.model = gm;
		this.blackTime = i * 60;
		this.whiteTime = i * 60;
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
		updatePlayer();
		model.updateBoard(b);
	}
	private void updatePlayer(){
		if (this.player != null){
			this.player.setUpdatedByNetwork(false);
		}
	}
}
