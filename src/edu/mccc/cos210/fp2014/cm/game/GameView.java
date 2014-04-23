package edu.mccc.cos210.fp2014.cm.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.*;
import javax.swing.JPanel;

import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.piece.*;
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
		players = new ArrayList<Player>();
		this.addMouseListener(this);
	}
	private BufferedImage image;
	public GameView(Checkmate c, GameModel model) {
		this(c);
		this.gm = model;
		setBackground(Color.LIGHT_GRAY);
		image = loadImage();
	}
	public void addPlayer(Player p){
		this.players.add(p);
	}
	private BufferedImage loadImage() {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(
				new FileInputStream("res/chess.gif")
			);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bi;
	}
	private void drawPiece(Graphics2D g2d, int x, int y, int gridX, int gridY) {
		g2d.drawImage(
				image,
				x,
				y,
				x + 60,
				y + 60,
				gridX,
				gridY,
				gridX + 60,
				gridY + 60,
				null
			);
	}
	private void drawPossibleTile(Graphics2D g2d, int x, int y) {
		g2d.setColor(Color.CYAN);
		g2d.setStroke(new BasicStroke(3));
		Rectangle2D s = new Rectangle2D.Double(x, y, 60, 60);
		g2d.draw(s);
	}
	/**
	 * Updates the view when the model updates.
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.paintComponent(this.getGraphics());
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
		GradientPaint gp = new GradientPaint(0, 0, Color.DARK_GRAY, 350, 480, Color.GRAY);
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
		
		g2d.setFont(new Font(g2d.getFont().toString(), Font.PLAIN, 60));
		g2d.setPaint(Color.WHITE);
		List<Piece> pieces = gm.getBoard().getPieces();
		int gridX, gridY;
		for (Piece p: pieces) {
			if (p instanceof Pawn) {
				gridX = 0;
			} else if (p instanceof Rook) {
				gridX = 60;
			} else if (p instanceof Knight) {
				gridX = 120;
			} else if (p instanceof Bishop) {
				gridX = 180;
			} else if (p instanceof Queen) {
				gridX = 240;
			} else {
				gridX = 300;
			}
			if (p.getColor()) {
				gridY = 0;
			} else {
				gridY = 60;
			}
			drawPiece(g2d, p.getX() * 60 + 160, p.getY() * 60 + 60, gridX, gridY);
		}
		for (PossibleTile pt : gm.getBoard().getPossibleTiles()){
			drawPossibleTile(g2d, pt.getX() * 60 + 160, pt.getY() * 60 + 60);
		}
		g2d.dispose();
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
		int x = e.getX();
		int y = e.getY();
		boolean found = false;
		Piece piece = null;
		PossibleTile possibleTile = null;
		for (Piece p : this.gm.getBoard().getPieces()){
			if (x > (p.getX() + 1) * 60 + 100 && x < (p.getX() + 2) * 60 + 100 &&
					y > (p.getY() + 1) * 60 && y < (p.getY() + 2) * 60) {
				found = true;
				piece = p;
				break;
			}
		}
		for (PossibleTile pt : this.gm.getBoard().getPossibleTiles()){
			if (x > (pt.getX() + 1) * 60 + 100 && x < (pt.getX() + 2) * 60 + 100 &&
					y > (pt.getY() + 1) * 60 && y < (pt.getY() + 2) * 60) {
				found = true;
				possibleTile = pt;
				break;
			}
		}
		if (found) {
			if (possibleTile != null) {
				if (this.gm.getBoard().hasSelectedPiece()){
					Piece p = this.gm.getBoard().getSelectedPiece();
					p.setSelected(false);
					for(Player player : this.players) {
						player.updateModel(p, possibleTile);
					}
				}
			}else if (piece != null) {
				Board b = this.gm.getBoard();
				b.clearSelected();
				piece.setSelected(true);
				this.gm.updateBoard(b);
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
