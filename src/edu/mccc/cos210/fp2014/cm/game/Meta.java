package edu.mccc.cos210.fp2014.cm.game;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * Various board info.
 * A list of information relevant to each board, including gametype, time left over, scores (for the AI).
 */
 @XmlRootElement
public class Meta implements Cloneable {
	@XmlElement
	private GameType gameType;
	@XmlElement
	private int whiteTime;
	@XmlElement
	private int blackTime;
	@XmlElement
	private double whiteScore;
	@XmlElement
	private double blackScore;
	@XmlElement
	private int numPossibleMoves;
	public Meta(GameType g) {
		this.gameType = g;
		this.whiteScore = 0;
		this.blackScore = 0;
		this.numPossibleMoves = 0;
	}
	public Meta(GameType g, int t){
		this(g);
		this.whiteTime = t;
		this.blackTime = t;
	}
	public Meta(GameType gt, int wT, int bT, double wS, double bS, int nPM){
		this.gameType = gt;
		this.whiteTime = wT;
		this.blackTime = bT;
		this.whiteScore = wS;
		this.blackScore = bS;
		this.numPossibleMoves = nPM;
	}
	public GameType getGameType(){
		return this.gameType;
	}
	public int getWhiteTime(){
		return this.whiteTime;
	}
	public int getBlackTime(){
		return this.blackTime;
	}
	public void setWhiteTime(int t){
		this.whiteTime = t;
	}
	public void setBlackTime(int t){
		this.blackTime = t;
	}
	public double getWhiteScore(){
		return this.whiteScore;
	}
	public double getBlackScore(){
		return this.blackScore;
	}
	public void setWhiteScore (double s){
		this.whiteScore = s;
	}
	public void setBlackScore(double s){
		this.blackScore = s;
	}
	public int getPossibleMoves(){
		return this.numPossibleMoves;
	}
	public void setPossibleMoves(int m){
		this.numPossibleMoves = m;
	}
	public Meta clone(){
		return new Meta(this.getGameType(),
				this.getWhiteTime(),
				this.getBlackTime(),
				this.getWhiteScore(),
				this.getBlackScore(),
				this.getPossibleMoves());
	}
}
