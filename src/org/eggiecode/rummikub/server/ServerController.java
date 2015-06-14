package org.eggiecode.rummikub.server;

import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.server.objects.ClientPlayer;
import org.eggiecode.rummikub.server.objects.Command;

public class ServerController {
	private ArrayList<Stone> stones = new ArrayList();
	protected ArrayList<ClientPlayer> clients = new ArrayList();
	private Table table;
	private Command command;
	private static final int colors[] = new int[] { 0x344CFA, 0xff0000,
			0xC48206, 0x0 };
	private static final int numbers[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13 };

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

	public void startGame() {
		stones.clear();

	}

	public void addClient(ClientPlayer player) {
		if (clients.size() < 2) {
			clients.add(player);
		}
	}

}
