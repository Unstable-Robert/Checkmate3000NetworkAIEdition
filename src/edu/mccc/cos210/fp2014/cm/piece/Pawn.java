package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the pawn and its movements.
 */
@XmlRootElement
public class Pawn extends Piece {
	@XmlElement
	private boolean hasMoved;
	@XmlElement
	private boolean possibleToPassant;
	public Pawn(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
		this.hasMoved = false;
		this.possibleToPassant = false;
	}
	public Pawn(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.hasMoved = false;
		this.possibleToPassant = false;
	}
	public Pawn(int x, int y, boolean c, int iD, boolean s, boolean moved, boolean passant){
		super(x,y,c,iD,s);
		this.hasMoved = moved;
		this.possibleToPassant = passant;
	}
	/**
	 * Whether or not the pawn has just moved forward two spaces and can be taken en passant.
	 */
	public boolean possibleToPassant() {
		return this.possibleToPassant;
	}
	public void setPossibleToPassant(boolean possible) {
		this.possibleToPassant = possible;
	}
	/**
	 * Whether or not the pawn has moved, and therefor whether it can move forward two spaces
	 */
	private boolean hasMoved() {
		return this.hasMoved;
	}
	private void setMoved(){
		this.hasMoved = true;
	}
	@Override
	public Pawn clone(){
		return new Pawn(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected(),
				this.hasMoved(),
				this.possibleToPassant());
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		ArrayList<PossibleTile> lazyTiles = new ArrayList<PossibleTile>();
		PossibleTile step1;
		if (color){
			step1 = new PossibleTile(this.getX(), this.getY() - 1, this);
		} else {
			step1 = new PossibleTile(this.getX(), this.getY() + 1, this);
		}
		boolean canMove = decideToAddTile(b, lazyTiles, step1);
		if (!hasMoved && canMove) {
			Pawn clone = this.clone();
			PossibleTile step2;
			clone.setMoved();
			clone.setPossibleToPassant(true);
			if (color){
				step2 = new PossibleTile(clone.getX(), clone.getY() - 2, clone);
			} else {
				step2 = new PossibleTile(clone.getX(), clone.getY() + 2, clone);
			}
			decideToAddTile(b, lazyTiles, step2);
		}
		Pawn clone = this.clone();
		clone.setMoved();
		PossibleTile attack1;
		PossibleTile attack2;
		if (color){
			attack1 = new PossibleTile(clone.getX() - 1, clone.getY() - 1, clone);
			attack2 = new PossibleTile(clone.getX() + 1, clone.getY() - 1, clone);
		} else {
			attack1 = new PossibleTile(clone.getX() - 1, clone.getY() + 1, clone);
			attack2 = new PossibleTile(clone.getX() + 1, clone.getY() + 1, clone);
		}
		decideToAddAttackTile(b, lazyTiles, attack1);
		decideToAddAttackTile(b, lazyTiles, attack2);
		PossibleTile passant1 = new PossibleTile(clone.getX() - 1, clone.getY(), clone);
		PossibleTile passant2 = new PossibleTile(clone.getX() + 1, clone.getY(), clone);
		decideToAddPassantTile(b, lazyTiles, passant1);
		decideToAddPassantTile(b, lazyTiles, passant2);
		return lazyTiles;
	}
	@Override
	protected boolean decideToAddTile(Board b, ArrayList<PossibleTile> pts, PossibleTile pt){
		if (!checkBounds(pt.getX(), pt.getY())) {
			return false;
		}
		for (Piece p : b.getPieces()){
			if (checkSameSpace(p, pt)) {
				return false;
			} 
		}
		pts.add(pt);
		return true;
	}
	private boolean decideToAddAttackTile(Board b, ArrayList<PossibleTile> pts, PossibleTile pt){
		if (!checkBounds(pt.getX(), pt.getY())) {
			return false;
		}
		for (Piece p : b.getPieces()){
			if (checkSameSpace(p, pt)) {
				if (this.color != p.color) {
					pt.setRemovePiece(p);
					pts.add(pt);
					return false;
				}
			} 
		}
		return true;
	}
	private boolean decideToAddPassantTile(Board b, ArrayList<PossibleTile> pts, PossibleTile pt){
		if (!checkBounds(pt.getX(), pt.getY())) {
			return false;
		}
		PossibleTile ps;
		if (color){
			ps = new PossibleTile(pt.getX(), pt.getY() - 1, pt.getOriginalPiece());
		} else {
			ps = new PossibleTile(pt.getX(), pt.getY() + 1, pt.getOriginalPiece());
		}
		for (Piece p : b.getPieces()){
			if (p instanceof Pawn){
				Pawn pawn = (Pawn) p;
				if (pawn.possibleToPassant()){
					if (checkSameSpace(pawn, pt)){
						ps.setRemovePiece(pawn);
						pts.add(ps);
						return false;
					}
				}
			}
		}
		return true;
	}
}
