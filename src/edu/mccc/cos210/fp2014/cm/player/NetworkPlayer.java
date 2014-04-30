package edu.mccc.cos210.fp2014.cm.player;

import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Observable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.game.GameModel;
import edu.mccc.cos210.fp2014.cm.menu.Checkmate;
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
	private boolean active;
	private boolean closeResponsibilities;
	
	private NetworkPlayer(GameModel gm, Checkmate c, Boolean color, InetAddress a) throws IOException{
		super(gm, c, color);
		this.mh = new MarshalHandler();
		this.active = true;
		this.closeResponsibilities = true;
	}
	public static NetworkPlayer GetHostNetwork(GameModel gm, Checkmate c, InetAddress a)throws IOException{
		NetworkPlayer np = new NetworkPlayer(gm, c, true, a);
		np.setServerSocket(new ServerSocket(7531));
		(new Thread(np)).start();
		return np;
	}
	public static NetworkPlayer GetJoinNetwork(GameModel gm, Checkmate c, InetAddress a) throws IOException{
		NetworkPlayer np = new NetworkPlayer(gm, c, false, a);
		np.setSocket(new Socket(a, 7531));
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
		return super.updateModel(piece, pt);
	}
	/**
	 * Sends a marshalled board object to the other player if the board has been updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Board b = this.checkPawnPromotion();
		if (b != null){
			this.gm.updateBoard(b, false);
		}
		try {
			if(!this.gm.isTimedUpdate()){ 
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				writeMessage(this.gm.getBoard(), dos);
			}
		}catch (IOException e) {
			unreachablePlayer();
		}catch (JAXBException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			JPanel panel = new JPanel(new GridLayout(2, 1));
			JLabel label = new JLabel("Wait up, nobody's connected yet!");
			String[] options = new String[]{"OK"};
			panel.add(label);
			JOptionPane.showOptionDialog(null, panel, "Waiting for Another Player",
						  JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
						  null, options, options[0]);
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
				this.gm.startTimer();
				this.update(gm, null);
			}
			while (this.active){
				try {
					InputStream is = socket.getInputStream();
					if (is.available() > 0){
						DataInputStream dis = new DataInputStream(is);
						ByteArrayInputStream bais = readMessage(dis);
						Board b = mh.unmarshal(bais);
						if (b == null){
							
						}
						this.gm.updateBoard(b, true);
					}
				} catch (Exception e){
					unreachablePlayer();				
				}
			}
		} catch (IOException e) {
			
		}
	}
	private void unreachablePlayer() {
		this.closeSockets();
		if (this.closeResponsibilities){
			if (gm.hasTimer()){
				gm.cancelTimer();
			} 
			myCheckmate.endGame(!this.isWhite());	
		}
	}
	private void writeMessage(Board b, DataOutputStream dos) throws JAXBException, IOException, SocketException{
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
	public void closeSockets() {
		try {
			this.active = false;
			this.socket.close();
			if (this.ss != null) {
				this.ss.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setCloseResponsibility(boolean b) {
		this.closeResponsibilities = b;
	}
}
