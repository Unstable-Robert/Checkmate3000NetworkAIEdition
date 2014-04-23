package edu.mccc.cos210.fp2014.cm.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.piece.Piece;
import edu.mccc.cos210.fp2014.cm.piece.PossibleTile;
import edu.mccc.cos210.fp2014.cm.util.MarshalHandler;

/**
 * Human networked player.
 * A networked player, only one of these is created during networked games. 
 * Handles sockets, marshalling, and other networked transmission of the current game
 * state.
 */
public class NetworkPlayer extends Player implements Runnable {
	private ServerSocket ss;
	private Socket socket;
	private MarshalHandler mh;
	private InetAddress address;
	
	public NetworkPlayer(GameModel gm, boolean isWhite, InetAddress a) throws IOException{
		super(gm,isWhite);
		ss = new ServerSocket(7531);
		mh = new MarshalHandler();
		this.address = a;
	}
	public NetworkPlayer(InetAddress a) throws IOException{
		mh = new MarshalHandler();
		this.address = a;
		this.socket = new Socket(address, 7531);
	}
	/**
	 * Updates the model if the local individual or networked individual make a move.
	 */
	@Override
	public boolean updateModel(Piece piece, PossibleTile pt) {
		return super.updateModel(piece, pt);
	}
	/**
	 * Sends a marshalled board object to the other player if the board has been updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			synchronized(this.socket){
				OutputStream os = socket.getOutputStream();
				mh.marshal(gm.getBoard(), os);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Called when the class is first created, this opens the socket.
	 */
	@Override
	public void run() {
		try {
			this.socket = ss.accept();
			this.socket.setSoTimeout(100);
			while (true){
				try {
					synchronized (this.socket){
						InputStream is = socket.getInputStream();
						Board b = mh.unmarshal(is);
						gm.updateBoard(b);
					}
				} catch (SocketTimeoutException e){
					try {
						Thread.sleep(this.socket.getSoTimeout()/2);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
