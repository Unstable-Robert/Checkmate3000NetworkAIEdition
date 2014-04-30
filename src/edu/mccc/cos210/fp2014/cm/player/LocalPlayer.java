package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.piece.*;

/**
 * A local player object.
 * Updates the model when the view is updated.
 */
public class LocalPlayer extends Player {
	public LocalPlayer(GameModel gm, Checkmate c, boolean b) {
		super(gm, c, b);
	}
	/**
	 * Updates the model.
	 */
	@Override
	public boolean updateModel(Piece piece, PossibleTile pt){
		return super.updateModel(piece, pt);
	}
	/**
	 * Called when the model is updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (this.gm.getBoard() != null){
			Board b = this.checkPawnPromotion();
			if (b != null){
				gm.updateBoard(b, false);
			}
		}
	}
}
