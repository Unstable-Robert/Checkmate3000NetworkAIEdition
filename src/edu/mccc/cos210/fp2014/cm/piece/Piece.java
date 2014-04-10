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
	protected Color c;
	@XmlElement
	protected int uID;
	@XmlElement
	protected boolean selected;
	/**
	 * A method to be implemented by subclasses which gets a list of possible moves.
	 */
	public abstract ArrayList<PossibleTile> getPossibleTiles(Board b);
	/**
	 * Changes the location of the piece.
	 */
	public void setLocation(int x, int y) {
		
	}
	/**
	 * Changes whether or not the piece is selected.
	 */
	public void setSelected(boolean b) {
		
	}
	//be careful. See board.clone warning....
	public Piece clone(){
		return null;
	}
}
