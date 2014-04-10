package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.piece.Piece;

/**
 * A user wants to promote it's pawn to another piece when reaching the end of the board.
 */
public class PawnPromotionView extends JPanel implements ActionListener, Callable<Piece> {

	private static final long serialVersionUID = 6061543872701062824L;
	private Piece p;
	
	public PawnPromotionView() {
		
	}
	
	/**
	 * The user has decided which piece to promote the pawn.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Probably? needed after this has been displayed. I can't think of another way
	 * in which for the player class get's the information of which piece the pawn will be.
	 */
	@Override
	public Piece call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
