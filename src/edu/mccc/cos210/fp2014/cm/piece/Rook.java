package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * The class represents the Rook piece and it's movements
 */
public class Rook extends Piece {

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
	 * This returns true if the rook has not moved and false if it has.
	 */
	public boolean canCastle(){
		return false;
	}
}
