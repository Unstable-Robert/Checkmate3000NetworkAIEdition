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
	protected boolean isWhite;
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
		this.isWhite = c;
		this.uID = iD;
	}
	public Piece(int x, int y, boolean c, int iD, boolean s){
		this(x, y, c, iD);
		this.selected = s;
	}
	/**
	 * Gets possible tiles that this piece can move on the given board.
	 * @param board the board that is checked for possible moves
	 * @return all of the possible tiles to which this piece can move.
	 */
	public ArrayList<PossibleTile> getPossibleTiles(Board board) {
		ArrayList<PossibleTile> finalTiles = new ArrayList<PossibleTile>();
		ArrayList<PossibleTile> lazyTiles = getLazyTiles(board);
		for (PossibleTile pt : lazyTiles){
			if (!causesCheck(pt, board)){
				finalTiles.add(pt);
			}
		}
		return finalTiles;
	}	
	/**
	 * Checks for possible moves, regardless of whether or not a move causes
	 * a the player to go into check.
	 */
	protected abstract ArrayList<PossibleTile> getLazyTiles(Board b);
	protected boolean causesCheck(PossibleTile pt, Board b){
		Board bClone = b.clone();
		bClone.removePiece(pt.getOriginalPiece());
		Piece newPiece = pt.getOriginalPiece().clone();
		newPiece.setX(pt.getX());
		newPiece.setY(pt.getY());
		bClone.addPiece(newPiece);
		if (pt.hasPieceToRemove()){
			bClone.removePiece(pt.getRemovePiece());
		}
		King k = null;
		for (Piece p : bClone.getPieces()){
			if (p.isWhite() == pt.getOriginalPiece().isWhite() &&
					p instanceof King){
				k = (King) p;
				break;
			}
		}
		for (Piece p : bClone.getPieces()){
			if(p.isWhite() != pt.getOriginalPiece().isWhite()){
				for(PossibleTile move : p.getLazyTiles(bClone)){
					if (checkSameSpace(k, move)){
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Changes the location of the piece.
	 */
	public void setLocation(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;
	}
	public int getX() {
		return this.xLoc;
	}
	public int getY() {
		return this.yLoc;
	}
	public void setX(int x) {
		this.xLoc = x;
	}
	public void setY(int y) {
		this.yLoc = y;
	}
	public boolean isWhite() {
		return this.isWhite;
	}
	public int getUID(){
		return this.uID;
	}
	public boolean isSelected() {
		return this.selected;
	}
	/**
	 * Changes whether or not the piece is selected.
	 */
	public void setSelected(boolean b) {
		this.selected = b;
	}
	protected boolean decideToAddTile(Board b, ArrayList<PossibleTile> pts, PossibleTile pt){
		if (!checkBounds(pt.getX(), pt.getY())) {
			return false;
		}
		for (Piece p : b.getPieces()){
			if (checkSameSpace(p, pt)) {
				if (this.isWhite != p.isWhite) {
					pt.setRemovePiece(p);
					pts.add(pt);
				}
				return false;
			} 
		}
		pts.add(pt);
		return true;
	}
	protected boolean checkBounds(int x, int y){
		return !(x < 0 || x > 7 || y < 0 || y > 7);
	}
	protected boolean checkSameSpace(Piece p1, PossibleTile p2){
		return (p1.getX() == p2.getX() && p1.getY() == p2.getY());
	}
	public abstract String getUnicode();
	public abstract Piece clone();
}
