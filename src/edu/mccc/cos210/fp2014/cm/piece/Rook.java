package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * The class represents the Rook piece and it's movements
 */
@XmlRootElement
public class Rook extends Piece {

	@XmlElement
	private boolean canCastle;
	public Rook(int x, int y, Color c, int iD){
		super(x,y,c,iD);
		this.canCastle = true;
	}
	public Rook(int x, int y, Color c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.canCastle = true;
	}
	public Rook(int x, int y, Color c, int iD, boolean s, boolean castle){
		super(x,y,c,iD,s);
		this.canCastle = castle;
	}
	/**
	 * Gets possible tiles that this piece can move given the board.
	 * @param board the board that is checked for possible moves
	 * @return all of the possible tiles to which this piece can move.
	 */
	@Override
	public ArrayList<PossibleTile> getPossibleTiles(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * This returns true if the rook has not moved and false if it has.
	 */
	public boolean canCastle(){
		return false;
	}
	@Override
	public Rook clone(){
		return new Rook(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected(), 
				this.canCastle());
	}
}
