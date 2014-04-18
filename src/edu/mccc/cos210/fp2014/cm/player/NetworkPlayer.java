package edu.mccc.cos210.fp2014.cm.player;

import java.net.Socket;
import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.util.MarshalHandler;

/**
 * A networked player, only one of these is created during networked games. 
 * Handles sockets, marshalling, and other networked transmission of the current game
 * state.
 */
public class NetworkPlayer extends Player implements Runnable{

	private Socket socket;
	private MarshalHandler mh;
	
	/**
	 * Updates the model if the local individual or networked individual make a move.
	 */
	@Override
	public void updateModel(Piece p) {
		// TODO Auto-generated method stub

	}
	/**
	 * Sends a marshaled board object to the other player if the board has been updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Called when the class is first created, this opens the socket and such.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
