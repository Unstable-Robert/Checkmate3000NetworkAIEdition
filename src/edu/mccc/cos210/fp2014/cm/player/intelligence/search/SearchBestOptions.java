package edu.mccc.cos210.fp2014.cm.player.intelligence.search;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.piece.Pawn;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.piece.Queen;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class SearchBestOptions extends SearchAlgorithm {
	public SearchBestOptions(Board t, int md) {
		super(t, md);	
	}
	@Override
	public void run() {
	}
	@Override
	protected void search(int depth, Tree<Board> b) {
		ArrayList<PossibleTile> tiles;
		if (depth < maxDepth) {
			for (Piece p : b.getRoot().getPieces()) {
				if (p.isWhite() == b.getRoot().isWhiteTurn()) {
					int count = 0;
					for (PossibleTile pt : p.getPossibleTiles(b.getRoot())) {
						tiles = new ArrayList<PossibleTile>();
						tiles.add(new PossibleTile(p.getX(), p.getY(), p));
						tiles.add(pt);
						Piece newPiece = p.clone();
						newPiece.setLocation(pt.getX(), pt.getY());
						if (newPiece instanceof Pawn && ((Pawn) newPiece).canPromote()) {
							newPiece = new Queen(newPiece);
						}
						Board newBoard = b.getRoot().clone();
						newBoard.removePiece(p);
						newBoard.addPiece(newPiece);
						if (pt.hasPieceToRemove()) {
							Piece removedPiece = pt.getRemovePiece();
							newBoard.removePiece(removedPiece);
							/*if (newPiece instanceof Pawn) {
								if (!((Pawn)newPiece).canPromote()) {
									newBoard.addMove(new int[] {
										newPiece.getUID(), pt.getX(), pt.getY(), removedPiece.getUID()}
									);
								} else {
									newBoard.addMove(new int[] {newPiece.getUID(), pt.getX(), pt.getY()});
								}
							} else {
								newBoard.addMove(new int[] {newPiece.getUID(), pt.getX(), pt.getY()});
							}
						} else {
							newBoard.addMove(new int[] {newPiece.getUID(), pt.getX(), pt.getY()});*/
						}
						newBoard.setPrevTiles(tiles);
						b.addLeaf(newBoard);
						Tree<Board> leaf = b.getLeaf(newBoard);
						search(depth + 1, leaf);
						count++;
					}
					p.setNumMoves(count);
				}
			}
		}
	}
	@Override
	public void search(Tree<Board> t) {
		if (t.hasLeaves()) {
			for (Tree<Board> b : t.getLeaves()) {
				search(b);
			}
		} else {
			this.tree = t;
			this.search(0, t);
		}
	}
}
