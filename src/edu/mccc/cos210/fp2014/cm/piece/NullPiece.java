package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;

public class NullPiece extends Piece {

	public NullPiece() {
		// TODO Auto-generated constructor stub
	}

	public NullPiece(int x, int y, boolean c, int iD) {
		// TODO Auto-generated constructor stub
	}

	public NullPiece(int x, int y, boolean c, int iD, boolean s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ArrayList<PossibleTile> getLazyTiles(Board b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
