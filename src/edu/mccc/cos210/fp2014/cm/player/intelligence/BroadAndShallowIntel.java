package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.SimpleEvalWithNumMoves;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.BruteSearch;

public class BroadAndShallowIntel extends Intelligence {
	
	public BroadAndShallowIntel(int n, boolean iw){
		this.depth = n;
		this.isWhite = iw;
		this.search = new BruteSearch(this.currentBoard, this.depth);
		//this.eval = new SimpleEval(this.currentBoard,this.depth, this.isWhite);
		this.eval = new SimpleEvalWithNumMoves(this.currentBoard,this.depth, this.isWhite);
	}
}
