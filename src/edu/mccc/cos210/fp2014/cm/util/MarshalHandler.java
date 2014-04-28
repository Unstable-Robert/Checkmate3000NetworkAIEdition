package edu.mccc.cos210.fp2014.cm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamConstants;

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
	public Board unmarshal(InputStream in) throws JAXBException, IOException{
			Unmarshaller um = this.jc.createUnmarshaller();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new byte[in.available()];
			while (in.available() > 0)
			{
				int i = in.read(buffer);
				baos.write(buffer, 0, i);
			}
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			Files.copy(in, Paths.get("unmarshaltest.xml"));
			Board b = (Board) um.unmarshal(bais);
			return b;
	}
	/**
	 * Marshals a board object into an OutputStream.
	 * @param b The board object to marshal
	 * @return The outputstream which represents the board object.
	 */
	public void marshal(Board b, OutputStream os) throws JAXBException, IOException {
			Marshaller m = this.jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			FileOutputStream fos = new FileOutputStream(Paths.get("unmarshaltest_presend.xml").toFile());
			m.marshal(b, fos);
			m.marshal(b, os);
			
	}
}
