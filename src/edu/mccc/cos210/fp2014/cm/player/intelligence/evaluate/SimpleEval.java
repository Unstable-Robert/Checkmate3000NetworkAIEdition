package edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.*;

public class SimpleEval extends EvaluationAlgorithm {

	public SimpleEval(Board b, int d, boolean iW){
		super(b, d, iW);
	}
	@Override
	public void run() {

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
		}
		return value;
	}
}
