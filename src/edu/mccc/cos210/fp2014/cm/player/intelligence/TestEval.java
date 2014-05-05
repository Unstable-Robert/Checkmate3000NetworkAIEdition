package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.List;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.*;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class TestEval extends EvaluationAlgorithm {
	public TestEval(Board b, int d, boolean white) {
		this.isWhite = white;
	}
	@Override
	public void run() {
	}
	@Override
	protected int evaluate(Tree<Board> tree) {
		return evaluate(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	private int evaluate(Tree<Board> tree, int alpha, int beta) {
		Board b = tree.getRoot();
		if (tree.getLeaves().isEmpty()) {
			this.isFinished = true;
			return getBoardValue(b) * (b.isWhiteTurn() == this.isWhite ? 1 : -1);
		}
		// Negascout Algorithm
		List<Tree<Board>> t = tree.getLeaves();
		int score;
		for(int i = 0; i < t.size(); i++) {
			if (i > 0) {
				score = -evaluate(t.get(i), -alpha-1, alpha);
				if (alpha < score && score < beta) {
					score = -evaluate(t.get(i), -beta, -score);
				}
			} else {
				score = -evaluate(t.get(i), -beta, -alpha);
			}
			if (score > alpha) {
				alpha = score;
			}
			if (alpha >= beta) {
				tree.removeLeaf(t.get(i)); // I believe this line needs to be changed.
			}
		}
		return alpha;
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
		int myKing = 0, myQueen = 0, myBishop = 0, myKnight = 0, myRook = 0, 
			myPawn = 0, myMoves = 0;
		int yourKing = 0, yourQueen = 0, yourBishop = 0, yourKnight = 0, 
			yourRook = 0, yourPawn = 0, yourMoves = 0;
		List<Piece> pieces = b.getPieces();
		for (Piece p : pieces) {
			if (isWhite == p.isWhite()) {
				if (p instanceof Bishop) {
					myBishop++;
				} else if (p instanceof King) {
					myKing++;
				} else if (p instanceof Knight) {
					myKnight++;
				} else if (p instanceof Pawn) {
					myPawn++;
				} else if (p instanceof Queen) {
					myQueen++;
				} else if (p instanceof Rook) {
					myRook++;
				}
			} else {
				if (p instanceof Bishop) {
					yourBishop++;
				} else if (p instanceof King) {
					yourKing++;
				} else if (p instanceof Knight) {
					yourKnight++;
				} else if (p instanceof Pawn) {
					yourPawn++;
				} else if (p instanceof Queen) {
					yourQueen++;
				} else if (p instanceof Rook) {
					yourRook++;
				}
			}
		}
		int value = 200 * (myKing - yourKing) +
					  9 * (myQueen - yourQueen) +
					  5 * (myRook - yourRook) +
					  3 * (myBishop - yourBishop + myKnight - yourKnight) +
					      (myPawn - yourPawn);
		return value;
	}
}
