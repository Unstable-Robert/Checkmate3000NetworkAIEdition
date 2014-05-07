package edu.mccc.cos210.fp2014.cm.player.intelligence.search;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class SearchBestOptions extends SearchAlgorithm {
	
	public SearchBestOptions(Board t, int md) {
		super(t, md);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	@Override
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
		}
	}

	@Override
	public void search(Tree<Board> t) {
		if (t.hasLeaves()){
			for (Tree<Board> b : t.getLeaves()){
				search(b);
			}
		} else {
			this.tree = t;
    		this.search(0, t);
		}
	}

}
