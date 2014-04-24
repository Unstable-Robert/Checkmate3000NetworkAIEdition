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
        for (Piece p : this.gm.getBoard().getPieces()){
            if (p instanceof Pawn){
               if (((Pawn) p).canPromote()){
                   ((Pawn) p).setHasPromoted(true);
                   JPanel panel = new JPanel(new GridLayout(2, 1));
                   JLabel label = new JLabel("What would you like to promote your pawn to?");
                   JComboBox selection = new JComboBox(new String[]{"Queen","Knight","Bishop","Rook"});
                   String[] options = {"OK"};
                   panel.add(label);
                   panel.add(selection);
                   int Selected = JOptionPane.showOptionDialog(null, panel, "Pawn Promotion",
                                  JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                  null, options, options[0]);
                   Piece newPiece = null;
                   switch (Selected){
                       case 0: newPiece = new Queen(p);
                           break;
                       case 1: newPiece = new Knight(p);
                           break;
                       case 2: newPiece = new Bishop(p);
                           break;
                       case 3: newPiece = new Rook(p);
                           break;
                   }
                   PossibleTile tile = new PossibleTile(p.getX(),p.getY(),p,p);
                   updateModel(newPiece, tile);

               }
            }
        }
	}
}
