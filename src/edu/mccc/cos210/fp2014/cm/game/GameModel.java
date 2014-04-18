package edu.mccc.cos210.fp2014.cm.game;

import java.util.Observable;
import java.util.Timer;

/**
 * This model holds internal information about the state of the game.
 * Including: the board itself, a timer if applicable, whether or not the game is over
 */
public class GameModel extends Observable{
	
	private Board board;
	private Timer t;
	
	/**
	 * Default public constructor.
	 */
	public GameModel() {
		
	}
	
	/**
	 * Gets a copy of the current board.
	 * @return a copy of the current board.
	 */
	public Board getBoard() {
		return null;
	}
	/**
	 * Updates the board
	 * @param b The new board.
	 */
	public void updateBoard(Board b) {
		
	}
	/**
	 * Called at the end of each turn, by the player or an expired timer.
	 */
	public void nextTurn() {
		
	}
	/**
	 * Called when one game timer expires.
	 */
	public void gameExpired() {
		
	}
	/**
	 * Returns true if the game is over.
	 */
	private boolean isCheckMate(){
		return false;
	}
}
