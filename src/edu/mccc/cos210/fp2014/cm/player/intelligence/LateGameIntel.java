package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.LateGameEval;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.BruteSearch;

public class LateGameIntel extends Intelligence {

	public LateGameIntel(int n, boolean iw){
		super(n,iw);
		this.search = new BruteSearch(this.currentBoard, this.depth);
		this.eval = new LateGameEval(this.currentBoard,this.depth, this.isWhite);
	}

}
