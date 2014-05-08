package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the Bishop piece and its movements.
 */
@XmlRootElement(name="Bishop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bishop extends Piece {
	public Bishop() {
	}
	public Bishop(int x, int y, boolean c, int iD) {
		super(x,y,c,iD);
	}
	public Bishop(int x, int y, boolean c, int iD, boolean s) {
		super(x,y,c,iD,s);
	}
	public Bishop(Piece p) {
		super(p);
	}
	@Override
	public Bishop clone() {
		return new Bishop(
			this.getX(), 
			this.getY(), 
			this.isWhite(), 
			this.getUID(), 
			this.isSelected()
		);
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		ArrayList<PossibleTile> possibleTiles = new ArrayList<PossibleTile>();
		int i = 1;
		boolean canSearch = true;
		while (canSearch) {
			PossibleTile pt = new PossibleTile(this.getX() - i, this.getY() - i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch) {
			PossibleTile pt = new PossibleTile(this.getX() - i, this.getY() + i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch) {
			PossibleTile pt = new PossibleTile(this.getX() + i, this.getY() - i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch) {
			PossibleTile pt = new PossibleTile(this.getX() + i, this.getY() + i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		return possibleTiles;
	}
	@Override
	public String locToString() {
		return "B" + String.valueOf(Character.toChars(65+this.getX())) + (Math.abs(this.getY() - 8));
	}
}
