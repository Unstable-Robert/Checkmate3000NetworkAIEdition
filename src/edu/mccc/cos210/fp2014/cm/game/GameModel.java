package edu.mccc.cos210.fp2014.cm.game;

import edu.mccc.cos210.fp2014.cm.util.GameType;

import java.util.Observable;
import java.util.Timer;

import edu.mccc.cos210.fp2014.cm.piece.Piece;

/**
 * This model holds internal information about the state of the game.
 * Holds information on the board itself, a timer (if applicable), whether or not the game is over
 */
public class GameModel extends Observable {
	private Board board;
	private Timer timer;
	private TimerEvent timerEvent;
	/**
	 * Default public constructor.
	 */
	public GameModel() {
		this.board = new Board(GameType.NORMAL);
	}
	public GameModel(int i, TimerEvent te) {
		this();
	}
	public GameModel(Board b) {
		this.board = b;
	}
	public GameModel(int i, TimerEvent te, Board b){
		this(b);
		this.timer = new Timer();
		this.timerEvent = te;
		this.board.updateBothTimes(i * 60 * 1000);
		this.timer.schedule(te, 0, this.board.getTime());
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
	public void updateBoard(Board b) {
		this.board = b;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * Called at the end of each turn, by the player or an expired timer.
	 */
	public void nextTurn() {
		//reschedule timer, if applicable
		this.board.nextTurn();
	}
	/**
	 * Called when one game timer expires.
	 */
	public void gameExpired() {
	}
	/**
	 * Returns true if the game is over.
	 */
	public boolean isCheckMate(){
		for (Piece p : this.board.getPieces()){
			if (p.isWhite() == this.board.isWhiteTurn()) {
				if (!p.getPossibleTiles(this.board).isEmpty()){
					return false;
				}
			}
		}
		return true;
	}
}
