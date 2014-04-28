package edu.mccc.cos210.fp2014.cm.game;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import edu.mccc.cos210.fp2014.cm.piece.Pawn;
import edu.mccc.cos210.fp2014.cm.player.NetworkPlayer;

public class MarshalTest {

	public MarshalTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			GameModel gm1 = new GameModel();
			GameModel gm2 = new GameModel(null);
			NetworkPlayer np1 = NetworkPlayer.GetHostNetwork(gm1, InetAddress.getByName("127.0.0.1"));
			NetworkPlayer np2 = NetworkPlayer.GetJoinNetwork(gm2, InetAddress.getByName("127.0.0.1"));
			(new Thread(np1)).start();
			(new Thread(np2)).start();
			Board b = gm2.getBoard();
			b.addPiece(new Pawn());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
