package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * Represents the Knight piece and its movements.
 */
@XmlRootElement
public class Knight extends Piece {
	public Knight(int x, int y, Color c, int iD){
		super(x,y,c,iD);
	}
	public Knight(int x, int y, Color c, int iD, boolean s){
		super(x,y,c,iD,s);
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
	public Knight clone(){
		return new Knight(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected());
	}
}
