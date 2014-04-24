package edu.mccc.cos210.fp2014.cm.piece;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This represents the place that a piece can move, and what piece will be removed if it move does.
 */
@XmlRootElement(name="PossibleTile")
@XmlAccessorType(XmlAccessType.FIELD)
public class PossibleTile implements Cloneable{
	@XmlElement
	private int xLoc;
	@XmlElement
	private int yLoc;
	@XmlElement
	private Piece originalPiece;
	@XmlElement
	private Piece removePiece;
	/**
	 * Default constructor.
	 */
	public PossibleTile(){
		
	}
	public PossibleTile(int x, int y, Piece original){
		this.xLoc = x;
		this.yLoc = y;
		this.originalPiece = original;
		this.removePiece = new NullPiece();
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
	public void setRemovePiece(Piece p){
		this.removePiece = p;
	}
	public boolean hasPieceToRemove() {
		return !(this.removePiece instanceof NullPiece);
	}
	public PossibleTile clone(){
		return new PossibleTile(
				this.getX(),
				this.getY(),
				this.getOriginalPiece().clone(),
				this.getRemovePiece().clone());
	}
}
