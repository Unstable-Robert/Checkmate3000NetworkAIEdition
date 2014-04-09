package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.piece.Piece;

/**
 * 
 */
public class PawnPromotionView extends JPanel implements ActionListener, Callable<Piece> {

	private static final long serialVersionUID = 6061543872701062824L;
	private Piece p;
	
	/**
	 * 
	 */
	public PawnPromotionView() {
		
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 */
	@Override
	public Piece call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
