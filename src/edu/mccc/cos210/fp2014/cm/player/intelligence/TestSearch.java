package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.util.Tree;
import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Search algorithm test.
 * testing concept, all of this needs to be rewritten
 * This is a very stupid brute search algorithm. It iterates through all possible moves
 * with a MAXDEPTH and saves them in a Tree<Board>.
 * When actually implemented, we should probably decide to search only one depth, then 
 * evaluate, etc. We should also use an AiTree instead of a tree for evaluation purposes.
 */
public class TestSearch extends SearchAlgorithm implements Runnable {
	private int maxDepth;
	public TestSearch(Board t, int md) {
		this.tree = new Tree<Board>(t);
		this.maxDepth = md;
		this.threadPool = Executors.newFixedThreadPool(10);
		this.isFinished = false;
	}
	private TestSearch(Tree<Board> t, int d, ExecutorService tp) {
		this.tree = t;
		this.maxDepth = d;
		this.threadPool = tp;
	}
	/**
	 * Creates new TestSearch objects for all of the leaves in a tree.
	 */
	protected void search(int depth, Tree<Board> tree) {
		if (depth < maxDepth) {
			for (Piece p : tree.getRoot().getPieces()) {
				if (p.isWhite() == tree.getRoot().isWhiteTurn()) {
					for (PossibleTile pt : p.getPossibleTiles(tree.getRoot())) {
						Piece newPiece = p.clone();
						newPiece.setLocation(pt.getX(), pt.getY());
						Board newBoard = tree.getRoot().clone();
						newBoard.removePiece(p);
						newBoard.addPiece(newPiece);
						if (pt.hasPieceToRemove()){
							newBoard.removePiece(pt.getRemovePiece());
						}
						tree.addLeaf(newBoard);
						Tree<Board> leaf = tree.getLeaf(newBoard);
						search(depth + 1, leaf);
					}
				}
			}
		}
	}
	public void search(Tree<Board> t){
		this.tree = t;
		this.search(0, t);
	}
	/**
	 * This searches for all of the possible moves within a possible board and adds
	 * these possibilities as leaves of that board.
	 * It then searches that tree.
	 */
	@Override
	public void run() {
	}
}
