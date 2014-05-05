package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.Bishop;
import edu.mccc.cos210.fp2014.cm.piece.King;
import edu.mccc.cos210.fp2014.cm.piece.Knight;
import edu.mccc.cos210.fp2014.cm.piece.Pawn;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.Queen;
import edu.mccc.cos210.fp2014.cm.piece.Rook;

public class SimpleEvalWithNumMoves extends SimpleEval {
	public SimpleEvalWithNumMoves(Board b, int d, boolean iW) {
		super(b, d, iW);
	}

	@Override
	public double getBoardValue(Board b) {
		double value = 0;
		for (Piece p : b.getPieces()) {
			if (p.isWhite() == this.isWhite){
					if (p instanceof Bishop){
						value = value + 3;
					} else if (p instanceof King){
						value = value + 200;
					} else if (p instanceof Knight){
						value = value + 3;
					} else if (p instanceof Pawn){
						value = value + 1;
					} else if (p instanceof Queen){
						value = value + 9;
					} else if (p instanceof Rook){
						value = value + 5;
				}
			} else {
				if (p instanceof Bishop){
					value = value - 3;
				} else if (p instanceof King){
					value = value - 200;
				} else if (p instanceof Knight){
					value = value - 3;
				} else if (p instanceof Pawn){
					value = value - 1;
				} else if (p instanceof Queen){
					value = value - 9;
				} else if (p instanceof Rook){
					value = value - 5;
				}
			}
			value = value + .1 * (double) p.getPossibleTiles(b).size();
		}
		return value;
	}
}
