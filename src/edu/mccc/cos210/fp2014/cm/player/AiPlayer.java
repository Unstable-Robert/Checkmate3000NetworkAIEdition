package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.player.intelligence.BestOptionsEarlyGameIntel;
import edu.mccc.cos210.fp2014.cm.player.intelligence.BestOptionsIntel;
import edu.mccc.cos210.fp2014.cm.player.intelligence.BroadAndShallowIntel;
import edu.mccc.cos210.fp2014.cm.player.intelligence.EarlyGameIntel;
import edu.mccc.cos210.fp2014.cm.player.intelligence.Intelligence;
import edu.mccc.cos210.fp2014.cm.player.intelligence.LateGameIntel;
import edu.mccc.cos210.fp2014.cm.util.Difficulty;
import edu.mccc.cos210.fp2014.cm.util.GamePart;

/**
 * Artificial intelligent chess player.
 * It contains an intelligence object which can be defined be the point in the game
 * (beginning, middle, or end) which handles all of the search and evaluation.
 */
public class AiPlayer extends Player implements Runnable{
	private Intelligence intelligence;
	private GamePart gamePart;
	private Difficulty difficulty;
	private boolean moving;
	private int numMoves;
	public AiPlayer(GameModel gm, Checkmate c, boolean b, Difficulty d) {
		super(gm, c, b);
		this.isWhite = b;
		this.difficulty = d;
		moving = false;
		this.numMoves = 0;
		this.gamePart = GamePart.BEGINNING;
		setIntelligence();
	}
	/**
	 * This takes a board parameter and returns the best move (as determined by the intelligence module).
	 * @param b The board to search for the best move
	 * @return The board with the new best move.
	 */
	public Board getMove(Board b) {
		if (setGamePart(b)){
			setIntelligence();
		}
		this.intelligence.searchAndEval(b);
		Board board = this.intelligence.getBest();
		board.nextTurn();
		this.numMoves += 2;
		return board;
	}
	private void setIntelligence() {
		int depth = 2;
		
		switch(this.gamePart) {
			case BEGINNING:
				switch (this.difficulty) {
				case EASY:
					this.intelligence = new EarlyGameIntel(3, this.isWhite);
					break;
				case MEDIUM:
					this.intelligence = new BestOptionsEarlyGameIntel(3, this.isWhite);
					break;
				case HARD:
					this.intelligence = new BestOptionsEarlyGameIntel(6, this.isWhite);
					break;
				case HUMAN:
					break;
				}
				break;
			case MIDDLE:	
				switch (this.difficulty) {
				case EASY:
					this.intelligence = new BroadAndShallowIntel(3, this.isWhite);
					break;
				case MEDIUM:
					this.intelligence = new BestOptionsIntel(3, this.isWhite);
					break;
				case HARD:
					this.intelligence = new BestOptionsIntel(6, this.isWhite);
					break;
				case HUMAN:
					break;
				}
			case END:
				switch (this.difficulty) {
				case EASY:
					this.intelligence = new LateGameIntel(3, this.isWhite);
					break;
				case MEDIUM:
					this.intelligence = new LateGameIntel(3, this.isWhite);
					break;
				case HARD:
					this.intelligence = new LateGameIntel(6, this.isWhite);
					break;
				case HUMAN:
					break;
				}
				break;
			default:
				break;
		}
	}
	private boolean setGamePart(Board b) {
		GamePart oldGamePart = this.gamePart;
		if (this.numMoves < 10){
			this.gamePart = GamePart.BEGINNING;
		} else {
			int count = 0;
			for (Piece p : b.getPieces()){
				if (p.isWhite() == this.isWhite){
					count++;
				}
			}
			if (count > 6){
				this.gamePart = GamePart.MIDDLE;
			} else {
				this.gamePart = GamePart.END;
			}
		}
		return this.gamePart == oldGamePart;
	}
	/**
	 * Updates the game model with it's move.
	 */
	@Override
	public boolean updateModel(Piece piece, PossibleTile pt){
		super.updateModel(piece, pt);
		return false;
	}
	/**
	 * Updates the intelligence.
	 * This is called when the model changes. It updates the intelligence's current board,
	 * if it differs from the current one.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(this.gm.getBoard().isWhiteTurn() == this.isWhite() && !this.moving){
			this.moving = true;
			this.gm.updateBoard(this.getMove(this.gm.getBoard()), false);
			this.moving = false;
		}
	}
	/**
	 * Called when this object is created in order to start the search-eval loop.
	 */
	@Override
	public void run() {
	}
}
