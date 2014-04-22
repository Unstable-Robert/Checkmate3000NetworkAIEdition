package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * An abstract class representing an individual piece on the board.
 */
@XmlRootElement
public abstract class Piece implements Cloneable {
	@XmlElement
	protected int xLoc;
	@XmlElement
	protected int yLoc;
	@XmlElement
	protected boolean color;
	@XmlElement
	protected int uID;
	@XmlElement
	protected boolean selected;
	/**
	 * Constructor to be implemented by subclasses which gets a list of possible moves.
	 */
	public Piece(){
	}
	public Piece(int x, int y, boolean c, int iD){
		this.xLoc = x;
		this.yLoc = y;
		this.color = c;
		this.uID = iD;
	}
	public Piece(int x, int y, boolean c, int iD, boolean s){
		this(x, y, c, iD);
		this.selected = s;
	}
	public abstract ArrayList<PossibleTile> getPossibleTiles(Board b);
	/**
	 * Checks for possible moves, regardless of whether or not a move causes
	 * a the player to go into check.
	 */
	protected abstract ArrayList<PossibleTile> getLazyTiles(Board b);
	/**
	 * Changes the location of the piece.
	 */
	public void setLocation(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;
	}
	public int getX(){
		return this.xLoc;
	}
	public int getY(){
		return this.yLoc;
	}
	public boolean getColor(){
		return this.color;
	}
	public int getUID(){
		return this.uID;
	}
	public boolean isSelected(){
		return this.selected;
	}
	/**
	 * Changes whether or not the piece is selected.
	 */
	public void setSelected(boolean b) {
		this.selected = b;
	}
	protected boolean checkBounds(int x, int y){
		return !(x < 0 | x > 7 | y < 0 | y > 7);
	}
	public abstract Piece clone();
}
