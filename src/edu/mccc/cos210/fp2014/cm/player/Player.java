package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observer;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * 
 */
public abstract class Player implements Observer{

	protected GameModel gm;
	protected Color color;
	
	/**
	 * 
	 */
	protected abstract void updateModel(Piece p);

}
