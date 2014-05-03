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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.menu.SettingsView;
import edu.mccc.cos210.fp2014.cm.piece.*;
import edu.mccc.cos210.fp2014.cm.player.NetworkPlayer;
import edu.mccc.cos210.fp2014.cm.player.Player;
import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * JPanel view of the a logged game
 */
public class LogView extends SettingsView implements Observer {
	private static final long serialVersionUID = 1L;
	private Board board;
	private List<PossibleTile> movesOrSomething = new ArrayList<PossibleTile>();
	private BufferedImage image;
	private int turnNum;
	public LogView(Checkmate c) {
		super(c);
		
		turnNum = 0;
		board = new Board(GameType.NORMAL);
		image = loadImage();
		
		JButton backButton = new JButton("Main Menu");
		backButton.setSize(100, 50);
		backButton.setLocation((int)(c.getWidth() * 0.05), (int)(c.getHeight() * 0.10));
		backButton.setVisible(true);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(backButton);
		
		JButton loadButton = new JButton("Load Log");
		loadButton.setSize(100, 50);
		loadButton.setLocation((int)(c.getWidth() * 0.05), (int)(c.getHeight() * 0.20));
		loadButton.setVisible(true);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fc = new JFileChooser(new File("logs"));
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileFilter(
					new FileNameExtensionFilter("Checkmate 3000 Logs", "cm3")
				);
				int result = fc.showOpenDialog(LogView.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						loadFile(fc.getSelectedFile());
					} catch (Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		add(loadButton);
		
		JButton nextButton = new JButton(">");
		nextButton.setSize(45, 45);
		nextButton.setLocation((int)(c.getWidth() * 0.55), (int)(c.getHeight() * 0.865));
		nextButton.setVisible(true);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				turnNum++;
				repaint();
			}
		});
		add(nextButton);
		
		JButton previousButton = new JButton("<");
		previousButton.setSize(45, 45);
		previousButton.setLocation((int)(c.getWidth() * 0.40), (int)(c.getHeight() * 0.865));
		previousButton.setVisible(true);
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (turnNum > 0) {
					turnNum--;
					repaint();
				}
			}
		});
		add(previousButton);
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
	private void loadFile(File f) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s = br.readLine();
		br.close();
		Pattern p = Pattern.compile("[0-9]+[.]|:");
		Matcher m = p.matcher(s);
		s = m.replaceAll("");
		String[] moves = s.split(" ");
		for (int i = 0; i < moves.length; i++) {
			if (i% 2 == 0) {
				//white's move
			} else {
				//black's move
			}
			// check for enpassant, castle
		}
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
	/**
	 * Updates the view when the model updates.
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.paintComponent(this.getGraphics());
	}
	/**
	 * Draws game.
	 * Draws board, pieces, times, whose turn it is.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		Rectangle2D r = new Rectangle2D.Double(155, 55, 490, 490);
		g2d.setPaint(Color.BLACK);
		g2d.fill(r);
		
		r = new Rectangle2D.Double(160, 60, 480, 480);
		GradientPaint gp = new GradientPaint(0, 0, Color.DARK_GRAY, 350, 480, Color.GRAY);
		g2d.setPaint(gp);
		g2d.fill(r);
		
		g2d.setPaint(Color.WHITE);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if ( (x + y) % 2 == 0 ) {
					r = new Rectangle2D.Double(x * 60 + 160, y * 60 + 60, 60, 60);
					g2d.fill(r);
				}
			}
		}
		
		g2d.setFont(new Font(g2d.getFont().toString(), Font.PLAIN, 28));
		if (board.isWhiteTurn()) {
			g2d.drawString("Turn " + turnNum, myCheckmate.getWidth() * .02f, myCheckmate.getHeight() * .05f);
		}
		
		List<Piece> pieces = board.getPieces();
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
			if (p.isWhite()) {
				gridY = 60;
			} else {
				gridY = 0;
			}
			drawPiece(g2d, p.getX() * 60 + 160, p.getY() * 60 + 60, gridX, gridY);
		}
		this.paintChildren(g);
		g2d.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
