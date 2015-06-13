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

	public Stone getNewStone() {
		Stone s = stones.get(random.nextInt(stones.size()));
		while (s.isOnPlayerBoard() || s.isOnTable()) {
			s = stones.get(random.nextInt(stones.size()));
		}
		return s;
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
			player.addStone(getNewStone());
		}
	}

	public boolean addToTable(StoneSet set) {
		if(!set.isValid())
			return false;
		table.add(set);
		return true;
	}
}
