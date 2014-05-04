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
				List<Tree<Board>> t = tree.getLeaves();
				for(int i = 0; i < t.size(); i++){
					int value = evaluate(t.get(i));
					bestValue = bestValue > value ? bestValue : value;
				}
			} else {
				bestValue = Integer.MAX_VALUE;
				List<Tree<Board>> t = tree.getLeaves();
				for(int i = 0; i < t.size(); i++){
					int value = evaluate(t.get(i));
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
		int myKing = 0, myQueen = 0, myBishop = 0, myKnight = 0, myRook = 0, 
			myPawn = 0, myMoves = 0;
		int yourKing = 0, yourQueen = 0, yourBishop = 0, yourKnight = 0, 
			yourRook = 0, yourPawn = 0, yourMoves = 0;
		for (Piece p : b.getPieces()) {
			if (isWhite == p.isWhite()){
				if (p instanceof Bishop){
					myBishop++;
				} else if (p instanceof King){
					myKing++;
				} else if (p instanceof Knight){
					myKnight++;
				} else if (p instanceof Pawn){
					myPawn++;
				} else if (p instanceof Queen){
					myQueen++;
				} else if (p instanceof Rook){
					myRook++;
				}
				myMoves += p.getPossibleTiles(b).size();
			} else {
				if (p instanceof Bishop){
					yourBishop++;
				} else if (p instanceof King){
					yourKing++;
				} else if (p instanceof Knight){
					yourKnight++;
				} else if (p instanceof Pawn){
					yourPawn++;
				} else if (p instanceof Queen){
					yourQueen++;
				} else if (p instanceof Rook){
					yourRook++;
				}
				yourMoves += p.getPossibleTiles(b).size();
			}
		}
		value = 200 * (myKing-yourKing) + 9 * (myQueen-yourQueen) + 5 * (myRook-yourRook) + 
			3 * (myBishop-yourBishop + myKnight-yourKnight) + 1*(myPawn-yourPawn) + 
			(int)(0.1*(myMoves-yourMoves));
		return value;
	}
	
}
