package org.eggiecode.rummikub.models.core;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private ArrayList<Stone> stones = new ArrayList();
	public int score;
	private int id;

	public Player(int id) {
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public void addStone(Stone s) {
		stones.add(s);
		s.setOnPlayerBoard(true);
	}

	public void addStones(ArrayList<Stone> stones) {
		for (Stone s : stones) {
			stones.add(s);
			s.setOnPlayerBoard(true);
		}
	}

	public Stone[] getStones() {
		return stones.toArray(new Stone[0]);
	}

	public void removeStone(Stone s) {
		stones.remove(s);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
