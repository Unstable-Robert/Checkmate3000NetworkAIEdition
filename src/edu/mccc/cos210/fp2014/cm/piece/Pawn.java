package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the pawn and its movements.
 */
@XmlRootElement(name="Pawn")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pawn extends Piece {
	@XmlElement
	private boolean hasMoved;
	@XmlElement
	private boolean possibleToPassant;
    @XmlElement
    private boolean hasPromoted;
    public Pawn(){
    	
    }
	public Pawn(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
		this.hasMoved = false;
		this.possibleToPassant = false;
        this.hasPromoted = false;
	}
	public Pawn(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.selected = s;
		this.hasMoved = false;
		this.possibleToPassant = false;
        this.hasPromoted = false;
	}
	public Pawn(int x, int y, boolean c, int iD, boolean s, boolean moved, boolean passant){
		super(x,y,c,iD,s);
		this.selected = s;
		this.hasMoved = moved;
		this.possibleToPassant = passant;
        this.hasPromoted = false;
	}
    public Pawn(Piece p){
        super(p);
        this.hasPromoted = false;
    }
    public boolean isPromoted(){return this.hasPromoted;}
    public void  setHasPromoted(boolean p) {this.hasPromoted = p;}
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

    /**
     * Weather or not the pawn is in a position to be promoted
     */
    public boolean canPromote(){
        if (this.isWhite()){
            if (this.getY() == 0) return true;
        }
        else {
            if (this.getY() == 7) return true;
        }
        return false;
    }
	@Override
	public Pawn clone(){
		return new Pawn(this.getX(), 
				this.getY(), 
				this.isWhite(), 
				this.getUID(), 
				this.isSelected(),
				this.hasMoved(),
				this.possibleToPassant());
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		ArrayList<PossibleTile> lazyTiles = new ArrayList<PossibleTile>();
		PossibleTile step1;
		if (isWhite){
			Pawn clone = this.clone();
			clone.setMoved();
			step1 = new PossibleTile(clone.getX(), clone.getY() - 1, clone);
		} else {
			Pawn clone = this.clone();
			clone.setMoved();
			step1 = new PossibleTile(clone.getX(), clone.getY() + 1, clone);
		}
		boolean canMove = decideToAddTile(b, lazyTiles, step1);
		if (!this.hasMoved && canMove) {
			Pawn clone = this.clone();
			PossibleTile step2;
			clone.setMoved();
			clone.setPossibleToPassant(true);
			if (isWhite){
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
		if (isWhite){
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
				if (this.isWhite != p.isWhite) {
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
		if (isWhite){
			ps = new PossibleTile(pt.getX(), pt.getY() - 1, pt.getOriginalPiece());
		} else {
			ps = new PossibleTile(pt.getX(), pt.getY() + 1, pt.getOriginalPiece());
		}
		for (Piece p : b.getPieces()){
			if (p instanceof Pawn && p.isWhite != this.isWhite){
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
