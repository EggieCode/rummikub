package org.eggiecode.rummikub.server;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.server.objects.ClientPlayer;
import org.eggiecode.rummikub.server.objects.Command;

public class ServerController {
	private ArrayList<Stone> stones = new ArrayList();
	protected ArrayList<ClientPlayer> clients = new ArrayList();
	private Table table;
	private Command command;
	private int playerOnTurn;
	private Random random = new Random();
	private ArrayList<Stone> beginStones;
	private static final int colors[] = new int[] { 0x344CFA, 0xff0000,
			0xC48206, 0x0 };
	private static final int numbers[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13 };
	private Send_Receive s_r = new Send_Receive();

	public ServerController(Server server) {
		table = new Table();
		for (int i = 0; i < 2; i++) {
			for (int color : colors) {
				for (int number : numbers) {
					stones.add(new Stone(number, color));
				}
			}

			stones.add(new Stone(-1, colors[i]));
		}

	}

	public void addClient(ClientPlayer player) {
		if (clients.size() < 2) {
			clients.add(player);
		}
	}

	public ArrayList<Stone> generateBeginStones() {
		beginStones.clear();
		Stone s = stones.get(random.nextInt(stones.size()));
		while (s.isOnPlayerBoard() || s.isOnTable()) {
			s = stones.get(random.nextInt(stones.size()));
		}

		beginStones.add(s);
		return beginStones;
	}

	public void nextturn(int playNum) {
		switch (playNum) {
		case 1:
			s_r.sendTurn(clients.get(0).getObjectOutput(), false);
			s_r.sendTurn(clients.get(1).getObjectOutput(), true);
			s_r.sendPutTable(clients.get(0).getObjectOutput(), table);
			playerOnTurn = 1;
			break;
		case 2:
			s_r.sendTurn(clients.get(1).getObjectOutput(), true);
			s_r.sendTurn(clients.get(0).getObjectOutput(), false);
			s_r.sendPutTable(clients.get(1).getObjectOutput(), table);
			playerOnTurn = 2;
			break;
		}

	}

	public void getPutTable() {
		switch (playerOnTurn) {
		case 1:
			s_r.receivePutTable(clients.get(0).getObjectInput());
		case 2:
			s_r.receivePutTable(clients.get(1).getObjectInput());
		}
	}

	public void SendBeginStones() {
		generateBeginStones();
		s_r.sendBeginStones(clients.get(0).getObjectOutput(), beginStones);
		generateBeginStones();
		s_r.sendBeginStones(clients.get(1).getObjectOutput(), beginStones);
	}

	public void startGame() {
		stones.clear();

	}

}
