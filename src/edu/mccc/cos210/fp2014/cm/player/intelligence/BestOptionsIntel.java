package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate.SimpleEvalWithNumMoves;
import edu.mccc.cos210.fp2014.cm.player.intelligence.search.SearchBestOptions;
import edu.mccc.cos210.fp2014.cm.util.Tree;

public class BestOptionsIntel extends Intelligence {

	public BestOptionsIntel(int n, boolean iw) {
		super(n, iw);
		this.search = new SearchBestOptions(this.currentBoard, this.depth);
		this.eval = new SimpleEvalWithNumMoves(this.currentBoard, this.depth, this.isWhite);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void searchAndEval(Board b){
		Tree<Board> t = new Tree<Board>(b);
		for (int i = 0; i < 3; i++) {
			this.search.search(t);
			this.eval.setRoot(t);
			this.eval.evaluate(t, true);
			t = this.eval.getSeveralBest(10);
		}
	}
}
