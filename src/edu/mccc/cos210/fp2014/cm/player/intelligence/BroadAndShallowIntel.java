package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;

public class BroadAndShallowIntel extends Intelligence {
	private int depth;
	
	public BroadAndShallowIntel(int n, boolean iw){
		this.depth = n;
		this.isWhite = iw;
		this.search = new BruteSearch(this.currentBoard, this.depth);
		this.eval = new SimpleEvalWithNumMoves(this.currentBoard,this.depth, this.isWhite);
	}
	@Override
	public Board getBest() {
		return this.eval.getBest();
	}

}
