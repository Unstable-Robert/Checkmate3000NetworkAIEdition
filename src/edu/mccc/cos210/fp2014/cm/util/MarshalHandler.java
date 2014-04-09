package edu.mccc.cos210.fp2014.cm.util;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * This handles marshaling our board class into an output stream and unmarshalling an input stream into a board class.
 */
public class MarshalHandler {
	
	JAXBContext jc;
	
	/**
	 * Unmarshals an input stream into a board object.
	 * @param in The input stream to unmarshal
	 * @return The board object which was represented by the input stream.
	 */
	public Board unmarshal(InputStream in) {
		return null;
	}
	/**
	 * Marshals a board object into an outputstream
	 * @param b The board object to marshal
	 * @return The outputstream which represents the board object.
	 */
	public OutputStream marshal(Board b) {
		return null;
	}
}
