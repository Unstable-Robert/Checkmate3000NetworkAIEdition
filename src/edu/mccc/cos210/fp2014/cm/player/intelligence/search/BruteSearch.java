package edu.mccc.cos210.fp2014.cm.player.intelligence.search;

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
    
    public BruteSearch(Board t, int md) {
        super(t, md);
    }
    /**
     * Creates new BruteSearch objects for all of the leaves in a tree.
     */
    protected void search(int depth, Tree<Board> b) {
        if (depth < maxDepth) {
    	    for (Piece p : b.getRoot().getPieces()) {
    	    	if (p.isWhite() == b.getRoot().isWhiteTurn()) {
    	    		int count = 0;
    		        for (PossibleTile pt : p.getPossibleTiles(b.getRoot())) {
    		            Piece newPiece = p.clone();
    		            newPiece.setLocation(pt.getX(), pt.getY());
    		            Board newBoard = b.getRoot().clone();
    		            newBoard.removePiece(p);
    		            newBoard.addPiece(newPiece);
    		            if (pt.hasPieceToRemove()){
    		                newBoard.removePiece(pt.getRemovePiece());
    		            }
    		            b.addLeaf(newBoard);
    		            Tree<Board> leaf = b.getLeaf(newBoard);
    		    	    search(depth + 1, leaf);
    		    	    count ++;
    		        }
    		        p.setNumMoves(count);
    	    	}
    	    }
            //for (Tree<Board> b : tree.getLeaves()) {
              //  this.threadPool.submit(new BruteSearch(b, this.depth + 1, this.threadPool));
            //}
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