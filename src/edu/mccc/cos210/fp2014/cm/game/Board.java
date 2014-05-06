package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;
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
	@XmlElement(name="possibleTiles")
	private ArrayList<PossibleTile> possibleTiles;
	@XmlElement(name="piece")
	private ArrayList<Piece> pieces;
	@XmlElement(name="movesSincePieceTaken")
	private int movesSincePieceTaken;
	@XmlElement(name="whiteTurn")
	private boolean whiteTurn;
	@XmlElement(name="metaInfo")
	private Meta metaInfo;
	@XmlElement(name="moves")
    private ArrayList<int[]> moves;
	@XmlElement(name="messages")
	private ArrayList<ChatMessage> messages;
	/**
	 * Constructor.
	 * Saves possible tiles, pieces, moves, whose turn it is, and meta data.
	 */
	public Board(){
		this.possibleTiles = new ArrayList<PossibleTile>();
		this.pieces = new ArrayList<Piece>();
		this.messages = new ArrayList<ChatMessage>();
		this.movesSincePieceTaken = 0;
		this.whiteTurn = true;
		this.metaInfo = new Meta();
        this.moves = new ArrayList<int[]>();
	}
	public Board(GameType g) {
		this();
		this.metaInfo = new Meta(g);
		setUpPieces();
	}
	public Board(GameType g, int t){
		this();
		this.metaInfo = new Meta(g, t);
		setUpPieces();
	}
	public Board(ArrayList<PossibleTile> tiles, ArrayList<Piece> p, int m, boolean t, Meta meta, ArrayList<int[]> moveList) {
		this.possibleTiles = tiles;
		this.pieces = p;
		this.movesSincePieceTaken = m;
		this.whiteTurn = t;
		this.metaInfo = meta;
        this.moves = moveList;
	}
	private void setUpPieces(){
		int id = 0;
		this.pieces.add(new Rook(0, 7, true, id));
		id++;
		this.pieces.add(new Knight(1, 7, true, id));
		id++;
		this.pieces.add(new Bishop(2, 7, true, id));
		id++;
		this.pieces.add(new Queen(3, 7, true, id));
		id++;
		this.pieces.add(new King(4, 7, true, id));
		id++;
		this.pieces.add(new Bishop(5, 7, true, id));
		id++;
		this.pieces.add(new Knight(6, 7, true, id));
		id++;
		this.pieces.add(new Rook(7, 7, true, id));
		id++;
		for (int i = 0; i< 8; i++){
			this.pieces.add(new Pawn(i, 6, true, id));
			id++;
		}
		this.pieces.add(new Rook(0, 0, false, id));
		id++;
		this.pieces.add(new Knight(1, 0, false, id));
		id++;
		this.pieces.add(new Bishop(2, 0, false, id));
		id++;
		this.pieces.add(new Queen(3, 0, false, id));
		id++;
		this.pieces.add(new King(4, 0, false, id));
		id++;
		this.pieces.add(new Bishop(5, 0, false, id));
		id++;
		this.pieces.add(new Knight(6, 0, false, id));
		id++;
		this.pieces.add(new Rook(7, 0, false, id));
		id++;
		for (int i = 0; i< 8; i++){
			this.pieces.add(new Pawn(i, 1, false, id));
			id++;
		}
	}
	public void addMessage(ChatMessage cm) {
		this.messages.add(cm);
	}
	public ArrayList<ChatMessage> getMessages() {
		return this.messages;
	}
	/**
	 * This updates the timer that will be painted by GameView
	 * @param i The number of seconds remaining on the clock for the current player's turn
	 */
	public void updateTime(int i) {
		if (this.whiteTurn){
			this.metaInfo.setWhiteTime(i);
		} else {
			this.metaInfo.setBlackTime(i);
		}
	}
	public void updateBothTimes(int i) {
		this.metaInfo.setWhiteTime(i);
		this.metaInfo.setBlackTime(i);
	}
	public int getWhiteTime() {
		return this.metaInfo.getWhiteTime();
	}
	public int getBlackTime() {
		return this.metaInfo.getBlackTime();
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
	public ArrayList<Piece> getPieces() {
		return this.pieces;
	}
	public ArrayList<PossibleTile> getPossibleTiles(){
		if (this.hasSelectedPiece()){
			Piece p = this.getSelectedPiece();
			this.possibleTiles = p.getPossibleTiles(this);
		} else {
			this.possibleTiles = new ArrayList<PossibleTile>();
		}
		return this.possibleTiles;
	}
	public int getNumMovesSinceLastPieceTaken(){
		return this.movesSincePieceTaken;
	}
	public boolean isWhiteTurn() {
		return this.whiteTurn;
	}
	public Meta getMetaInfo() {
		return this.metaInfo;
	}
    /**
     * Adds a piece to the board
     * @param p Piece being added to the board
     */
	public void addPiece(Piece p) {
		this.getPieces().add(p);
	} 
	public void clearSelected() {
		for(Piece p : this.getPieces()) {
			p.setSelected(false);
		}
	}
	public boolean hasSelectedPiece() {
		for (Piece p : this.getPieces()) {
			if (p.isSelected()){
				return true;
			}
		}
		return false;
	}
	public Piece getSelectedPiece() {
		for (Piece p : this.getPieces()) {
			if (p.isSelected()) {
				return p;
			}
		}
		return null;
	}

    /**
     * Gets the Log of all moves Taken
     * @return String of Moves
     */
    public ArrayList<int[]> getMoves() {
        return this.moves;
    }
    /**
     * Adds a Move to the Log
     * @param s Move Taken
     */
	public void addMove(int[] move) {
		this.moves.add(move);
    }
	public Board clone() {
		ArrayList<Piece> newPieces = new ArrayList<Piece>();
		ArrayList<PossibleTile> newTiles = new ArrayList<PossibleTile>();
		for(Piece p : this.getPieces()) {
			newPieces.add(p.clone());
		}
		return new Board(newTiles, 
			newPieces,
			this.getNumMovesSinceLastPieceTaken(), 
			this.whiteTurn,
			this.getMetaInfo().clone(),
			this.getMoves()
		);
	}
}
