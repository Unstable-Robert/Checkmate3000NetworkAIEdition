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
			GameModel gm1 = new GameModel(null);
			NetworkPlayer np1 = NetworkPlayer.GetHostNetwork(new GameModel(), InetAddress.getByName("127.0.0.1"));
			NetworkPlayer np2 = NetworkPlayer.GetJoinNetwork(gm1, InetAddress.getByName("127.0.0.1"));
			(new Thread(np1)).start();
			(new Thread(np2)).start();
			np1.update(new GameModel(), null);
			Board b = gm1.getBoard();
			b.addPiece(new Pawn());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
