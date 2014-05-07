package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.EarlyGameEval;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.SearchBestOptions;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class BestOptionsEarlyGameIntel extends Intelligence {

	public BestOptionsEarlyGameIntel(int n, boolean iw) {
		super(n, iw);
		this.search = new SearchBestOptions(this.currentBoard, this.depth);
		this.eval = new EarlyGameEval(this.currentBoard, this.depth, this.isWhite);
	}
	@Override
	public void searchAndEval(Board b){
		Tree<Board> t = new Tree<Board>(b);
		this.search.search(t);
		this.eval.setRoot(t);
		this.eval.evaluate(t, true);
		t = this.eval.getSeveralBest(5);
		this.search.search(t);
		this.eval.setRoot(t);
		this.eval.evaluate(t, true);	
	}
}
