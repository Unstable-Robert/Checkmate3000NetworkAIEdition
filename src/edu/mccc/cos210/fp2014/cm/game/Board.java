package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;

/**
 * Represents the piece-centric board of the chess engine.
 * It contains meta information about itself, the position of pieces, and turn info.
 * It also keeps track of information about draw scenarios.
 */
 @XmlRootElement
public class Board implements Cloneable{
	@XmlElement
	private ArrayList<PossibleTile> possibleTiles;
	@XmlElement
	private ArrayList<Piece> pieces;
	@XmlElement
	private int movesSincePieceTaken;
	@XmlElement
	private boolean whiteTurn;
	@XmlElement
	private Meta metaInfo;
	/**
	 * Constructor.
	 * Saves possible tiles, pieces, moves, whose turn it is, and meta data.
	 */
	public Board(ArrayList<PossibleTile> tiles, ArrayList<Piece> p, int moves, boolean t, Meta meta) {
	}
	/**
	 * This updates the timer that will be painted by GameView
	 * @param i The number of seconds remaining on the clock for the current player's turn
	 */
	public void updateTimer(int i) {
	}
	/**
	 * This changes which player is taking their turn.
	 */
	public void nextTurn() {
	}
	/**
	 * This restarts the countdown used to enact a draw when there have been no pieces taken for 50 turns.
	 */
	public void pieceTaken() {
	}
    /**
     * Removes a Piece from the board
     * @param p Piece being removed from board
     */
	public void removePiece(Piece p){
	}
    /**
     * Gets all the pieces on the board
     * @return All the pieces currently on the board
     */
	public List<Piece> getPieces() {
		return null;
	}
	public List<PossibleTile> getPossibleTiles(){
		return null;
	}
	public int getNumMovesSinceLastPieceTaken(){
		return 0;
	}
	public boolean isWhiteTurn(){
		return false;
	}
	public Meta getMetaInfo(){
		return null;
	}
    /**
     * Adds a piece to the board
     * @param p Piece being added to the board
     */
	public void addPiece(Piece p){
	} 
	public Board clone(){
		ArrayList<Piece> newPieces = new ArrayList<Piece>();
		ArrayList<PossibleTile> newTiles = new ArrayList<PossibleTile>();
		for(Piece p : this.getPieces()){
			newPieces.add(p.clone());
		}
		for(PossibleTile t : this.getPossibleTiles()){
			newTiles.add(t.clone());
		}
		return new Board(newTiles, 
				newPieces,
				this.getNumMovesSinceLastPieceTaken(), 
				this.whiteTurn,
				this.getMetaInfo().clone());
	}
}
