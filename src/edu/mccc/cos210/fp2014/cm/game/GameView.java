package edu.mccc.cos210.fp2014.cm.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.player.Player;

public class GameView extends JPanel implements Observer, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254471447199451521L;
	private GameModel gm;
	private Player p;
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void repaint(){
		
	}
}
