package edu.mccc.cos210.fp2014.cm.player.intelligence;

import java.util.ArrayList;

import edu.mccc.cos210.fp2014.cm.piece.Piece;

public abstract class Intelligence {

	protected SearchAlgorithm search;
	protected EvaluationAlgorithm eval;
	
	public abstract Piece getBest(ArrayList<Piece> pieces);
	protected abstract ArrayList<ArrayList<Piece>> search(ArrayList<Piece> pieces);
	protected abstract double evaluate(ArrayList<Piece> pieces);
}
