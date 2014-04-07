package edu.mccc.cos210.fp2014.cm.piece;

import java.util.ArrayList;

public abstract class Piece {
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	
	protected int xLoc;
	protected int yLoc;
	protected boolean Color;
	
	public abstract ArrayList<PossibleTile> getPossibleTiles();
	public void setLocation(int x, int y){
		
	}
}
