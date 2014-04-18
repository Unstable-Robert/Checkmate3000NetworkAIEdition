package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observer;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * Generic player object, containing the update model and update methods in addition to 
 * some a game model and a color.
 */
public abstract class Player implements Observer{

	protected GameModel gm;
	protected Color color;
	
	/**
	 * This class will update the game model.
	 */
	protected abstract void updateModel(Piece p);

}
