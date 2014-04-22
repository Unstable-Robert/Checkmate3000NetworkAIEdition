package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the pawn and its movements.
 */
@XmlRootElement
public class Pawn extends Piece {
	@XmlElement
	private boolean hasMoved;
	@XmlElement
	private boolean possibleToPassant;
	public Pawn(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
		this.hasMoved = false;
		this.possibleToPassant = false;
	}
	public Pawn(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.hasMoved = false;
		this.possibleToPassant = false;
	}
	public Pawn(int x, int y, boolean c, int iD, boolean s, boolean moved, boolean passant){
		super(x,y,c,iD,s);
		this.hasMoved = moved;
		this.possibleToPassant = passant;
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
	 * Whether or not the pawn has just moved forward two spaces and can be taken en passant.
	 */
	public boolean possibleToPassant() {
		return this.possibleToPassant;
	}
	/**
	 * Whether or not the pawn has moved, and therefor whether it can move forward two spaces
	 */
	private boolean hasMoved() {
		return this.hasMoved;
	}
	@Override
	public Pawn clone(){
		return new Pawn(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected(),
				this.hasMoved(),
				this.possibleToPassant());
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
}
