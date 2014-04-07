package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.piece.Piece;

public abstract class SearchAlgorithm {
	
	protected abstract ArrayList<ArrayList<Piece>> search(ArrayList<Piece> pieces);
}
