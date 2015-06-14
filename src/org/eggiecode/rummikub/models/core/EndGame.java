package org.eggiecode.rummikub.models.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EndGame implements Serializable {
	private Player winner;
	private ArrayList<Player> players = new ArrayList();

	public void addPlayer(Player p) {
		players.add(p);
	}

	public void setWinner() {
		for(Player p : players) {
			int score = 0;
			for(Stone s : p.getStones()) {
				score += s.getNumber();
			}
			p.setScore(score);
		}
		Collections.sort(players, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {

				return o1.getScore() - o2.getScore();
			}
		});
		winner = players.get(0);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getWinner() {
		return winner;
	}

}
