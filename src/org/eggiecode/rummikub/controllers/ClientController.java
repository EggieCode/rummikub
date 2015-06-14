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
import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.EndGame;
import org.eggiecode.rummikub.models.core.Player;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.models.networking.ClientBroadcastReply;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;
import org.eggiecode.rummikub.server.objects.Command;

public class ClientController extends Thread {

	private static final String BROADCAST_INET_ADDR = "224.0.0.5";
	private static final int BROADCAST_PORT = 45110;
	private InetAddress BROADCAST_INET;
	private DatagramSocket serverSocket;

	private RunnikubController runnikubController;
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	private ArrayList<ServerBroadcastReply> servers = new ArrayList<>();

	private boolean connecting = false;
	private boolean connected = false;

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

	protected boolean connect(InetAddress address, int i) {
		// TODO Auto-generated method stub
		connecting = true;
		new Thread(new Runnable() {
			public void run() {
				try {
					clientSocket = new Socket(address, i);
					// clientSocket.setKeepAlive(true);
					outputStream = new ObjectOutputStream(
							clientSocket.getOutputStream());
					outputStream.flush();
					inputStream = new ObjectInputStream(
							clientSocket.getInputStream());
					System.out.println("Connected!");
					ClientController.this.start();
					connected = true;
					connecting = false;

					sendCommand(Command.READY);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					connected = false;
					connecting = false;
				}

			}

		}).start();

		return true;
	}

	protected void sendCommand(Command command) {
		try {
			this.outputStream.reset();
			this.outputStream.writeObject(command);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Object o;
		try {
			while (true) {
				o = inputStream.readObject();
				System.out.println(o.toString());
				if (o instanceof Command) {
					runnikubController.processCommand((Command) o);
				}
				if (o instanceof Table) {
					runnikubController.setTable((Table) o);
				}
				if (o instanceof Player) {
					runnikubController.setPlayer((Player) o);
				}
				if (o instanceof EndGame) {
					runnikubController.setEndGame((EndGame)o);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findServers() {
		servers.clear();
		// Get the address that we are going to connect to.
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
					for (int i = 0; i < 5; i++) {
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
					DatagramPacket msgPacket = new DatagramPacket(b, 1024 * 24,
							BROADCAST_INET, BROADCAST_PORT);
					serverSocket.receive(msgPacket);

					ObjectInputStream inputStream = new ObjectInputStream(
							new ByteArrayInputStream(b, 0, 1024 * 24));
					Object o = inputStream.readObject();
					if (o instanceof ServerBroadcastReply) {

						ServerBroadcastReply reply = (ServerBroadcastReply) o;
						reply.setAddress(msgPacket.getAddress());
						addServerReplay(reply);
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

	public void addServerReplay(ServerBroadcastReply replay) {
		// TODO Auto-generated method stub
		for (ServerBroadcastReply s : servers) {
			if (s.equals(replay))
				return;
		}
		servers.add(replay);
	}

	public ArrayList<ServerBroadcastReply> getServers() {
		// TODO Auto-generated method stub
		return this.servers;

	}

	public boolean isConnecting() {
		return connecting;
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (connected) {
			this.join();
			this.interrupt();
			this.clientSocket.close();

		}

		super.finalize();
	}

	public void sendStoneSet(StoneSet set) {
		// TODO Auto-generated method stub
		try {
			this.outputStream.reset();
			this.outputStream.writeObject(set);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
