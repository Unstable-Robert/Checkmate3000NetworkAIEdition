package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.EarlyGameEval;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.BruteSearch;

public class EarlyGameIntel extends Intelligence {

	public EarlyGameIntel(int n, boolean iw){
		this.depth = n;
		this.isWhite = iw;
		this.search = new BruteSearch(this.currentBoard, this.depth);
		this.eval = new EarlyGameEval(this.currentBoard,this.depth, this.isWhite);
	}
}
