package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.List;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.*;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class SimpleEval extends EvaluationAlgorithm {

	public SimpleEval(Board b, int d, boolean iW){
		this.isWhite = iW;
	}
	@Override
	public void run() {

	}

	@Override
	protected double evaluate(Tree<Board> tree, boolean maxPlayer) {
		Board b = tree.getRoot();
		if (tree.getLeaves().isEmpty()){
			this.isFinished = true;
			return getBoardValue(b);
		}else {
			double bestValue;
			if (maxPlayer){
				bestValue = Integer.MIN_VALUE;
				List<Tree<Board>> t = tree.getLeaves();
				for(int i = 0; i < t.size(); i++){
					double value = evaluate(t.get(i), false);
					bestValue = bestValue > value ? bestValue : value;
				}
			} else {
				bestValue = Integer.MAX_VALUE;
				List<Tree<Board>> t = tree.getLeaves();
				for(int i = 0; i < t.size(); i++){
					double value = evaluate(t.get(i), true);
					bestValue = bestValue < value ? bestValue : value; 
				}
			}
			tree.setScore(bestValue);
			this.isFinished = true;
			return bestValue;
		}
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
