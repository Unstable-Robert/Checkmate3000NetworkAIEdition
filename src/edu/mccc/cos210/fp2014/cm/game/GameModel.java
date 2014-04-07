package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;
import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.piece.Piece;

public class GameModel extends Observable{
	
	private Color turn;
	private boolean whiteCheck;
	private boolean blackCheck;
	private ArrayList<Piece> pieces;
	
	public GameModel() {
		
	}
	
	public ArrayList<Piece> getPieces() {
		return null;
	}
	public void updateGame (Piece p) {
		
	}
	private void isCheck(){
		
	}
	private void isCheckMate(){
		
	}
}
