package edu.mccc.cos210.fp2014.cm.player.intelligence;

import edu.mccc.cos210.fp2014.cm.game.Board;

public class BroadAndShallowIntel extends Intelligence {

	public BroadAndShallowIntel(Board b, int md){
		this.search = new BruteSearch(b, md);
		this.eval = new SimpleEval();
	}
	@Override
	public Board getBest() {
		// TODO Auto-generated method stub
		return null;
	}

}
