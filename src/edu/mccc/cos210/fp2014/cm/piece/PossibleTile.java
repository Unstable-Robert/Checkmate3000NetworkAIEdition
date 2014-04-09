package edu.mccc.cos210.fp2014.cm.piece;

/**
 * This represents the place that a piece can move, and what it will remove if it does.
 */
public class PossibleTile {
	private int xLoc;
	private int yLoc;
	private Piece originalPiece;
	private Piece removePiece;
	
	/**
	 * Default constructor
	 */
	public PossibleTile(int x, int y, Piece original){

	}
	/**
	 * Constructor including a piece to remove
	 */
	public PossibleTile(int x, int y, Piece original, Piece removed) {
		
	}
	
	/**
	 * returns the x location of the tile
	 */
	public int getXLoc() {
		return 0;
	}
	/**
	 * returns the y location of the tile.
	 */
	public int getYLoc() {
		return 0;
	}
}
