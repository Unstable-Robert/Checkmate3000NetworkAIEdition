package edu.mccc.cos210.fp2014.cm.util;

import java.io.FilterInputStream;
import java.io.InputStream;

public class UncloseableInputStream extends FilterInputStream {
	public UncloseableInputStream(InputStream is){
		super(is);
	}
	@Override
	public void close(){}
}
