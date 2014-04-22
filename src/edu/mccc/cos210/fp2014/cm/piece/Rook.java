package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * The class represents the Rook piece and its movements.
 */
@XmlRootElement
public class Rook extends Piece {
	@XmlElement
	private boolean canCastle;
	public Rook(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
		this.canCastle = true;
	}
	public Rook(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
		this.canCastle = true;
	}
	public Rook(int x, int y, boolean c, int iD, boolean s, boolean castle){
		super(x,y,c,iD,s);
		this.canCastle = castle;
	}
	/**
	 * This returns true if the rook has not moved and false if it has.
	 */
	public boolean canCastle(ArrayList<Piece> board){
		return false;
	}
	@Override
	public Rook clone(){
		return new Rook(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected(), 
				this.canCastle);
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		ArrayList<PossibleTile> possibleTiles = new ArrayList<PossibleTile>();
		int i = 1;
		boolean canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX() - i, this.getY(), this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX() + i, this.getY(), this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX(), this.getY() - i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX(), this.getY() + i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		if (this.canCastle) {
			for (Piece p : b) {
				if (p instanceof King){
					King k = (King) p;
					if (this.getX() < k.getX() &&
						k.canCastleLeft(b)){
						possibleTiles.add(new PossibleTile(k.getX(), k.getY(), this));
					} else if (this.getX() > k.getX() &&
						k.canCastleRight(b)){
						possibleTiles.add(new PossibleTile(k.getX(), k.getY(), this));
					}
				}
			}
		}
		return possibleTiles;
	}
}
