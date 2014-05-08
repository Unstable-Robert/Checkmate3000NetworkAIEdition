package edu.mccc.cos210.fp2014.cm.game;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChatMessage {
	@XmlElement(name="from")
	private String from;
	@XmlElement(name="message")
	private String message;
	public ChatMessage() {	
	}
	public ChatMessage(String f, String m) {
		this.from = f;
		this.message = m;
	}
	public String getFrom() {
		return this.from;
	}
	public String getMessage() {
		return this.message;
	}
}
