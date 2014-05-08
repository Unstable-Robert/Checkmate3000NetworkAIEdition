package edu.mccc.cos210.fp2014.cm.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
import edu.mccc.cos210.fp2014.cm.util.GameType;
import edu.mccc.cos210.fp2014.cm.util.GameResult;

/**
 * JPanel view of the a logged game
 */
public class LogView extends SettingsView implements Observer {
	private static final long serialVersionUID = 1L;
	private List<Board> moves;
	private BufferedImage image;
	private int turnNum;
	private int maxTurn;
	private int[] removedPiece;
	private boolean whiteWon;
    private GameResult winner;
	public LogView(Checkmate c) {
		super(c);

		turnNum = 0;
		moves = new ArrayList<Board>();
		moves.add(new Board(GameType.NORMAL));
		image = loadImage();

		JButton backButton = new JButton("Main Menu");
		backButton.setSize(100, 50);
		backButton.setLocation((int)(c.getWidth() * 0.035), (int)(c.getHeight() * 0.10));
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
		loadButton.setLocation((int)(c.getWidth() * 0.035), (int)(c.getHeight() * 0.20));
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
						JOptionPane.showMessageDialog(
							myCheckmate,
							"File successfully loaded.",
							"File Loaded",
							JOptionPane.DEFAULT_OPTION
						);
					} catch (Exception ex){
						JOptionPane.showMessageDialog(
							myCheckmate,
							"The file you have chosen is not a valid Checkmate 3000 game log.",
							"Invalid Checkmate 3000 Game Log",
							JOptionPane.WARNING_MESSAGE
						);
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
                boolean endGameVis = false;
				if (turnNum < moves.size()-1) {
					turnNum++;
					repaint();
				}
                if (turnNum == moves.size()-1 && !endGameVis){
                    String message = "";
                    switch(winner){
                        case WhiteWon:
                            message = "White Won!!";
                            break;
                        case BlackWon:
                            message = "Black Won!!";
                            break;
                        case DrawGame:
                            message = "The game ended in a draw.";
                            break;
                    }
                    String[] options = new String[]{"Load New Log", "Reset", "Okay"};
                    int gameOverAction = JOptionPane.showOptionDialog(
                        null, message, "Log Over",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]
                    );
                    if (gameOverAction == 1){
                        turnNum = 0;
                        repaint();
                    }
                    if (gameOverAction == 0){
                        JFileChooser fc = new JFileChooser(new File("logs"));
                        fc.setAcceptAllFileFilterUsed(false);
                        fc.setFileFilter(
                            new FileNameExtensionFilter("Checkmate 3000 Logs", "cm3")
                        );
                        int result = fc.showOpenDialog(LogView.this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            try {
                                loadFile(fc.getSelectedFile());
                                JOptionPane.showMessageDialog(
                                    myCheckmate,
                                    "File successfully loaded.",
                                    "File Loaded",
                                    JOptionPane.DEFAULT_OPTION
                                );
                            } catch (Exception ex){
                                JOptionPane.showMessageDialog(
                                    myCheckmate,
                                    "The file you have chosen is not a valid Checkmate 3000 game log.",
                                    "Invalid Checkmate 3000 Game Log",
                                    JOptionPane.WARNING_MESSAGE
                                );
                            }
                        }
                    }
                }
			}
		});
		add(nextButton);

		final JButton previousButton = new JButton("<");
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
		ArrayList<Board> newMoves = new ArrayList<Board>();
		newMoves.add(new Board(GameType.NORMAL));
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s = br.readLine();
		br.close();
		String[] sArray1 = s.split(";");
        this.winner = GameResult.fromInt(Integer.parseInt(Character.toString(sArray1[sArray1.length-1].charAt(0))));
        String[] sArray = new String[sArray1.length-1];
        for (int x = 0; x < sArray1.length-1; x++) sArray[x] = sArray1[x];
		String[] moveElements;
		int uid, x, y, specialInfo;
		ArrayList<Piece> pieces;
		Piece originalPiece = null, newPiece = null, removedPiece = null;
		for (int i = 0; i < sArray.length; i++) {
			specialInfo = -1;
			moveElements = sArray[i].split(",");
			uid = Integer.parseInt(moveElements[0]);
			x = Integer.parseInt(moveElements[1]);
			y = Integer.parseInt(moveElements[2]);
			if (moveElements.length == 4) {
				specialInfo = Integer.parseInt(moveElements[3]);
			}
			Board newBoard = newMoves.get(i).clone();
			pieces = newMoves.get(i).getPieces();
			removedPiece = null;
			for (Piece p : pieces) {
				if (p.getUID() == uid) {
					originalPiece = p;
					if (specialInfo < -1) {
						switch (specialInfo) {
						case -100:
							newPiece = new Queen(originalPiece.clone());
							break;
						case -200:
							newPiece = new Knight(originalPiece.clone());
							break;
						case -300:
							newPiece = new Bishop(originalPiece.clone());
							break;
						case -400:
							newPiece = new Rook(originalPiece.clone());
							break;
						default:
							newPiece = originalPiece.clone();
							break;
						}
					} else {
						newPiece = originalPiece.clone();
					}
				} else if (p.getX() == x && p.getY() == y) {
					removedPiece = p;
				}
				if (specialInfo >= 0) {
					if (p.getUID() == specialInfo) {
						removedPiece = p;
					}
				}
			}
			if (originalPiece instanceof King) {
				if (originalPiece.getX() == 4) {
					if (x == 6) {
						for (Piece p : pieces) {
							if (p instanceof Rook && p.isWhite() == originalPiece.isWhite() && p.getX() == 7) {
								Piece rook = p.clone();
								newBoard.removePiece(p);
								rook.setX(5);
								newBoard.addPiece(rook);
							}
						}
					} else if (x == 2) {
						for (Piece p : pieces) {
							if (p instanceof Rook && p.isWhite() == originalPiece.isWhite() && p.getX() == 0) {
								Piece rook = p.clone();
								newBoard.removePiece(p);
								rook.setX(3);
								newBoard.addPiece(rook);
							}
						}
					}
				}
			}
			newPiece.setLocation(x, y);
			ArrayList<PossibleTile> tiles = new ArrayList<PossibleTile>();
			tiles.add(new PossibleTile(originalPiece.getX(), originalPiece.getY(), originalPiece));
			tiles.add(new PossibleTile(newPiece.getX(), newPiece.getY(), newPiece));
			newBoard.setPrevTiles(tiles);
			newBoard.removePiece(originalPiece);
			newBoard.addPiece(newPiece);
			if (removedPiece != null){
				newBoard.removePiece(removedPiece);
			}
			newMoves.add(newBoard);
		}
		turnNum = 0;
		moves = newMoves;
		repaint();
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
	private void drawPrevTile(Graphics2D g2d, int x, int y) {
		g2d.setColor(new Color(0.85f, 0.30f, 0.30f, 0.25f));
		Rectangle2D s = new Rectangle2D.Double(x, y, 60, 60);
		g2d.fill(s);
		g2d.setColor(new Color(0.85f, 0.30f, 0.30f));
		g2d.setStroke(new BasicStroke(3));
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
		if (moves.get(turnNum).isWhiteTurn()) {
			g2d.drawString("Turn " + turnNum, myCheckmate.getWidth() * .035f, myCheckmate.getHeight() * .06f);
		}

		for (PossibleTile pt : moves.get(turnNum).getPrevTiles()) {
			drawPrevTile(
				g2d,
				pt.getX() * 60 + 160,
				pt.getY() * 60 + 60
			);
		}
		List<Piece> pieces = moves.get(turnNum).getPieces();
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
}
