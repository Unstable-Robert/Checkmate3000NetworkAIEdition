package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * Represents the king piece and it's movements
 */
public class King extends Piece {

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
	 * whether or not the king has moved and can castle
	 */
	public boolean canCastle(){
		return false;
	}
}
