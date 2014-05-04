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
public class BruteSearch extends SearchAlgorithm implements Runnable {

    private int depth;
    private Tree<Board> tree;
    private int maxDepth;
    
    public BruteSearch(Board t, int md) {
        this.tree = new Tree<Board>(t);
        this.maxDepth = md;
        this.depth = 0;
        this.threadPool = Executors.newFixedThreadPool(10);
        this.isFinished = false;
    }
    private BruteSearch(Tree<Board> t, int d, ExecutorService tp) {
        this.tree = t;
        this.depth = d;
        this.threadPool = tp;
    }

    /**
     * Creates new BruteSearch objects for all of the leaves in a tree.
     */
    protected void search() {
        if (this.depth < maxDepth) {
            for (Tree<Board> b : tree.getLeaves()) {
                this.threadPool.submit(new BruteSearch(b, this.depth + 1, this.threadPool));
            }
        }
    }
    /**
     * This searches for all of the possible moves within a possible board and adds
     * these possibilities as leaves of that board.
     * It then searches that tree.
     */
    @Override
	public void run() {
		Board root = this.tree.getRoot();
	    for (Piece p : root.getPieces()) {
	    	if (p.isWhite() == root.isWhiteTurn()) {
		        for (PossibleTile pt : p.getPossibleTiles(root)) {
		            Piece newPiece = p.clone();
		            newPiece.setLocation(pt.getX(), pt.getY());
		            Board newBoard = root.clone();
		            newBoard.removePiece(p);
		            newBoard.addPiece(newPiece);
		            if (pt.hasPieceToRemove()){
		                newBoard.removePiece(pt.getRemovePiece());
		            }
		            tree.addLeaf(newBoard);
		        }
	    	}
	    }
	    search();
	    this.isFinished = true;
	}
}
