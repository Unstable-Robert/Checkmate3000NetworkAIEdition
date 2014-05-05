package edu.mccc.cos210.fp2014.cm.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
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
 * JPanel view of the board.
 * Also handles click actions and player selection.
 */
public class GameView extends SettingsView implements Observer, MouseListener{
	private static final long serialVersionUID = 1L;
	private GameModel gm;
	private ArrayList<Player> players;
	private List<PossibleTile> pTiles = new ArrayList<PossibleTile>();
	private BufferedImage image;
	private JButton resignButton, sendButton;
	private JTextField sendTF;
	private JTextArea chatTextArea;
	public GameView(Checkmate c) {
		super(c);
		players = new ArrayList<Player>();
		this.setDoubleBuffered(true);
		this.addMouseListener(this);
	}
	public GameView(Checkmate c, GameModel model, boolean networked) {
		this(c);
		this.gm = model;
		image = loadImage();
		
		resignButton = new JButton("Resign");
		resignButton.setSize(100, 50);
		resignButton.setLocation((int)(c.getWidth() * 0.05), (int)(c.getHeight() * 0.10));
		resignButton.setVisible(true);
		resignButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				endGame();
			}
		});
		add(resignButton);		

		if (networked){
			chatTextArea = new JTextArea("", 14, 26);
			chatTextArea.setEditable(false);
			chatTextArea.setLineWrap(true);
			//chatTextArea.setOpaque(true);
			add(chatTextArea);
			JScrollPane scrollPane =  new JScrollPane(chatTextArea,
	                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
	                );
			scrollPane.setLocation(
					(int)(c.getWidth() * 0.02), 
					(int)(c.getHeight() * 0.50)
				);
			scrollPane.setSize(135, 220);
	        add(scrollPane);
			sendTF = new JTextField();
			sendTF.setSize(250,20);
			sendTF.setLocation(
				(int)(c.getWidth() * 0.02), 
				(int)(c.getHeight() * 0.90)
			);
			add(sendTF);
			
			sendButton = new JButton("send");
			sendButton.setSize(100, 20);
			sendButton.setLocation(
				(int)(c.getWidth() * 0.05 + sendTF.getWidth() + 10),
				(int)(c.getHeight() * 0.90)
			);
			sendButton.setVisible(true);
			sendButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					String color = "";
					for (Player p : players){
						if (p.isWhite()){
							color = "White";
						} else {
							color = "Black";
						}
						break;
					}
					gm.addMessage(new ChatMessage(color, getSentText()));
					sendTF.setText("");
				}
			});
			add(sendButton);
		}
	}
	public void setChatLabel(String s) {
		chatTextArea.setText(s);
	}
	public String getSentText() {
		return sendTF.getText();
	}
	public void addPlayer(Player p) {
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
	private void cleanUp() {
		for (Player p : players) {
			if (p instanceof NetworkPlayer) {
				NetworkPlayer np = (NetworkPlayer) p;
				np.setCloseResponsibility(false);
				np.closeSockets();
			}
		}
		if (gm.hasTimer()) {
			gm.cancelTimer();
		}
	}
	private void saveLog(boolean isWhiteTurn) {
		ArrayList<int[]> moves = this.gm.getBoard().getMoves();
		System.out.println(moves);
		JFileChooser fc = new JFileChooser(new File("logs"));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(
			new FileNameExtensionFilter("Checkmate 3000 Logs", "cm3")
		);
		fc.showSaveDialog(this);
		try {
			FileWriter fw = new FileWriter(fc.getSelectedFile() + ".cm3");
			for (int i = 0; i < moves.size(); i++) {
				for (int j = 0; j < moves.get(i).length; j++) {
					fw.write(Integer.toString(moves.get(i)[j]));
					fw.write(",");
				}
				fw.write(";");
			}
			fw.close();
		} catch (java.io.IOException ex){
			ex.printStackTrace();
		}
	}
	private void endGame() {
		cleanUp();
		String message = "White won.";
		boolean isWhiteTurn = gm.getBoard().isWhiteTurn();
		if (isWhiteTurn) {
			message = "Black won.";
		}
		String[] options = new String[]{"Okay", "Save Game Log"};
		int gameOverAction = JOptionPane.showOptionDialog(
			this, message, "Game Over",
			JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			null, options, options[0]
		);
		if (gameOverAction == JOptionPane.OK_OPTION) {
			myCheckmate.setView(Checkmate.MAIN_MENU);	
		} else {
			saveLog(isWhiteTurn);
			myCheckmate.setView(Checkmate.MAIN_MENU);
		}
	}
	private void drawGame() {
		cleanUp();
		String message = "The game ends in a draw.";
		String[] options = new String[]{"Okay", "Save Game Log"};
		int gameOverAction = JOptionPane.showOptionDialog(
			this, message, "Game Over",
			JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			null, options, options[0]
		);
		if (gameOverAction == JOptionPane.OK_OPTION) {
			myCheckmate.setView(Checkmate.MAIN_MENU);	
		} else {
			String moves = this.gm.getBoard().getMoves() + "1-1";
			JFileChooser fc = new JFileChooser(new File("logs"));
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(
				new FileNameExtensionFilter("Checkmate 3000 Logs", "cm3")
			);
			fc.showSaveDialog(this);
			try {
				FileWriter fw = new FileWriter(fc.getSelectedFile() + ".cm3");
				fw.write(moves);
				fw.flush();
				fw.close();
			} catch (java.io.IOException ex){
				ex.printStackTrace();
			}
			myCheckmate.setView(Checkmate.MAIN_MENU);
		}
	}
	private void addChats(){
		StringBuilder sb = new StringBuilder();
		for(ChatMessage cm : this.gm.getBoard().getMessages()){
			sb.append(cm.getFrom());
			sb.append(": ");
			sb.append(cm.getMessage());
			sb.append("\n");
		}
		chatTextArea.setText(sb.toString());
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
		if (this.gm.canDraw()) {
			int wantsDraw = JOptionPane.showConfirmDialog(
				myCheckmate,
				"It is possible to declare this game as a tie. Would you like to end this game in a draw?",
				"End game in a draw?",
				JOptionPane.YES_NO_OPTION
			);
			if (wantsDraw == JOptionPane.YES_OPTION) {
				drawGame();
			}
		}
		this.paintComponent(this.getGraphics());
		if (this.gm.mustDraw()) {
			drawGame();
		}
		if (this.gm.isCheckMate()){
			JPanel panel = new JPanel(new GridLayout(2, 1));
			JLabel label;
			if(myCheckmate.getGameModel().getBoard().isWhiteTurn()) {
				label = new JLabel("Black Wins!!");
			} else {
				label = new JLabel("White Wins!!");
			}
			panel.add(label);
			endGame();
		}
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
		if (gm.getBoard().isWhiteTurn()) {
			g2d.drawString("White's Turn", myCheckmate.getWidth() * .02f, myCheckmate.getHeight() * .05f);
		} else {
			g2d.drawString("Black's Turn", myCheckmate.getWidth() * .02f, myCheckmate.getHeight() * .05f);
		}
		
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
			if (p.isWhite()) {
				gridY = 60;
			} else {
				gridY = 0;
			}
			drawPiece(g2d, p.getX() * 60 + 160, p.getY() * 60 + 60, gridX, gridY);
		}
		for (PossibleTile pt : pTiles){
			drawPossibleTile(
				g2d, 
				pt.getX() * 60 + 160, 
				pt.getY() * 60 + 60
			);
		}
		g2d.setPaint(Color.WHITE);
		if (this.gm.getBoard().getMetaInfo().getGameType().equals(GameType.TIMED_GAME)){
			int blackTime = gm.getBoard().getBlackTime();
			int whiteTime = gm.getBoard().getWhiteTime();
			g2d.drawString("Black Time", myCheckmate.getWidth() * 0.81f, myCheckmate.getHeight() * 0.05f);
			// use string formatter here instead of this mess
			g2d.drawString(
				blackTime / 60 + ":" + (blackTime % 60 < 10 ? "0" : "") + blackTime % 60, 
				myCheckmate.getWidth() * 0.85f, 
				myCheckmate.getHeight() * 0.10f
			);
			g2d.drawString("White Time", myCheckmate.getWidth() * 0.81f, myCheckmate.getHeight() * 0.20f);
			g2d.drawString(
				whiteTime / 60 + ":" + (whiteTime % 60 < 10 ? "0" : "") + whiteTime % 60, 
				myCheckmate.getWidth() * 0.85f, 
				myCheckmate.getHeight() * 0.25f
			);
		}
		if (chatTextArea != null){
			addChats();
		}
		this.paintChildren(g);
		g2d.dispose();
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
			if (
				x > (p.getX() + 1) * 60 + 100 && 
				x < (p.getX() + 2) * 60 + 100 &&
				y > (p.getY() + 1) * 60 && 
				y < (p.getY() + 2) * 60
			) {
				found = true;
				piece = p;
				break;
			}
		}
		for (PossibleTile pt : this.gm.getBoard().getPossibleTiles()){
			if (
				x > (pt.getX() + 1) * 60 + 100 && 
				x < (pt.getX() + 2) * 60 + 100 &&
				y > (pt.getY() + 1) * 60 && 
				y < (pt.getY() + 2) * 60
			) {
				found = true;
				possibleTile = pt;
				break;
			}
		}
		pTiles = new ArrayList<PossibleTile>();
		if (found) {
			if(possibleTile != null && piece != null && !possibleTile.hasPieceToRemove()){
				if (this.gm.getBoard().hasSelectedPiece()){
					Piece p = this.gm.getBoard().getSelectedPiece();
					p.setSelected(false);
					for(Player player : this.players) {
						if (player.updateCastle(p, piece)){
							break;
						}
					}
				}
			}else if (possibleTile != null) {
				if (this.gm.getBoard().hasSelectedPiece()){
					Piece p = this.gm.getBoard().getSelectedPiece();
					p.setSelected(false);
					for(Player player : this.players) {
						if (player.updateModel(p, possibleTile)){
							break;
						}
					}
				}
			}else if (piece != null) {
				if (this.gm.getBoard().hasSelectedPiece()){
					Piece p = this.gm.getBoard().getSelectedPiece();
					p.setSelected(false);
					repaint();
				} else {
					Board b = this.gm.getBoard();
					if (
						(b.isWhiteTurn() && piece.isWhite()) ||
						(!b.isWhiteTurn() && !piece.isWhite()) 
					){
						b.clearSelected();
						piece.setSelected(true);
						pTiles = b.getPossibleTiles();
						if (pTiles.size() == 0) {
							piece.setSelected(false);
						}
						this.gm.updateBoard(b, false);
					}
				}
			}
		} else {
			if (this.gm.getBoard().hasSelectedPiece()){
				Piece p = this.gm.getBoard().getSelectedPiece();
				p.setSelected(false);
				repaint();
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
