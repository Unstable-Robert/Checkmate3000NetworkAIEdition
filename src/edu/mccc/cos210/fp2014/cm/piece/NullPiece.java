package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.game.Board;

@XmlRootElement(name="NullPiece")
@XmlAccessorType(XmlAccessType.FIELD)
public class NullPiece extends Piece {
	public NullPiece() {
	}
	public NullPiece(int x, int y, boolean c, int iD) {
	}
	public NullPiece(int x, int y, boolean c, int iD, boolean s) {
	}
	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		return null;
	}
	@Override
	public Piece clone() {
		return null;
	}
}
