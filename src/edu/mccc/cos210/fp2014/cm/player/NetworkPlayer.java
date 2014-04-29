package edu.mccc.cos210.fp2014.cm.player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;

import javax.xml.bind.JAXBException;

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
	private boolean updatedByNetwork;
	private ServerSocket ss;
	private Socket socket;
	private MarshalHandler mh;
	
	private NetworkPlayer(GameModel gm, Boolean color, InetAddress a) throws IOException{
		super(gm, color);
		mh = new MarshalHandler();
	}
	public static NetworkPlayer GetHostNetwork(GameModel gm, InetAddress a)throws IOException{
		NetworkPlayer np = new NetworkPlayer(gm, true, a);
		np.setServerSocket(new ServerSocket(7531));
		np.updatedByNetwork = false;
		(new Thread(np)).start();
		return np;
	}
	public static NetworkPlayer GetJoinNetwork(GameModel gm, InetAddress a) throws IOException{
		NetworkPlayer np = new NetworkPlayer(gm, false, a);
		np.setSocket(new Socket(a, 7531));
		np.updatedByNetwork = true;
		(new Thread(np)).start();
		return np;
	}
	private void setServerSocket(ServerSocket ss){
		this.ss = ss;
	}
	private void setSocket(Socket s){
		this.socket = s;
	}
	@Override
	public boolean updateModel(Piece piece, PossibleTile pt){
		this.updatedByNetwork = false;
		return super.updateModel(piece, pt);
	}
	/**
	 * Sends a marshalled board object to the other player if the board has been updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			if(!this.updatedByNetwork){ 
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				writeMessage(this.gm.getBoard(), dos);
			}
		}catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
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
				this.update(gm, null);
			}
			while (true){
				try {
					InputStream is = socket.getInputStream();
					if (is.available() > 0){
						DataInputStream dis = new DataInputStream(is);
						ByteArrayInputStream bais = readMessage(dis);
						Board b = mh.unmarshal(bais);
						this.updatedByNetwork = true;
						this.gm.updateBoard(b);
					}
				} catch (SocketTimeoutException e){
					e.printStackTrace();
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeMessage(Board b, DataOutputStream dos) throws JAXBException, IOException{
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    mh.marshal(b, bout);
	    byte[] msgBytes = bout.toByteArray();
	    dos.writeInt(msgBytes.length);
	    dos.write(msgBytes);
		dos.flush();
	}
	private ByteArrayInputStream readMessage(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        byte[] ba = new byte[size];
        dis.readFully(ba);
        return new ByteArrayInputStream(ba);
    }
	public void setUpdatedByNetwork(boolean b) {
		this.updatedByNetwork = b;
	}
}
