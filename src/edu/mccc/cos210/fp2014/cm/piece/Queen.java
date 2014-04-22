package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the Queen piece and its movement.
 */
@XmlRootElement
public class Queen extends Piece {
	public Queen(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
	}
	public Queen(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
	}
	@Override
	public Queen clone(){
		return new Queen(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected());
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
}
