package edu.mccc.cos210.fp2014.cm.player;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;

public abstract class Player {

	protected GameModel gm;
	
	protected abstract void updateModel(Piece p);
	public void updateEvent(Piece p){
		
	}
}
