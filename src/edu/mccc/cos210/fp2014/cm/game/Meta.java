package edu.mccc.cos210.fp2014.cm.game;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.mccc.cos210.fp2014.cm.util.GameType;

/**
 * A list of information relevant to each board, including gametype, time left over,
 * scores (for the AI).
 */
 @XmlRootElement
public class Meta {
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
}
