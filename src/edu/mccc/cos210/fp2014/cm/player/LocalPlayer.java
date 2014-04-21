package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;

/**
 * A local player object.
 * Updates the model when the view is updated.
 */
public class LocalPlayer extends Player {
	
	public LocalPlayer(GameModel gm, boolean b) {
		super(gm, b);
	}
	/**
	 * Updates the model.
	 */
	@Override
	public void updateModel(Piece p) {
	}
	/**
	 * Called when the model is updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
	}
}
