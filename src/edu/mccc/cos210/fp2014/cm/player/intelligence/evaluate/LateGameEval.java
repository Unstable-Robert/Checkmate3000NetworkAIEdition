package edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.Bishop;
import edu.mccc.cos210.fp2014.cm.piece.King;
import edu.mccc.cos210.fp2014.cm.piece.Knight;
import edu.mccc.cos210.fp2014.cm.piece.Pawn;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.Queen;
import edu.mccc.cos210.fp2014.cm.piece.Rook;

public class LateGameEval extends EvaluationAlgorithm {
	public LateGameEval(Board b, int d, boolean iW) {
		super(b, d, iW);
	}
	@Override
	public void run() {
	}
	@Override
	public double getBoardValue(Board b) {
		double value = 0;
		for (Piece p : b.getPieces()) {
			if (p.isWhite() == this.isWhite) {
				if (p instanceof Bishop) {
					value = value + 3;
				} else if (p instanceof King) {
					value = value + 200 + getKingVal(p);
				} else if (p instanceof Knight) {
					value = value + 3;
				} else if (p instanceof Pawn) {
					value = value + 1 + getPawnVal(p);
				} else if (p instanceof Queen) {
					value = value + 9;
				} else if (p instanceof Rook) {
					value = value + 5;
				}
			} else {
				if (p instanceof Bishop) {
					value = value - 3;
				} else if (p instanceof King) {
					value = value - 200 - getKingVal(p);
				} else if (p instanceof Knight) {
					value = value - 3;
				} else if (p instanceof Pawn) {
					value = value - 1 - getPawnVal(p);
				} else if (p instanceof Queen) {
					value = value - 9;
				} else if (p instanceof Rook) {
					value = value - 5;
				}
			}
		}
		return value;
	}
	private int getKingVal(Piece p) {
		return getKingAxisVal(p.getX() + getKingAxisVal(p.getY()));
	}
	private int getKingAxisVal(int loc) {
		switch(loc) {
		case 0:
		case 7:
			return -4;
		case 1:
		case 6:
			return -2;
		case 2:
		case 5:
			return 2;
		case 3:
		case 4:
			return 4;
		default:
			return 0;
		}
	}
	private int getPawnVal(Piece p) {
		if (p.isWhite()) {
			return -p.getY() + 7;
		} else {
			return p.getY();
		}
	}
}
