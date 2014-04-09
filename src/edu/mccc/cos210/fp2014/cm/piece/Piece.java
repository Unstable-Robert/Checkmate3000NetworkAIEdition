package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * 
 */
public abstract class Piece {
	
	protected int xLoc;
	protected int yLoc;
	protected Color c;
	protected int uID;
	protected boolean selected;
	/**
	 * 
	 */
	public abstract ArrayList<PossibleTile> getPossibleTiles(ArrayList<Piece> pieces);
	/**
	 * 
	 */
	public void setLocation(int x, int y){
		
	}
}
