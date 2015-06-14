package org.eggiecode.rummikub.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eggiecode.rummikub.controllers.RunnikubController;
import org.eggiecode.rummikub.models.networking.ClientBroadcastReply;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;
import org.eggiecode.rummikub.server.objects.ClientPlayer;

public class Server {
	ServerSocket serverSocket;

	private ServerController controler;
	private String serverName = "Rummicup Server";
	private int port = 45123;

	public static void main(String[] args) throws UnknownHostException {
		new Server();
	}

	public Server() throws UnknownHostException {

		TestBoardcastClient t = new TestBoardcastClient(serverName, port);
		new Thread(t).start();

		controler = new ServerController(this);
		try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void startServer() throws IOException {
		serverSocket = new ServerSocket(port);

		System.out.println("Server started");
		while (controler.clients.size() < 2) {
			System.out.println("Acceping client");

			ClientPlayer task;
			try {
				Socket socket = serverSocket.accept();
				System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
				task = new ClientPlayer(controler, socket, controler.clients.size());
				new Thread(task).start();
				if (task != null) {
					controler.addClient(task);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}

}

class TestBoardcastClient implements Runnable {

	private static final String BROADCAST_INET_ADDR = "224.0.0.5";
	private static final int BROADCAST_PORT = 45110;
	private InetAddress address;
	private DatagramPacket reply;
	private int port;
	private String serverName;

	public TestBoardcastClient(String serverName, int port)
			throws UnknownHostException {
		// Get the address that we are going to connect to.
		address = InetAddress.getByName(BROADCAST_INET_ADDR);
		this.port = port;
		this.serverName = serverName;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Since the message is small here, 256 bytes should be enough.
		byte[] buf = new byte[1024 * 24];

		// Create a new Multicast socket (that will allow other sockets/programs
		// to join it as well.
		try (MulticastSocket clientSocket = new MulticastSocket(BROADCAST_PORT)) {
			// Joint the Multicast group.
			clientSocket.joinGroup(address);

			while (true) {
				// Receive the information and print it.
				DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
				clientSocket.receive(msgPacket);

				ObjectInputStream inputStream = new ObjectInputStream(
						new ByteArrayInputStream(buf, 0, 1024 * 24));
				Object o = inputStream.readObject();

				if (o instanceof ClientBroadcastReply) {
					ClientBroadcastReply replay = (ClientBroadcastReply) o;
				}

				ByteArrayOutputStream buffer = new ByteArrayOutputStream(
						1024 * 24);
				ObjectOutputStream outputStream = new ObjectOutputStream(buffer);
				outputStream.writeObject(new ServerBroadcastReply(serverName,
						1, port));

				byte[] b = buffer.toByteArray();
				DatagramPacket replayPacket = new DatagramPacket(b, b.length,
						msgPacket.getSocketAddress());
				clientSocket.send(replayPacket);

			}
		} catch (IOException ex) {
			ex.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
