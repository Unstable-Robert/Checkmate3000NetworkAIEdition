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

/**
 * JPanel view of the a logged game
 */
public class LogView extends SettingsView implements Observer {
	private static final long serialVersionUID = 1L;
	private Board board;
	private List<String> moves = new ArrayList<String>();
    private List<String> prevMoves = new ArrayList<String>();
	private BufferedImage image;
	private int turnNum;
    private int maxTurn;
    private int[] removedPiece;
    private boolean whiteWon;
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
                if (turnNum < maxTurn) {
                    NextTurn();
                    repaint();
                }
                if (turnNum == maxTurn){
                    String message;
                    if (whiteWon){
                        message = "White Won!!";
                    } else {
                        message = "Black Won!!";
                    }

                    String[] options = new String[]{"Okay", "Load Another File"};
                    int gameOverAction = JOptionPane.showOptionDialog(
                            null, message, "Game Over",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options, options[0]
                    );
                    if (gameOverAction == 1){
                        System.out.print("Okay");
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
					PrevTurn();
                    if (CheckedRemovedPiece()){
                        PrevTurn();
                        turnNum++;
                    }
					repaint();
				}
			}
		});
		add(previousButton);
	}
    private void NextTurn(){
        String moving = moves.get(turnNum);
        String[] split = moving.split(":");
        int uid = Integer.parseInt(split[0]);
        int x = Integer.parseInt(Character.toString(split[1].charAt(0)));
        int y = Integer.parseInt(Character.toString(split[1].charAt(1)));
        RemovePieceAt(x, y);
        MovePieceTo(uid, x, y);
        turnNum++;
    }
    private void PrevTurn(){
        String moving = prevMoves.remove(prevMoves.size()-1);
        String[] split = moving.split(":");
        int uid = Integer.parseInt(split[0]);
        int x = Integer.parseInt(Character.toString(split[1].charAt(0)));
        int y = Integer.parseInt(Character.toString(split[1].charAt(1)));
        MovePieceBackTo(uid, x, y);
        turnNum--;
    }
    private boolean RemovePieceAt(int x, int y){
        for (Piece p : this.board.getPieces()){
            if (p.getX() == x && p.getY() == y){
                String oldPiece = (p.getUID()+":"+x+y);
                prevMoves.add(oldPiece);
                p.setLocation(-1, -2);
                removedPiece[turnNum] = 1;
                return true;
            }
        }
        return false;
    }
    private boolean CheckedRemovedPiece(){
        if (removedPiece[turnNum] == 1) return true;
        return false;
    }

    private void MovePieceTo(int uid, int x, int y){
        for (Piece p : this.board.getPieces()){
            if (p.getUID() == uid){
                String oldPiece = (uid+":"+p.getX()+p.getY());
                p.setLocation(x,y);
                prevMoves.add(oldPiece);
            }
        }
    }
    private void MovePieceBackTo(int uid, int x, int y){
        for (Piece p : this.board.getPieces()){
            if (p.getUID() == uid){
                p.setLocation(x,y);
            }
        }
    }
    private void CleanUp(){
        turnNum = 0;
        maxTurn = 0;
        board = new Board(GameType.NORMAL);
        image = loadImage();
        prevMoves = new ArrayList<String>();
        moves = new ArrayList<String>();
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
        CleanUp();
        repaint();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s = br.readLine();
		br.close();
		Pattern p = Pattern.compile("[0-9]+[.]");
		Matcher m = p.matcher(s);
		s = m.replaceAll("");
        s = s.replace("[","");
        s = s.replace("]"," ");
        s = s.replace(",","");
		String[] move = s.split(" ");
        this.maxTurn = move.length-1;
        removedPiece = new int[maxTurn];
		for (int i = 0; i < move.length; i++) {
			if (i % 2 == 0) {
				//white's move
                if (move[i].contains("-")){
                    if (move[i].contains("1-0")) whiteWon = true;
                    if (move[i].contains("0-1")) whiteWon = false;
                } else moves.add(move[i]);
			} else {
				//black's move
                if (move[i].contains("-")){
                    if (move[i].contains("1-0")) whiteWon = true;
                    if (move[i].contains("0-1")) whiteWon = false;
                } else moves.add(move[i]);
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
}
