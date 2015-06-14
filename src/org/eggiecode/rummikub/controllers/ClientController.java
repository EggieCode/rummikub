package org.eggiecode.rummikub.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.eggiecode.rummikub.models.networking.ClientBroadcastReply;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;

public class ClientController extends Thread {

	private static final String BROADCAST_INET_ADDR = "224.0.0.5";
	private static final int BROADCAST_PORT = 45110;
	private InetAddress BROADCAST_INET;
	private DatagramSocket serverSocket;

	private RunnikubController runnikubController;
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	public ClientController(RunnikubController runnikubController) {
		// TODO Auto-generated constructor stub
		try {
			BROADCAST_INET = InetAddress.getByName(BROADCAST_INET_ADDR);
			serverSocket = new DatagramSocket();
			ReplayThread replayThread = new ReplayThread(this, serverSocket);
			new Thread(replayThread).start();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.runnikubController = runnikubController;
	}

	protected boolean connect(InetAddress address) {
		// TODO Auto-generated method stub

		try {
			clientSocket = new Socket(address, 6789);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());
			outputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.start();
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Object o;
		try {
			while ((o = inputStream.readObject()) != null) {

			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findServers() {
		// Get the address that we are going to connect to.
		System.out.println("Searching servers");
		final byte b[];
		// Open a new DatagramSocket, which will be used to send the data.
		try {

			ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024 * 24);
			ObjectOutputStream outputStream = new ObjectOutputStream(buffer);
			outputStream.writeObject(new ClientBroadcastReply("TEST123"));

			b = buffer.toByteArray();

		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return;
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for (int i = 0; i < 5000000; i++) {
						System.out.println("Sending data");
						DatagramPacket msgPacket = new DatagramPacket(b,
								b.length, BROADCAST_INET, BROADCAST_PORT);
						serverSocket.send(msgPacket);
						Thread.sleep(2000);

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	public class ReplayThread implements Runnable {

		private DatagramSocket serverSocket;
		private ClientController clientController;

		public ReplayThread(ClientController clientController,
				DatagramSocket serverSocket) throws IOException {
			this.serverSocket = serverSocket;
			this.clientController = clientController;

		}

		@Override
		public void run() {
			try {
				while (true) {
					byte b[] = new byte[1024 * 24];
					System.out.println("Waiting for packet");
					DatagramPacket msgPacket = new DatagramPacket(b, 1024 * 24,
							BROADCAST_INET, BROADCAST_PORT);
					serverSocket.receive(msgPacket);

					ObjectInputStream inputStream = new ObjectInputStream(
							new ByteArrayInputStream(b, 0, 1024 * 24));
					Object o = inputStream.readObject();
					if (o instanceof ServerBroadcastReply) {
						ServerBroadcastReply replay = (ServerBroadcastReply) o;
						System.out.print(replay);
						System.out.println(" - " + msgPacket.getAddress().getHostAddress() + "\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
