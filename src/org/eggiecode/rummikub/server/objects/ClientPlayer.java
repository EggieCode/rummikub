package org.eggiecode.rummikub.server.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.eggiecode.rummikub.models.core.Player;

public class ClientPlayer implements Runnable {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outStream;

	private Player player;
	
	public ClientPlayer(Socket socket) throws IOException {
		
		this.player = new Player();
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			int b;
			while ((b = inputStream.read()) != -1) {
				System.out.println(b);

			}

		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
