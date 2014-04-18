package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * Represents the king piece and it's movements
 */
public class King extends Piece {
	private boolean canCastle;
	public King(int x, int y, Color c, int iD){
		super(x,y,c,iD);
		this.canCastle = true;
	}
	public King(int x, int y, Color c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.canCastle = true;
	}
	public King(int x, int y, Color c, int iD, boolean s, boolean castle){
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
	 * whether or not the king has moved and can castle
	 */
	public boolean canCastle(){
		return false;
	}
	public King clone(){
		return new King(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected(), 
				this.canCastle());
	}
}
