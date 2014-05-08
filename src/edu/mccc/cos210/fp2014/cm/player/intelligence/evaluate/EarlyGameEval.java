package edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.Bishop;
import edu.mccc.cos210.fp2014.cm.piece.King;
import edu.mccc.cos210.fp2014.cm.piece.Knight;
import edu.mccc.cos210.fp2014.cm.piece.Pawn;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.Queen;
import edu.mccc.cos210.fp2014.cm.piece.Rook;

public class EarlyGameEval extends EvaluationAlgorithm {

	public EarlyGameEval(Board b, int d, boolean iW) {
		super(b, d, iW);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	//simple eval with nummoves, dissuades queen from moving
	@Override
	public double getBoardValue(Board b) {
		double value = 0;
		for (Piece p : b.getPieces()) {
			if (p.isWhite() == this.isWhite){
					value = value + getLocationVal(p);
					if (p instanceof Bishop){
						value = value + 3 + getHomeRowVal(p);
					} else if (p instanceof King){
						value = value + 200;
					} else if (p instanceof Knight){
						value = value + 3 + getHomeRowVal(p);
					} else if (p instanceof Pawn){
						value = value + 1;
					} else if (p instanceof Queen){
						int hinderQueen;
						if (this.isWhite){
							hinderQueen = p.getY() - 7;
						} else {
							hinderQueen = -p.getY();
						}
						value = value + 9 + hinderQueen;
					} else if (p instanceof Rook){
						value = value + 5;
				}
			} else {
				value = value - getLocationVal(p);
				if (p instanceof Bishop){
					value = value - 3 - getHomeRowVal(p);
				} else if (p instanceof King){
					value = value - 200;
				} else if (p instanceof Knight){
					value = value - 3 - getHomeRowVal(p);
				} else if (p instanceof Pawn){
					value = value - 1;
				} else if (p instanceof Queen){
					value = value - 9;
				} else if (p instanceof Rook){
					value = value - 5;
				}
			}
			if (!(p instanceof Queen)){
				value = value + .1 * (double) p.getNumMoves();
			}
		}
		return value;
	}
	private double getLocationVal(Piece p) {
		return getAxisVal(p.getX()) + getAxisVal(p.getY());
	}
	private double getAxisVal(int loc){
		switch(loc){
			case 0:
			case 7:
				return -1;
			case 1:
			case 6:
				return -.5;
			case 2:
			case 5:
				return .5;
			case 3:
			case 4:
				return 2;
			default:
				return 0;
		}
	}
	private double getHomeRowVal(Piece p){
		if (p.isWhite()) {
			if (p.getY() == 7) {
				return -1;
			}
		} else {
			if (p.getY() == 0) {
				return -1;
			}
		}
		return 0;
	}
}
