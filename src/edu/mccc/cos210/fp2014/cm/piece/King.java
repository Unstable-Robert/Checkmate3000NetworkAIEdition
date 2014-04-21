package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the king piece and its movements.
 */
@XmlRootElement
public class King extends Piece {
	@XmlElement
	private boolean canCastle;
	public King(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
		this.canCastle = true;
	}
	public King(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.canCastle = true;
	}
	public King(int x, int y, boolean c, int iD, boolean s, boolean castle){
		super(x,y,c,iD,s);
		this.canCastle = castle;
	}
	/**
	 * Gets possible tiles that this piece can move on the given board.
	 * @param board the board that is checked for possible moves
	 * @return all of the possible tiles to which this piece can move.
	 */
	@Override
	public ArrayList<PossibleTile> getPossibleTiles(Board board) {
		return null;
	}
	/**
	 * Sees if the king can castle to the left.
	 * Returns true if the king has not moved, there are no pieces in the way, 
	 * and if the king is in check, moving through check, or into check.
	 */
	public boolean canCastleLeft(ArrayList<Piece> board){
		return false;
	}
	/**
	 * Sees if the king can castle to the right.
	 * Returns true if the king has not moved, there are no pieces in the way, 
	 * and if the king is in check, moving through check, or into check.
	 */
	public boolean canCastleRight(ArrayList<Piece> board){
		return false;
	}
	public King clone(){
		return new King(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected(), 
				this.canCastle);
	}
}
