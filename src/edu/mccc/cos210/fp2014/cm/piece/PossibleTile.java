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
		this.xLoc = x;
		this.yLoc = y;
		this.originalPiece = original;
		this.removePiece = null;
	}
	/**
	 * Constructor including a piece to remove
	 */
	public PossibleTile(int x, int y, Piece original, Piece removed) {
		this.xLoc = x;
		this.yLoc = y;
		this.originalPiece = original;
		this.removePiece = removed;
	}
	/**
	 * Returns the x location of the tile.
	 */
	public int getX() {
		return this.xLoc;
	}
	/**
	 * Returns the Y location of the tile.
	 */
	public int getY() {
		return this.yLoc;
	}
	public Piece getOriginalPiece() {
		return this.originalPiece;
	}
	public Piece getRemovePiece() {
		return this.removePiece;
	}
	public boolean hasPieceToRemove() {
		return this.removePiece != null;
	}
	public PossibleTile clone(){
		return new PossibleTile(
				this.getX(),
				this.getY(),
				this.getOriginalPiece().clone(),
				this.getRemovePiece().clone());
	}
}
