package edu.mccc.cos210.fp2014.cm.player;

import java.awt.*;
import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.*;

import javax.swing.*;

/**
 * A local player object.
 * Updates the model when the view is updated.
 */
public class LocalPlayer extends Player {
	public LocalPlayer(GameModel gm, boolean b) {
		super(gm, b);
	}
	/**
	 * Updates the model.
	 */
	@Override
	public boolean updateModel(Piece piece, PossibleTile pt){
		return super.updateModel(piece, pt);
	}
	/**
	 * Called when the model is updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
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
					gm.getBoard().removePiece(p);
					gm.getBoard().addPiece(newPiece);
					gm.updateBoard(gm.getBoard());
				}
			}
		}
	}
}
