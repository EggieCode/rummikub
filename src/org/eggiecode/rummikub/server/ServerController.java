package org.eggiecode.rummikub.server;

import java.util.ArrayList;
import java.util.Random;

import org.eggiecode.rummikub.models.core.EndGame;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.server.objects.ClientPlayer;
import org.eggiecode.rummikub.server.objects.Command;

public class ServerController {
	private static final int colors[] = new int[] { 0x344CFA, 0xff0000,
			0xC48206, 0x0 };
	private static final int numbers[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13 };

	private ArrayList<Stone> stones = new ArrayList();
	protected ArrayList<ClientPlayer> clients = new ArrayList();
	private Table table;
	private Command command;
	private int playerOnTurn;
	private Random random = new Random();
	private ArrayList<Stone> beginStones;

	public ServerController(Server server) {
		table = new Table();

	}

	public void addClient(ClientPlayer player) {
		if (clients.size() < 2) {
			clients.add(player);
		}
	}

	private void startGame() {
		this.table = new Table();
		generateDeck();
		for (ClientPlayer c : clients) {
			c.createPlayer();
			c.sendPlayer();
			c.sendPutTable(table);
			c.sendCommand(Command.GAME_START);
		}

		clients.get(0).sendCommand(Command.PLAYER_TURN);
	}

	public void playerReady(ClientPlayer p) {
		boolean ready = true;
		p.setReady(true);
		for (ClientPlayer c : clients) {
			if (!c.isReady()) {
				ready = false;
				break;
			}
		}
		if (ready && clients.size() >= 2)
			startGame();
	}

	public void nextTurn(ClientPlayer clientPlayer) {
		if (clientPlayer.getPlayerNum() != playerOnTurn)
			return;
		if(clientPlayer.getPlayer().getStones().length == 0) {
			endGame();
			return;
		}
		
		int nextPlayerTurn = (playerOnTurn + 1) % clients.size();
		if (nextPlayerTurn < 0)
			nextPlayerTurn = -nextPlayerTurn;
		for (ClientPlayer c : clients) {
			c.sendPutTable(table);
			if (c.getPlayerNum() == nextPlayerTurn) {
				c.sendCommand(Command.PLAYER_TURN);
			} else if (c.getPlayerNum() == playerOnTurn) {

			}

		}
		playerOnTurn = nextPlayerTurn;
		System.out.println("Turn of: " + playerOnTurn);

	}

	public Stone randomStone() {
		int i = 0;
		Stone s = stones.get(random.nextInt(stones.size()));
//		Stone s = stones.get(i);
		while (s.isOnPlayerBoard() || s.isOnTable() && i < stones.size()) {
			s = stones.get(random.nextInt(stones.size()));
			i++;
		}
		
		return s;
	}

	public void generateDeck() {
		stones.clear();
		for (int i = 0; i < 2; i++) {
			for (int color : colors) {
				for (int number : numbers) {
					stones.add(new Stone(number, color, i));
				}
			}

			stones.add(new Stone(-1, colors[i], i));
		}

	}

	public void playerDisconnected(ClientPlayer clientPlayer) {
		// TODO Auto-generated method stub
		this.clients.remove(clientPlayer);
		System.out.println("Player disconnected");
	}

	public void putOnTable(StoneSet newStoneSet) {
		// TODO Auto-generated method stub
		this.table.add(newStoneSet);
		for (ClientPlayer c : clients)
			c.sendPutTable(table);
	}

	public void endGame() {
		// TODO Auto-generated method stub
		EndGame endGame = new EndGame();
		for (ClientPlayer c : clients) {
			endGame.addPlayer(c.getPlayer());
		}
		endGame.setWinner();
		for (ClientPlayer c : clients) {
			c.sendEndGame(endGame);
		}		
	}

}
