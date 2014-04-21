package edu.mccc.cos210.fp2014.cm.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.player.Player;

/**
 * JPanel view of the board.
 * Also handles click actions and player selection.
 */
public class GameView extends JPanel implements Observer, ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private GameModel gm;
	private ArrayList<Player> players;
	public void addPlayer(Player p){
		this.players.add(p);
	}
	/**
	 * Updates the view when the model updates.
	 */
	@Override
	public void update(Observable o, Object arg) {
	}
	/**
	 * Checks is the resign button was pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	/**
	 * Draws game.
	 * Draws board, pieces, times, whose turn it is.
	 */
	@Override
	public void repaint(){
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
