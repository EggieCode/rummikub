package org.eggiecode.rummikub.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eggiecode.rummikub.controllers.RunnikubController;
import org.eggiecode.rummikub.server.objects.ClientPlayer;

public class Server {
	ServerSocket serverSocket;
	private RunnikubController runnikubController;

	private ServerController controler;
	ClientPlayer task;

	public static void main(String[] args) throws UnknownHostException {
		TestBoardcastClient t = new TestBoardcastClient();
		new Thread(t).start();
		Server s = new Server();
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

class TestBoardcastClient implements Runnable {

	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;
	private InetAddress address;
	private DatagramPacket reply;

	public TestBoardcastClient() throws UnknownHostException {
		// Get the address that we are going to connect to.
		address = InetAddress.getByName(INET_ADDR);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Since the message is small here, 256 bytes should be enough.
		byte[] buf = new byte[256];

		// Create a new Multicast socket (that will allow other sockets/programs
		// to join it as well.
		try (MulticastSocket clientSocket = new MulticastSocket(PORT)) {
			// Joint the Multicast group.
			clientSocket.joinGroup(address);

			while (true) {
				// Receive the information and print it.
				DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
				clientSocket.receive(msgPacket);
				String msg = new String(buf, 0, buf.length);
				System.out
						.println("Socket 1 received msg ("
								+ msgPacket.getAddress().getHostAddress()
								+ "): " + msg);
				String sReply = "Welcom "
						+ msgPacket.getAddress().getHostAddress();

				reply = new DatagramPacket(sReply.getBytes(), sReply.length(),
						msgPacket.getSocketAddress());
				clientSocket.send(reply);
				System.out.println("Reply send");
			}
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}
}
