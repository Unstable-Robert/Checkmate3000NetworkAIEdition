package edu.mccc.cos210.fp2014.cm.player;

import java.net.Socket;

import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.util.MarshalHandler;

public class HostPlayer extends Player{

	private Socket socket;
	private MarshalHandler mh;
	
	@Override
	public void updateModel(Piece p) {
		// TODO Auto-generated method stub

	}
}
