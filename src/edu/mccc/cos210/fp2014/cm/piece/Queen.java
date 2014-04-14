package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * Represents the Queen piece and its movement.
 */
@XmlRootElement
public class Queen extends Piece {
	public Queen(int x, int y, Color c, int iD){
		super(x,y,c,iD);
	}
	public Queen(int x, int y, Color c, int iD, boolean s){
		super(x,y,c,iD,s);
	}
	/**
	 * Gets possible tiles that this piece can move on the given the board.
	 * @param board the board that is checked for possible moves
	 * @return all of the possible tiles to which this piece can move.
	 */
	@Override
	public ArrayList<PossibleTile> getPossibleTiles(Board board) {
		return null;
	}
	@Override
	public Queen clone(){
		return new Queen(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected());
	}
}
