package edu.mccc.cos210.fp2014.cm.player;

import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.piece.Bishop;
import edu.mccc.cos210.fp2014.cm.piece.King;
import edu.mccc.cos210.fp2014.cm.piece.Knight;
import edu.mccc.cos210.fp2014.cm.piece.Pawn;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.piece.Queen;
import edu.mccc.cos210.fp2014.cm.piece.Rook;

/**
 * Abstract player class.
 * Generic player object, containing the update model and update methods in addition to 
 * some a game model and a color.
 */
public abstract class Player implements Observer{
	protected GameModel gm;
	protected boolean isWhite;
	protected Checkmate myCheckmate;
	public Player() {
	}
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
			if (p instanceof Pawn){
				Pawn pawn = (Pawn) p;
				pawn.setPossibleToPassant(false);
			}
		}
		Board b = gm.getBoard();
		if (
			b.isWhiteTurn() == piece.isWhite() &&
			b.isWhiteTurn() == this.isWhite
		){
			b.nextTurn();
			b.removePiece(piece);
			Piece clone = pt.getOriginalPiece();
			clone.setX(pt.getX());
			clone.setY(pt.getY());
			clone.setSelected(false);
			b.addPiece(clone);
			if (pt.hasPieceToRemove()) {
				b.removePiece(pt.getRemovePiece());
 				this.gm.resetMoveRule();
				if (piece instanceof Pawn){
					if (!((Pawn)clone).canPromote()){
						b.addMove(clone.getUID() + "," + pt.getX() + "," + pt.getY());
					}
				} else if (piece instanceof Pawn) {
					this.gm.resetMoveRule();
					if (!((Pawn)clone).canPromote()) {
						b.addMove(clone.getUID() + "," + pt.getX() + "," + pt.getY());
					}
				} else {
					b.addMove(clone.getUID() + "," + pt.getX() + "," + pt.getY());
				}
			} else {
				this.gm.increaseMoveRule();
				b.addMove(clone.getUID() + "," + pt.getX() + "," + pt.getY());
			}
			gm.updateBoard(b, false);
            System.out.println(b.getMoves());
			return true;
		}
		return false;
	}
	public boolean updateCastle(Piece p1, Piece p2) {
		Board b = gm.getBoard();
		if (
			b.isWhiteTurn() == p1.isWhite() &&
			b.isWhiteTurn() == this.isWhite
		){
			b.nextTurn();
			Piece clone1 = p1.clone();
			Piece clone2 = p2.clone();
			b.removePiece(p1);
			b.removePiece(p2);
			if (p1.getX() < p2.getX()) {
                b.addMove("0-0");
				if (p1 instanceof King) {
					clone1.setX(p2.getX() - 1);
					clone2.setX(p1.getX() + 1);
				} else {
					clone1.setX(p2.getX() - 1);
					clone2.setX(p1.getX() + 2);
				}
			} else {
                b.addMove("0-0-0");
				if (p1 instanceof King){
					clone1.setX(p2.getX() + 2);
					clone2.setX(p1.getX() - 1);
				} else {
					clone1.setX(p2.getX() + 1);
					clone2.setX(p1.getX() - 1);
				}
			}
			clone1.setSelected(false);
			b.addPiece(clone1);
			b.addPiece(clone2);
			gm.updateBoard(b, false);
			return true;
		}
		return false;
	}
	public Board checkPawnPromotion() {
		for (Piece p : this.gm.getBoard().getPieces()) {
			if (p instanceof Pawn){
				Pawn pawn = (Pawn) p;
				if (pawn.canPromote() && !pawn.isPromoted()) {
					pawn.setHasPromoted(true);
					JPanel panel = new JPanel(new GridLayout(2, 1));
					JLabel label = new JLabel("What would you like to promote your pawn to?");
					JComboBox<String> selection = new JComboBox<String>(new String[]{"Queen","Knight","Bishop","Rook"});
					String[] options = new String[]{"OK"};
					panel.add(label);
					panel.add(selection);
					JOptionPane.showOptionDialog(null, panel, "Pawn Promotion",
								  JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
								  null, options, options[0]);
					Piece newPiece = null;
					switch (selection.getSelectedIndex()) {
					case 0:
						newPiece = new Queen(p);
						break;
					case 1:
						newPiece = new Knight(p);
						break;
					case 2:
						newPiece = new Bishop(p);
						break;
					case 3:
						newPiece = new Rook(p);
						break;
					default:
						break;
					}
					Board bClone = gm.getBoard().clone();
					bClone.removePiece(p);
					bClone.addPiece(newPiece);
                    bClone.addMove(newPiece.getUID()+":"+newPiece.locToString());
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
