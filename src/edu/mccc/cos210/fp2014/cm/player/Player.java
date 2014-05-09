package edu.mccc.cos210.fp2014.cm.player;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.piece.*;

/**
 * Abstract player class.
 * Generic player object, containing the update model and update methods in addition to 
 * some a game model and a color.
 */
public abstract class Player implements Observer {
	protected GameModel gm;
	protected boolean isWhite;
	protected Checkmate myCheckmate;
	public Player(GameModel gm, Checkmate c, boolean isWhite) {
		this.myCheckmate = c;
		this.gm = gm;
		this.isWhite = isWhite;
	}
	/**
	 * This class will update the game model.
	 */
	public boolean updateModel(Piece piece, PossibleTile pt) {
		for (Piece p : this.gm.getBoard().getPieces()) {
			if (p instanceof Pawn) {
				Pawn pawn = (Pawn) p;
				pawn.setPossibleToPassant(false);
			}
		}
		Board b = this.gm.getBoard();
		if (
			b.isWhiteTurn() == piece.isWhite() &&
			b.isWhiteTurn() == this.isWhite
		) {
			ArrayList<PossibleTile> tiles = new ArrayList<PossibleTile>();
			tiles.add(new PossibleTile(piece.getX(), piece.getY(), piece));
			tiles.add(pt);
			b.nextTurn();
			b.removePiece(piece);
			Piece clone = pt.getOriginalPiece();
			clone.setX(pt.getX());
			clone.setY(pt.getY());
			clone.setSelected(false);
			b.addPiece(clone);
			if (pt.hasPieceToRemove()) {
				Piece removedPiece = pt.getRemovePiece();
				b.removePiece(removedPiece);
 				this.gm.resetMoveRule();
				if (piece instanceof Pawn) {
					this.gm.resetMoveRule();
					if (!((Pawn)clone).canPromote()) {
						b.addMove(new int[] {clone.getUID(), pt.getX(), pt.getY(), removedPiece.getUID()});
					}
				} else {
					b.addMove(new int[] {clone.getUID(), pt.getX(), pt.getY()});
				}
			} else {
				if (piece instanceof Pawn) {
					this.gm.resetMoveRule();
					if (!((Pawn)clone).canPromote()) {
						b.addMove(new int[] {clone.getUID(), pt.getX(), pt.getY()});
					}
				} else {
					this.gm.increaseMoveRule();
					b.addMove(new int[] {clone.getUID(), pt.getX(), pt.getY()});
				}
			}
			this.gm.getBoard().setPrevTiles(tiles);
			this.gm.updateBoard(b, false);
			return true;
		}
		return false;
	}
	public boolean updateCastle(Piece p1, Piece p2) {
		Board b = gm.getBoard();
		if (
			b.isWhiteTurn() == p1.isWhite() &&
			b.isWhiteTurn() == this.isWhite
		) {
			ArrayList<PossibleTile> tiles = new ArrayList<PossibleTile>();
			b.nextTurn();
			Piece clone1 = p1.clone();
			Piece clone2 = p2.clone();
			b.removePiece(p1);
			b.removePiece(p2);
			if (p1.getX() < p2.getX()) {
				if (p1 instanceof King) {
					tiles.add(new PossibleTile(clone1.getX(), clone1.getY(), clone1));
					clone1.setX(p2.getX() - 1);
					clone2.setX(p1.getX() + 1);
					tiles.add(new PossibleTile(clone1.getX(), clone1.getY(), clone1));
					b.addMove(new int[] {clone1.getUID(), clone1.getX(), clone1.getY()});
				} else {
					tiles.add(new PossibleTile(clone2.getX(), clone2.getY(), clone2));
					clone1.setX(p2.getX() - 1);
					clone2.setX(p1.getX() + 2);
					tiles.add(new PossibleTile(clone2.getX(), clone2.getY(), clone2));
					b.addMove(new int[] {clone2.getUID(), clone2.getX(), clone2.getY()});
				}
			} else {
				if (p1 instanceof King) {
					tiles.add(new PossibleTile(clone1.getX(), clone1.getY(), clone1));
					clone1.setX(p2.getX() + 2);
					clone2.setX(p1.getX() - 1);
					tiles.add(new PossibleTile(clone1.getX(), clone1.getY(), clone1));
					b.addMove(new int[] {clone1.getUID(), clone1.getX(), clone1.getY()});
				} else {
					tiles.add(new PossibleTile(clone2.getX(), clone2.getY(), clone2));
					clone1.setX(p2.getX() + 1);
					clone2.setX(p1.getX() - 1);
					tiles.add(new PossibleTile(clone2.getX(), clone2.getY(), clone2));
					b.addMove(new int[] {clone2.getUID(), clone2.getX(), clone2.getY()});
				}
			}
			clone1.setSelected(false);
			b.addPiece(clone1);
			b.addPiece(clone2);
			this.gm.getBoard().setPrevTiles(tiles);
			gm.updateBoard(b, false);
			return true;
		}
		return false;
	}
	public Board checkPawnPromotion() {
		for (Piece p : this.gm.getBoard().getPieces()) {
			if (p instanceof Pawn && p.isWhite() == isWhite) {
				Pawn pawn = (Pawn) p;
				if (pawn.canPromote() && !pawn.isPromoted()) {
					pawn.setHasPromoted(true);
					JPanel panel = new JPanel(new GridLayout(2, 1));
					JLabel label = new JLabel("What would you like to promote your pawn to?");
					JComboBox<String> selection = new JComboBox<String>(
						new String[]{"Queen", "Knight", "Bishop", "Rook"}
					);
					String[] options = new String[]{"OK"};
					panel.add(label);
					panel.add(selection);
					int hasClosed = JOptionPane.CLOSED_OPTION;
					while (hasClosed == JOptionPane.CLOSED_OPTION) {
						hasClosed = JOptionPane.showOptionDialog(
							null, panel, "Pawn Promotion",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
							null, options, options[0]
						);
					}
					Board bClone = gm.getBoard().clone();
					Piece newPiece = null;
					switch (selection.getSelectedIndex()) {
					case 0:
						newPiece = new Queen(p);
						bClone.addMove(new int[] {newPiece.getUID(), newPiece.getX(), newPiece.getY(), -100});
						break;
					case 1:
						newPiece = new Knight(p);
						bClone.addMove(new int[] {newPiece.getUID(), newPiece.getX(), newPiece.getY(), -200});
						break;
					case 2:
						newPiece = new Bishop(p);
						bClone.addMove(new int[] {newPiece.getUID(), newPiece.getX(), newPiece.getY(), -300});
						break;
					case 3:
						newPiece = new Rook(p);
						bClone.addMove(new int[] {newPiece.getUID(), newPiece.getX(), newPiece.getY(), -400});
						break;
					default:
						newPiece = p;
						bClone.addMove(new int[] {newPiece.getUID(), newPiece.getX(), newPiece.getY()});
						break;
					}
					bClone.removePiece(p);
					bClone.addPiece(newPiece);
					bClone.setPrevTiles(gm.getBoard().getPrevTiles());
					return bClone;
				}
			}
		}
		return null;
	}
	public boolean isWhite() {
		return this.isWhite;
	}
}
