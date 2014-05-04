package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;

public class TestIntel extends Intelligence {
	private int depth;
	public TestIntel(int n, boolean white) {
		this.depth = n;
		this.isWhite = white;
		this.search = new TestSearch(this.currentBoard, this.depth);
		this.eval = new TestEval(this.currentBoard, this.depth, this.isWhite);
	}
	@Override
	public Board getBest() {
		return this.eval.getBest();
	}
}
