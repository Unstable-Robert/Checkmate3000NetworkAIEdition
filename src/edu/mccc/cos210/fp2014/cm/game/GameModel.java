package edu.mccc.cos210.fp2014.cm.game;

import edu.mccc.cos210.fp2014.cm.piece.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;

/**
 * This model holds internal information about the state of the game.
 * Holds information on the board itself, a timer (if applicable), whether or not the game is over
 */
public class GameModel extends Observable {
	private Board board;
	private Timer timer;
	private boolean isNetworkUpdate;
	private int totalTime, moveRule;
	private int winner;
	/**
	 * Default public constructor.
	 */
	public GameModel(Board b) {
		this.board = b;
		this.moveRule = 0;
	}
	public GameModel(int i, boolean isTimed, Board b){
		this(b);
		this.totalTime = i;
		this.timer = new Timer();
		this.board.updateBothTimes(this.totalTime * 60);
	}
	public void startTimer(){
		if (timer != null){
			this.timer.scheduleAtFixedRate(new TurnTimer(this, this.totalTime), 1000L, 1000L);
		}
	}
	/**
	 * Gets a copy of the current board.
	 * @return a copy of the current board.
	 */
	public Board getBoard() {
		return this.board;
	}
	/**
	 * Updates the board.
	 * @param b The new board.
	 */
	public void updateBoard(Board b, boolean timedUpdate) {
		this.isNetworkUpdate = timedUpdate;
		this.board = b;
		this.setChanged();
		this.notifyObservers();
	}
	public boolean isNetworkUpdate(){
		return this.isNetworkUpdate;
	}
	/**
	 * Called at the end of each turn, by the player or an expired timer.
	 */
	public void nextTurn() {
		this.board.nextTurn();
	}
	public int getWinner(){
		return this.winner;
	}
	public void setWinner(int w){
		this.winner = w;
	}
	/**
	 * Called when one game timer expires.
	 */
	public void gameExpired() {
		timer.cancel();
		if (board.isWhiteTurn()) {
			System.out.println("Black wins!!!");
		} else {
			System.out.println("White wins!!!");
		}
	}
	public boolean hasTimer(){
		return this.timer != null;
	}
	/**
	 * Returns true if the game is over.
	 */
	public boolean isCheckMate() {
		if ((this.board.getBlackTime() == 0 || this.board.getWhiteTime() == 0) &&
				this.hasTimer()){
			this.cancelTimer();
			return true;
		}
		boolean possibleCheckmate = false;
		for (Piece p : this.board.getPieces()){
			if (p.isWhite() == this.board.isWhiteTurn()) {
				if (!p.getPossibleTiles(this.board).isEmpty()) {
					return false;	
				}
				if (p instanceof King){
					King k = (King) p;
					possibleCheckmate = k.inCheck(this.board);
				}
			}
					
		}
		if (possibleCheckmate && this.hasTimer()){
			this.cancelTimer();
		}
		return possibleCheckmate;
	}
	public void cancelTimer() {
		timer.cancel();
	}
	public void increaseMoveRule() {
		moveRule++;
	}
	public void resetMoveRule() {
		moveRule = 0;
	}
	public boolean canDraw() {
		return moveRule % 50 == 0 && moveRule != 0;
	}
	public boolean mustDraw() {
		boolean draw = false;
		ArrayList<Piece> pieces = this.getBoard().getPieces();
		switch (pieces.size()) {
		case 2:
			draw = true;
			break;
		case 3:
			for (Piece p : pieces) {
				if (p instanceof Bishop) {
					draw = true;
				} else if (p instanceof Knight) {
					draw = true;
				}
			}
			break;
		case 4:
			ArrayList<Piece> bishops = new ArrayList<Piece>();
			for (Piece p : pieces) {
				if (p instanceof Bishop) {
					bishops.add(p);
				}
				if (bishops.size() == 2) {
					if (bishops.get(0).isWhite() == bishops.get(1).isWhite()) {
						draw = true;
					}
				}
			}
			break;
		default:
			break;
		}
		for (Piece p : this.board.getPieces()){
			if (p.isWhite() == this.board.isWhiteTurn()) {
				if (!p.getPossibleTiles(this.board).isEmpty()) {
					return false;	
				}
				if (p instanceof King){
					King k = (King) p;
					draw = !k.inCheck(this.board);
				}
			}
					
		}
		if (draw) {
			if(this.hasTimer()){
				this.cancelTimer();
			}
		}
		return draw;
	}
	public void addMessage(ChatMessage cm){
		this.isNetworkUpdate = false;
		this.board.addMessage(cm);
		this.setChanged();
		this.notifyObservers();
	}
}
