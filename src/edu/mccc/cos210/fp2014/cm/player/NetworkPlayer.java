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
	private boolean firstUpdate;
	private ServerSocket ss;
	private Socket socket;
	private MarshalHandler mh;
	private InetAddress address;
	
	public NetworkPlayer(GameModel gm, Boolean color, InetAddress a) throws IOException{
		super(gm, true);
		mh = new MarshalHandler();
		this.address = a;
		firstUpdate = true;
	}
	public static NetworkPlayer GetHostNetwork(GameModel gm, InetAddress a)throws IOException{
		NetworkPlayer np = new NetworkPlayer(gm, false, a);
		np.setServerSocket(new ServerSocket(7531));
		return np;
	}
	public static NetworkPlayer GetJoinNetwork(GameModel gm, InetAddress a) throws IOException{
		NetworkPlayer np = new NetworkPlayer(gm, false, a);
		np.setSocket(new Socket(a, 7531));
		np.update(np.gm, null);
		return np;
	}
	private void setServerSocket(ServerSocket ss){
		this.ss = ss;
	}
	private void setSocket(Socket s){
		this.socket = s;
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
			if(this.isWhite == this.gm.isWhiteTurn() || this.gm.hasTimer()){
				OutputStream os = socket.getOutputStream();
				mh.marshal(this.gm.getBoard(), os);
				os.flush();
				os.dispose();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Called when the class is first created, this opens the socket.
	 */
	@Override
	public void run() {
		try {
			if (this.ss != null){
				this.socket = this.ss.accept();
				update(this.gm, null);
			} 
			while (true){
				try {
					InputStream is = socket.getInputStream();
					Board b = mh.unmarshal(is);
					is.flush();
					is.dispose();
					this.gm.updateBoard(b);
				} catch (SocketTimeoutException e){
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
