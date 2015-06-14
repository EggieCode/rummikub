package org.eggiecode.rummikub.controllers;

import java.util.ArrayList;
import java.util.Random;

import org.eggiecode.rummikub.models.core.Player;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;

public class RunnikubController {

	private ArrayList<Stone> stones = new ArrayList();
	private ArrayList<StoneSet> stoneSets = new ArrayList<>();
	private Table table;
	private Player player;

	private Random random = new Random();
	private static final int colors[] = new int[] { 0x344CFA, 0xff0000,
			0xC48206, 0x0 };
	private static final int numbers[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13 };

	public RunnikubController() {
		table = new Table();
	}

	private void generateStone() {
		stones.clear();
		for (int i = 0; i < 2; i++) {
			for (int color : colors) {
				for (int number : numbers) {
					stones.add(new Stone(number, color));
				}
			}

			stones.add(new Stone(-1, colors[i]));
		}
	}

	public void givePlayerNewStone() {
		int i = 0;
		Stone s = stones.get(i);
		while (s.isOnPlayerBoard() || s.isOnTable()) {
			s = stones.get(++i);
		}
		// Stone s = stones.get(random.nextInt(stones.size()));
		// while (s.isOnPlayerBoard() || s.isOnTable()) {
		// s = stones.get(random.nextInt(stones.size()));
		// }
		player.addStone(s);
	}

	public Table getTable() {
		return table;
	}

	public Player getPlayer() {
		return player;
	}

	public void startGame() {
		generateStone();
		table = new Table();
		player = new Player();
		for (int i = 0; i < 14; i++) {
			givePlayerNewStone();
		}
	}

	public boolean addToTable(StoneSet set) {
		if (!set.isValid())
			return false;
		for (Stone s : set.getStones()) {
			s.setOnPlayerBoard(false);
			s.setOnTable(true);
		}
		table.add(set);
		return true;
	}
}
