package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the Knight piece and its movements.
 */
@XmlRootElement(name="Knight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Knight extends Piece {
	public Knight() {
	}
	public Knight(int x, int y, boolean c, int iD) {
		super(x,y,c,iD);
	}
	public Knight(int x, int y, boolean c, int iD, boolean s) {
		super(x,y,c,iD,s);
	}
	public Knight(Piece p) {
		super(p);
	}
	public Knight clone() {
		return new Knight(
			this.getX(), 
			this.getY(), 
			this.isWhite(), 
			this.getUID(), 
			this.isSelected()
		);
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		ArrayList<PossibleTile> lazyTiles = new ArrayList<PossibleTile>();
		ArrayList<PossibleTile> superLazyTiles = new ArrayList<PossibleTile>();
		superLazyTiles.add(new PossibleTile(this.getX() - 2, this.getY() - 1, this));
		superLazyTiles.add(new PossibleTile(this.getX() - 1, this.getY() - 2, this));
		superLazyTiles.add(new PossibleTile(this.getX() + 1, this.getY() - 2, this));
		superLazyTiles.add(new PossibleTile(this.getX() + 2, this.getY() - 1, this));
		superLazyTiles.add(new PossibleTile(this.getX() + 2, this.getY() + 1, this));
		superLazyTiles.add(new PossibleTile(this.getX() + 1, this.getY() + 2, this));
		superLazyTiles.add(new PossibleTile(this.getX() - 1, this.getY() + 2, this));
		superLazyTiles.add(new PossibleTile(this.getX() - 2, this.getY() + 1, this));
		for(PossibleTile pt : superLazyTiles) {
			decideToAddTile(b, lazyTiles, pt);
		}
		return lazyTiles;
	}
	@Override
	public String locToString() {
		return "N" + String.valueOf(Character.toChars(65+this.getX())) + (Math.abs(this.getY() - 8));
	}
}
