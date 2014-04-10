package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * And abstract class representing an individual piece on the board.
 */
public abstract class Piece implements Cloneable {
	
	@XmlElement
	protected int xLoc;
	@XmlElement
	protected int yLoc;
	@XmlElement
	protected Color color;
	@XmlElement
	protected int uID;
	@XmlElement
	protected boolean selected;
	/**
	 * A method to be implemented by subclasses which gets a list of possible moves.
	 */
	public Piece(){
		
	}
	public Piece(int x, int y, Color c, int iD){
		this.xLoc = x;
		this.yLoc = y;
		this.color = c;
		this.uID = iD;
		this.selected = false;
	}
	public Piece(int x, int y, Color c, int iD, boolean s){
		this.xLoc = x;
		this.yLoc = y;
		this.color = c;
		this.uID = iD;
		this.selected = s;
	}
	public abstract ArrayList<PossibleTile> getPossibleTiles(Board b);
	/**
	 * Changes the location of the piece.
	 */
	public void setLocation(int x, int y) {
		
	}
	public int getX(){
		return this.xLoc;
	}
	public int getY(){
		return this.yLoc;
	}
	public Color getColor(){
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
		
	}
	public abstract Piece clone();
}
