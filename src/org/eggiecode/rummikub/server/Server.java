package org.eggiecode.rummikub.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.eggiecode.rummikub.controllers.RunnikubController;
import org.eggiecode.rummikub.server.objects.ClientPlayer;

public class Server {
	ServerSocket serverSocket;
	private RunnikubController runnikubController;
	private ServerController controler;
	ClientPlayer task;

	public static void main(String[] args) {
		new Server();

	}

	public Server() {
		this.runnikubController = new RunnikubController();
		this.runnikubController.startGame();
		controler = new ServerController(this);

		try {
			serverSocket = new ServerSocket(8000);

			System.out.println("Server started");
			int j = 1;
			while (controler.clients.size() < 2) {
				System.out.println("Acceping client");

				Socket socket = serverSocket.accept();

				ClientPlayer task = new ClientPlayer(socket , j);
				new Thread(task).start();
				System.out.println("New client connected");
				if (task != null) {
					controler.addClient(task);
					j++;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
