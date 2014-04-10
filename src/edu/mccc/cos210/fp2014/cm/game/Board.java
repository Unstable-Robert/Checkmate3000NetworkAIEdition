package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;

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
	public void removePiece(Piece p){
		
	}
	public ArrayList<Piece> getPieces() {
		return null;
	}
	public void addPiece(Piece p){
		
	}
	//we need to take great care with this method, it's extremely easy to screw up
	//whoever implements this needs to do a deep clone (aka all of it's 
	//attribute objects are cloned too).
	public Board clone(){
		return null;
	}
}
