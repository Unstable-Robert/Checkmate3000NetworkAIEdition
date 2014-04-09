package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the pawn and it's movements.
 */
public class Pawn extends Piece {

	/**
	 * Gets possible tiles that this piece can move given the board.
	 * @param board the board that is checked for possible moves
	 * @return all of the possible tiles to which this piece can move.
	 */
	@Override
	public ArrayList<PossibleTile> getPossibleTiles(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Whether or not the pawn has just moved forward two spaces and can be taken en passant.
	 */
	public boolean possibleToPassant() {
		return false;
	}
	/**
	 * Whether or not the pawn has moved, and therefor whether it can move forward two spaces
	 */
	private boolean hasMoved() {
		return false;
	}
}
