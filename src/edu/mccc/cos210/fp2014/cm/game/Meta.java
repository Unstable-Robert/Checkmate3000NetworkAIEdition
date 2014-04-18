package edu.mccc.cos210.fp2014.cm.game;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * A list of information relevant to each board, including gametype, time left over,
 * scores (for the AI).
 */
 @XmlRootElement
public class Meta implements Cloneable {
	@XmlElement
	private GameType gameType;
	@XmlElement
	private int p1Time;
	@XmlElement
	private int p2Time;
	@XmlElement
	private double whiteScore;
	@XmlElement
	private double blackScore;
	@XmlElement
	private int numPossibleMoves;
	
	public Meta(GameType gt, int p1, int p2, double wS, double bS, int nPM){
		this.gameType = gt;
		this.p1Time = p1;
		this.p2Time = p2;
		this.whiteScore = wS;
		this.blackScore = bS;
		this.numPossibleMoves = nPM;
	}
	public GameType getGameType(){
		return null;
	}
	public int getP1Time(){
		return 0;
	}
	public int getP2Time(){
		return 0;
	}
	public double getWhiteScore(){
		return 0;
	}
	public double getBlackScore(){
		return 0;
	}
	public int getPossibleMoves(){
		return 0;
	}
	public Meta clone(){
		return new Meta(this.getGameType(),
				this.getP1Time(),
				this.getP2Time(),
				this.getWhiteScore(),
				this.getBlackScore(),
				this.getPossibleMoves());
	}
}
