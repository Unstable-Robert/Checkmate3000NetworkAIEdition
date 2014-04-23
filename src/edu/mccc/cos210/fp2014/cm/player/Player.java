package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observer;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;

/**
 * Abstract player class.
 * Generic player object, containing the update model and update methods in addition to 
 * some a game model and a color.
 */
public abstract class Player implements Observer{
	protected GameModel gm;
	protected boolean isWhite;
	public Player(){
	}
	public Player(GameModel gm, boolean isWhite){
		this.gm = gm;
		this.isWhite = isWhite;
	}
	/**
	 * This class will update the game model.
	 */
	public void updateModel(Piece oldPiece, Piece newPiece) {
		Board b = gm.getBoard();
		if (b.isWhiteTurn() == this.isWhite) {
			b.removePiece(oldPiece);
			b.addPiece(newPiece);
			gm.updateBoard(b);
		}
	}
}
