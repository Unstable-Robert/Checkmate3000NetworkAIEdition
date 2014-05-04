package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;

public class BroadAndShallowIntel extends Intelligence {
	private int depth;
	
	public BroadAndShallowIntel(int n){
		this.depth = n;
	}
	@Override
	public void searchAndEval(){
		this.search = new BruteSearch(this.currentBoard, this.depth);
		this.eval = new SimpleEval(this.currentBoard,this.depth);
		super.searchAndEval();
	}
	@Override
	public Board getBest() {
		return this.eval.getBest();
	}

}
