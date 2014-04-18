package edu.mccc.cos210.fp2014.cm.piece;

/**
 * This represents the place that a piece can move, and what piece will be removed if it move does.
 */
public class PossibleTile implements Cloneable{
	private int xLoc;
	private int yLoc;
	private Piece originalPiece;
	private Piece removePiece;
	/**
	 * Default constructor.
	 */
	public PossibleTile(int x, int y, Piece original){
	}
	/**
	 * Constructor including a piece to remove
	 */
	public PossibleTile(int x, int y, Piece original, Piece removed) {
	}
	/**
	 * Returns the x location of the tile.
	 */
	public int getX() {
		return 0;
	}
	/**
	 * Returns the Y location of the tile.
	 */
	public int getY() {
		return 0;
	}
	public Piece getOriginalPiece() {
		return null;
	}
	public Piece getRemovePiece() {
		return null;
	}
	public boolean hasPieceToRemove() {
		return false;
	}
	public PossibleTile clone(){
		return new PossibleTile(
				this.getX(),
				this.getY(),
				this.getOriginalPiece().clone(),
				this.getRemovePiece().clone());
	}
}
