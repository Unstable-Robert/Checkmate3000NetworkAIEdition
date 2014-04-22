package edu.mccc.cos210.fp2014.cm.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.player.Player;

/**
 * JPanel view of the board.
 * Also handles click actions and player selection.
 */
public class GameView extends JPanel implements Observer, ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private GameModel gm;
	private ArrayList<Player> players;
	private Checkmate myCheckmate;
	public GameView(Checkmate c) {
		myCheckmate = c;
		setBackground(Color.LIGHT_GRAY);
	}
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		Rectangle r = new Rectangle(155, 55, 490, 490);
		g2d.setPaint(Color.BLACK);
		g2d.fill(r);
		
		r = new Rectangle(160, 60, 480, 480);
		GradientPaint gp = new GradientPaint(0, 0, Color.BLACK, 350, 480, Color.DARK_GRAY);
		g2d.setPaint(gp);
		g2d.fill(r);
		
		g2d.setPaint(Color.WHITE);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if ( (x + y) % 2 == 0 ) {
					r = new Rectangle(x * 60 + 160, y * 60 + 60, 60, 60);
					g2d.fill(r);
				}
			}
		}
	}
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
