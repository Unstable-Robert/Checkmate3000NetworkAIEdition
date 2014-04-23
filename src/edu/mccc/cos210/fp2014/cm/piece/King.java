package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the king piece and its movements.
 */
@XmlRootElement
public class King extends Piece {
	@XmlElement
	private boolean canCastle;
	public King(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
		this.canCastle = true;
	}
	public King(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.canCastle = true;
	}
	public King(int x, int y, boolean c, int iD, boolean s, boolean castle){
		super(x,y,c,iD,s);
		this.canCastle = castle;
	}
	/**
	 * Sees if the king can castle to the left.
	 * Returns true if the king has not moved, there are no pieces in the way, 
	 * and if the king is in check, moving through check, or into check.
	 */
	public boolean canCastleLeft(Board board){
		if (!this.canCastle) { return false; }
		PossibleTile space1 = new PossibleTile(this.getX() - 1, this.getY(), this);
		PossibleTile space2 = new PossibleTile(this.getX() - 2, this.getY(), this);
		PossibleTile space3 = new PossibleTile(this.getX() - 3, this.getY(), this);
		for (Piece p : board.getPieces()){
			if (checkSameSpace(p, space1) ||
				checkSameSpace(p, space2) ||
				checkSameSpace(p, space3)) {
					return false;
				}
		}
		return true;
	}
	/**
	 * Sees if the king can castle to the right.
	 * Returns true if the king has not moved, there are no pieces in the way, 
	 * and if the king is in check, moving through check, or into check.
	 */
	public boolean canCastleRight(Board board){
		if (!this.canCastle) { return false; }
		PossibleTile space1 = new PossibleTile(this.getX() + 1, this.getY(), this);
		PossibleTile space2 = new PossibleTile(this.getX() + 2, this.getY(), this);
		for (Piece p : board.getPieces()){
			if (checkSameSpace(p, space1) ||
				checkSameSpace(p, space2)) {
					return false;
				}
		}
		return true;
	}
	public King clone(){
		return new King(this.getX(), 
				this.getY(), 
				this.isWhite(), 
				this.getUID(), 
				this.isSelected(), 
				this.canCastle);
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		King clone = this.clone();
		clone.canCastle = false;
		ArrayList<PossibleTile> possibleTiles = new ArrayList<PossibleTile>();
		ArrayList<PossibleTile> superLazyTile = new ArrayList<PossibleTile>();
		superLazyTile.add(new PossibleTile(clone.getX() - 1, clone.getY() - 1, clone));
		superLazyTile.add(new PossibleTile(clone.getX()    , clone.getY() - 1, clone));
		superLazyTile.add(new PossibleTile(clone.getX() + 1, clone.getY() - 1, clone));
		superLazyTile.add(new PossibleTile(clone.getX() + 1, clone.getY()    , clone));
		superLazyTile.add(new PossibleTile(clone.getX() + 1, clone.getY() + 1, clone));
		superLazyTile.add(new PossibleTile(clone.getX()    , clone.getY() + 1, clone));
		superLazyTile.add(new PossibleTile(clone.getX() - 1, clone.getY() + 1, clone));
		superLazyTile.add(new PossibleTile(clone.getX() - 1, clone.getY()    , clone));
		for(PossibleTile pt : superLazyTile) {
			decideToAddTile(b, possibleTiles, pt);
		}
		for (Piece p : b.getPieces()){
			if (p instanceof Rook && p.isWhite == this.isWhite) {
				Rook r = (Rook) p;
				if (r.canCastle()){
					if (this.getX() < r.getX() &&
						this.canCastleRight(b)){
						possibleTiles.add(new PossibleTile(r.getX(), r.getY(), this));
					} else if (this.getX() > r.getX() &&
						this.canCastleLeft(b)){
						possibleTiles.add(new PossibleTile(r.getX(), r.getY(), this));
					}	
				}
			}
		}
		return possibleTiles;
	}
}
