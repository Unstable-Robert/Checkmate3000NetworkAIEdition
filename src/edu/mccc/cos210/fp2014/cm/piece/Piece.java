package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * And abstract class representing an individual piece on the board.
 */
public abstract class Piece {
	
	protected int xLoc;
	protected int yLoc;
	protected Color c;
	protected int uID;
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
}
