package edu.mccc.cos210.fp2014.cm.game;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;

import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.util.Color;

public class GameModel extends Observable{
	
	private Color turn;
	private boolean whiteCheck;
	private boolean blackCheck;
	private ArrayList<Piece> pieces;
	private Timer t;
	
	public GameModel() {
		
	}
	
	public ArrayList<Piece> getPieces() {
		return null;
	}
	public void updateGame (Piece p) {
		
	}
	public void timerExpired(){
		
	}
	private void isCheck(){
		
	}
	private void isCheckMate(){
		
	}
}
