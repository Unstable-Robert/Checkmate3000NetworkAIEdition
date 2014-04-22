package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the Knight piece and its movements.
 */
@XmlRootElement
public class Knight extends Piece {
	public Knight(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
	}
	public Knight(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
	}
	public Knight clone(){
		return new Knight(this.getX(), 
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
