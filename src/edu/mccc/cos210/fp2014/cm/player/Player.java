package edu.mccc.cos210.fp2014.cm.player;

import java.util.Observer;

import edu.mccc.cos210.fp2014.cm.game.GameModel;

public abstract class Player {

	protected GameModel gm;
	
	protected abstract void updateModel();
	public void updateEvent(){
		
	}
}
