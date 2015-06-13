package org.eggiecode.rummikub.server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	ServerSocket serverSocket;
	
	public static void main(String[] args) {
		new Server();

	}

	public Server() {

		try {
			serverSocket = new ServerSocket(8000);
			
			System.out.println("Server started");
			int client = 1;
			while (true) {
				System.out.println("Acceping client");
				
				Socket socket = serverSocket.accept();

				GameClient task = new GameClient(socket);
				new Thread(task).start();
				System.out.println("New client connected");
				

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}

class GameClient implements Runnable {
	private Socket player;
	private DataInputStream inputStream;
	private DataOutputStream outStream;

	public GameClient(Socket player) throws IOException {
		this.player = player;
		inputStream = new DataInputStream(player.getInputStream());
		outStream = new DataOutputStream(player.getOutputStream());
	}

	@Override
	public void run() {
		try {
			int b;
			while((b = inputStream.read()) != -1) {
				System.out.println(b);
				
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
