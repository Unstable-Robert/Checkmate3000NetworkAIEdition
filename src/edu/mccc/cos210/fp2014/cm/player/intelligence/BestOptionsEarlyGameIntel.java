package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.EarlyGameEval;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.SearchBestOptions;

public class BestOptionsEarlyGameIntel extends BestOptionsIntel {

	public BestOptionsEarlyGameIntel(int n, boolean iw) {
		super(n, iw);
		this.search = new SearchBestOptions(this.currentBoard, this.depth);
		this.eval = new EarlyGameEval(this.currentBoard, this.depth, this.isWhite);
	}
}
