package org.eggiecode.rummikub.server.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.eggiecode.rummikub.models.core.Player;

public class ClientPlayer implements Runnable {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outStream;
	private int playerNum;
	private Player player;
	private ObjectInputStream OinputStream;
	private ObjectOutputStream OoutStream;
	
	public ClientPlayer(Socket socket, int playerNum) throws IOException {
		
		this.player = new Player();
		this.socket = socket;
		
		inputStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
		OinputStream = new ObjectInputStream(socket.getInputStream());
		OoutStream = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			//int b;
			//while ((b = inputStream.read()) != -1) {
			//	System.out.println(b);

			//}
			outStream.writeInt(playerNum);
			
		} catch (IOException e) {
			System.err.println(e);
		}

	}
	public DataInputStream getDataInput(){
		return inputStream;
		
	}
	public DataOutputStream getDataOutput(){
		return outStream;
		
	}
	public ObjectInputStream getObjectInput(){
		return OinputStream;
		
	}
	public ObjectOutputStream getObjectOutput(){
		return OoutStream;
		
	}
	
}
