package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.concurrent.ExecutorService;

import edu.mccc.cos210.fp2014.cm.util.Tree
import edu.mccc.cos210.fp2014.cm.game.Board

/**
 * testing concept, all of this needs to be rewritten
 */
public class BruteSearch implements Runnable{

    private int depth;
    private int Tree<Board> tree;
    private static final int MAXDEPTH = 5;
    
    public BruteSearch(Tree<Board> t) {
        this.tree = t;
        this.depth = 0;
        this.threadPool = new FixedExecutorService(10);
    }
    private BruteSearch(Tree<Board> t, int d, ExecutorService tp) {
        this.tree = t;
        this.depth = d;
        this.threadPool = tp;
    }

    private void search() {
        if (this.depth < MAXDEPTH) {
            for (Tree<Board> b : tree.getLeaves()) {
                BruteSearch.threadPool.submit(new BruteSearch(b, this.depth + 1, this.tp));
            }
        }
    }
    @Override
	public void run() {
	    for (Piece p : tree.getRoot().getPieces()) {
	        for (PossibleTile pt : p.getPossibleTiles) {
	            Piece newPiece = p.clone();
	            newPiece.setLocation(pt.getX, pt.getY);
	            Board newBoard = b.clone();
	            newBoard.removePiece(p);
	            newBoard.addPiece(newPiece);
	            if (pt.hasPieceToRemove(){
	                newBoard.removePiece(pt.getRemovePiece();
	            }
	            tree.addLeaf(newBoard);
	        }
	    }
	    search();
	}
}
