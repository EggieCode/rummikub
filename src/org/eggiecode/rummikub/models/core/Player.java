package org.eggiecode.rummikub.models.core;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	private ArrayList<Stone> stones = new ArrayList();
	
	public void addStone(Stone s) {
		stones.add(s);
		s.setOnPlayerBoard(true);
	}
	
	public Stone[] getStones() {
		return stones.toArray(new Stone[0]);
	}
}
