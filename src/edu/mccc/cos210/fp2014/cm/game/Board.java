package edu.mccc.cos210.fp2014.cm.game;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.piece.*;
import edu.mccc.cos210.fp2014.cm.util.GameType;

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
	public Board(GameType g) {
		this.possibleTiles = new ArrayList<PossibleTile>();
		this.pieces = new ArrayList<Piece>();
		this.movesSincePieceTaken = 0;
		this.whiteTurn = true;
		this.metaInfo = new Meta(g);
		setUpPieces();
	}
	public Board(GameType g, int t){
		this.possibleTiles = new ArrayList<PossibleTile>();
		this.pieces = new ArrayList<Piece>();
		this.movesSincePieceTaken = 0;
		this.whiteTurn = true;
		this.metaInfo = new Meta(g, t);
		setUpPieces();
	}
	private void setUpPieces(){
		int id = 0;
		this.pieces.add(new Rook(7, 0, true, id));
		id++;
		this.pieces.add(new Knight(7, 1, true, id));
		id++;
		this.pieces.add(new Bishop(7, 2, true, id));
		id++;
		this.pieces.add(new Queen(7, 3, true, id));
		id++;
		this.pieces.add(new King(7, 4, true, id));
		id++;
		this.pieces.add(new Bishop(7, 5, true, id));
		id++;
		this.pieces.add(new Knight(7, 6, true, id));
		id++;
		this.pieces.add(new Rook(7, 7, true, id));
		id++;
		for (int i = 0; i< 8; i++){
			this.pieces.add(new Pawn(i, 6, true, id));
			id++;
		}
		this.pieces.add(new Rook(0, 0, false, id));
		id++;
		this.pieces.add(new Knight(0, 1, false, id));
		id++;
		this.pieces.add(new Bishop(0, 2, false, id));
		id++;
		this.pieces.add(new Queen(0, 3, false, id));
		id++;
		this.pieces.add(new King(0, 4, false, id));
		id++;
		this.pieces.add(new Bishop(0, 5, false, id));
		id++;
		this.pieces.add(new Knight(0, 6, false, id));
		id++;
		this.pieces.add(new Rook(0, 7, false, id));
		id++;
		for (int i = 0; i< 8; i++){
			this.pieces.add(new Pawn(i, 1, false, id));
			id++;
		}
	}
	public Board(ArrayList<PossibleTile> tiles, ArrayList<Piece> p, int moves, boolean t, Meta meta) {
		this.possibleTiles = tiles;
		this.pieces = p;
		this.movesSincePieceTaken = moves;
		this.whiteTurn = t;
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
		this.whiteTurn = !this.whiteTurn;
	}
	/**
	 * This restarts the countdown used to enact a draw when there have been no pieces taken for 50 turns.
	 */
	public void pieceTaken() {
		this.movesSincePieceTaken = 0;
	}
    /**
     * Removes a Piece from the board
     * @param p Piece being removed from board
     */
	public void removePiece(Piece p){
		boolean found = false;
		for(Piece piece : this.getPieces()) {
			if (piece.getUID() == p.getUID()) {
				this.getPieces().remove(piece);
				found = true;
				break;
			}
		}
		if (!found){
			throw new NoSuchElementException();
		}
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
		boolean found = false;
		for(Piece piece : this.getPieces()) {
			if (piece.getUID() == p.getUID()) {
				this.getPieces().remove(piece);
				found = true;
				break;
			}
		}
		if (!found){
			throw new InvalidParameterException();
		}
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
