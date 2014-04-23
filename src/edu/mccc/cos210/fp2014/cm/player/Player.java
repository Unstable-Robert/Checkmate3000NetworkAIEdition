package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observer;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.King;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;

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
	public boolean updateModel(Piece piece, PossibleTile pt){
		Board b = gm.getBoard();
		if (b.isWhiteTurn() == piece.isWhite() &&
			b.isWhiteTurn() == this.isWhite
		){
			b.nextTurn();
			b.removePiece(piece);
			Piece clone = pt.getOriginalPiece();
			clone.setX(pt.getX());
			clone.setY(pt.getY());
			clone.setSelected(false);
			b.addPiece(clone);
			if (pt.hasPieceToRemove()){
				b.removePiece(pt.getRemovePiece());
			}
			gm.updateBoard(b);
			return true;
		}
		return false;
	}
	public boolean updateCastle(Piece p1, Piece p2) {
		Board b = gm.getBoard();
		if (b.isWhiteTurn() == p1.isWhite() &&
				b.isWhiteTurn() == this.isWhite) {
			b.nextTurn();
			Piece clone1 = p1.clone();
			Piece clone2 = p2.clone();
			b.removePiece(p1);
			b.removePiece(p2);
			if (p1.getX() < p2.getX()){
				if (p1 instanceof King){
					clone1.setX(p2.getX() - 1);
					clone2.setX(p1.getX() + 1);
				} else {
					clone1.setX(p2.getX() - 2);
					clone2.setX(p1.getX() + 1);
				}
			} else {
				if (p1 instanceof King){
					clone1.setX(p2.getX() + 1);
					clone2.setX(p1.getX() - 2);
				} else {
					clone1.setX(p2.getX() + 1);
					clone2.setX(p1.getX() - 1);
				}
			}
			clone1.setSelected(false);
			b.addPiece(clone1);
			b.addPiece(clone2);
			gm.updateBoard(b);
			return true;
		}
		return false;
	}
}
