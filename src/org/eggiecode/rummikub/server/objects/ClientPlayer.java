package org.eggiecode.rummikub.server.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.EndGame;
import org.eggiecode.rummikub.models.core.Player;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.server.ServerController;

public class ClientPlayer implements Runnable {
	private ServerController serverController;
	private Socket socket;
	private int playerNum;
	private Player player;
	private ObjectInputStream OinputStream;
	private ObjectOutputStream OoutStream;
	private boolean ready;

	public ClientPlayer(ServerController serverController, Socket socket,
			int playerNum) throws IOException {

		this.player = new Player(playerNum);
		this.socket = socket;
		this.serverController = serverController;
		this.playerNum = playerNum;
		OoutStream = new ObjectOutputStream(socket.getOutputStream());
		OinputStream = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		try {
			Object o;
			while ((o = OinputStream.readObject()) != null) {
				if (o instanceof Command) {
					Command c = (Command) o;
					processCommand(c);
				}

				if (o instanceof StoneSet) {
					StoneSet stoneSet = new StoneSet();
					for (Stone s : ((StoneSet) o).getStones()) {
						for (Stone playerStone : player.getStones()) {
							if (s.equals(playerStone)) {
								
								player.removeStone(playerStone);
								stoneSet.add(playerStone);
							}
						}
					}
					serverController.putOnTable(stoneSet);
					
				}
			}

		} catch (IOException e) {
			serverController.playerDisconnected(this);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processCommand(Command c) {
		switch (c) {
		case READY:
			this.serverController.playerReady(this);
			break;
		case NEW_STONE:
			Stone s = this.serverController.randomStone();
			if(s == null)
				this.serverController.endGame();
			
			player.addStone(s);
			this.sendPlayer();

		case END_TURN:

			this.serverController.nextTurn(this);
			break;
		
		default:
			break;
		}
	}

	public ObjectInputStream getObjectInput() {
		
		return OinputStream;

	}

	public ObjectOutputStream getObjectOutput() throws IOException {
		OoutStream.reset();
		return OoutStream;

	}

	public Player getPlayer() {
		return player;
	}

	// TODO Auto-generated method stub
	public void setReady(boolean b) {
		ready = b;
	}

	public boolean isReady() {
		// TODO Auto-generated method stub
		return ready;
	}

	public void createPlayer() {
		// TODO Auto-generated method stub
		this.player = new Player(playerNum);
		for (int i = 0; i < 14; i++)
			player.addStone(this.serverController.randomStone());
	}

	public void sendCommand(Command c) {
		try {

			getObjectOutput().writeObject(c);
			getObjectOutput().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPlayer() {
		try {
			getObjectOutput().writeObject(player);
			getObjectOutput().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendBeginStones(ArrayList<Stone> Beginstone) {
		try {
			getObjectOutput().writeObject(Beginstone);
			getObjectOutput().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendPutTable(Table table) {
		try {
			
			getObjectOutput().writeObject(table);
			getObjectOutput().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getPlayerNum() {
		// TODO Auto-generated method stub
		return playerNum;

	}

	public void sendEndGame(EndGame c) {
		// TODO Auto-generated method stub
		try {
			
			getObjectOutput().writeObject(c);
			getObjectOutput().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
