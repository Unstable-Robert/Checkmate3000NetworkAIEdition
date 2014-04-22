package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the Bishop piece and its movements.
 */
@XmlRootElement
public class Bishop extends Piece {
	public Bishop(int x, int y, boolean c, int iD){
		super(x,y,c,iD);
	}
	public Bishop(int x, int y, boolean c, int iD, boolean s){
		super(x,y,c,iD,s);
	}
	@Override
	public Bishop clone(){
		return new Bishop(this.getX(), 
				this.getY(), 
				this.getColor(), 
				this.getUID(), 
				this.isSelected());
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		ArrayList<PossibleTile> possibleTiles = new ArrayList<PossibleTile>();
		int i = 1;
		boolean canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX() - i, this.getY() - i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX() - i, this.getY() + i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX() + i, this.getY() - i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		i = 1;
		canSearch = true;
		while (canSearch){
			PossibleTile pt = new PossibleTile(this.getX() + i, this.getY() + i, this);
			canSearch = decideToAddTile(b, possibleTiles, pt);
			i++;
		}
		return possibleTiles;
	}
}
