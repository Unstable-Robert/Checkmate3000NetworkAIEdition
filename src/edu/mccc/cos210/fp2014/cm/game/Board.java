package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.util.Color;

/**
 * This class represents the piece centric board of the chess engine.
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
	private int movesSincePeiceTaken;
	@XmlElement
	private Color turn;
	@XmlElement
	private Meta metaInfo;

	public Board (ArrayList<PossibleTile> tiles, ArrayList<Piece> p, int moves, Color t, Meta meta) {
		this.possibleTiles = tiles;
		this.pieces = p;
		this.movesSincePeiceTaken = moves;
		this.turn = t;
		this.metaInfo = meta;
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
	 * This restarts the countdown used to enact a draw when there have been
	 * no taken pieces for 50 turns.
	 */
	public void peiceTaken() {

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
	public Color getTurn(){
		return null;
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
				this.getTurn(), 
				this.getMetaInfo().clone());
	}
}
