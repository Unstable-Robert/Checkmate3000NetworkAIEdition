package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.LateGameEval;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.SearchBestOptions;

public class BestOptionsLateGameIntel extends BestOptionsIntel {

	public BestOptionsLateGameIntel(int n, boolean iw) {
		super(n, iw);
		this.search = new SearchBestOptions(this.currentBoard, this.depth);
		this.eval = new LateGameEval(this.currentBoard, this.depth, this.isWhite);
	}
}
