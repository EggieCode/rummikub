package org.eggiecode.rummikub.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.eggiecode.rummikub.controllers.RunnikubController;
import org.eggiecode.rummikub.server.objects.ClientPlayer;

public class Server {
	ServerSocket serverSocket;
	private RunnikubController runnikubController;
	
	public static void main(String[] args) {
		new Server();

	}

	public Server() {
		this.runnikubController = new RunnikubController();
		this.runnikubController.startGame();
		
		
		
		try {
			serverSocket = new ServerSocket(8000);
			
			System.out.println("Server started");
			int client = 1;
			while (true) {
				System.out.println("Acceping client");
				
				Socket socket = serverSocket.accept();

				ClientPlayer task = new ClientPlayer(socket);
				new Thread(task).start();
				System.out.println("New client connected");
				

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
