package edu.mccc.cos210.fp2014.cm.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
	public MarshalHandler(){
		try {
			jc = JAXBContext.newInstance(Board.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	public synchronized Board unmarshal(InputStream in) {
		try {
			Unmarshaller um = this.jc.createUnmarshaller();
			//Files.copy(in, Paths.get("unmarshaltest.xml"));
			Board b = (Board) um.unmarshal(in);
			return b;
		} catch (JAXBException e) {
			e.printStackTrace();
		}// catch (IOException e) {
			//e.printStackTrace();
		//}
		return null;
	}
	/**
	 * Marshals a board object into an OutputStream.
	 * @param b The board object to marshal
	 * @return The outputstream which represents the board object.
	 */
	public synchronized void marshal(Board b, OutputStream os) {
		try {
			Marshaller m = this.jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(b, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
