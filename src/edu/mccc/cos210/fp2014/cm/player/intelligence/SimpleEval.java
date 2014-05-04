package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.*;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class SimpleEval extends EvaluationAlgorithm {

	public SimpleEval(Board b, int d, boolean iW){
		this.isWhite = iW;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	protected int evaluate(Tree<Board> tree) {
		Board b = tree.getRoot();
		if (tree.getLeaves().isEmpty()){
			this.isFinished = true;
			return getBoardValue(b);
		}else {
			int bestValue;
			if (b.isWhiteTurn()==this.isWhite){
				bestValue = Integer.MIN_VALUE;
				for(Tree<Board> t : tree.getLeaves()){
					int value = evaluate(t);
					bestValue = bestValue > value ? bestValue : value;
				}
			} else {
				bestValue = Integer.MAX_VALUE;
				for (Tree<Board> t : tree.getLeaves()){
					int value = evaluate(t);
					bestValue = bestValue < value ? bestValue : value; 
				}
			}
			tree.setScore(bestValue);
			this.isFinished = true;
			return bestValue;
		}
	}
	@Override
	public Board getBest() {
		Board best = null;
		int bestInt = Integer.MIN_VALUE;
		for (Tree<Board> b : this.tree.getLeaves()){
			if (b.getScore() > bestInt){
				best = b.getRoot();
				bestInt = b.getScore();
			}
		}
		return best;
	}
	@Override
	public int getBoardValue(Board b) {
		int value = 0;
		for (Piece p : b.getPieces()) {
				if (p instanceof Bishop){
					value += 3;
				} else if (p instanceof King){
					
				} else if (p instanceof Knight){
					value += 3;
				} else if (p instanceof Pawn){
					value += 1;
				} else if (p instanceof Queen){
					value += 10;
				} else if (p instanceof Rook){
					value += 5;
				} 
			
		}
		return value;
	}
	
}
