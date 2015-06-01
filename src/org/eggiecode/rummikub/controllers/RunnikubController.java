package org.eggiecode.rummikub.controllers;


import java.util.ArrayList;
import java.util.Random;

import org.eggiecode.rummikub.models.core.Stone;

public class RunnikubController {


	private ArrayList<Stone> stones = new ArrayList();
	private Random random = new Random();
	private static final int colors[] = new int[] { 0x344CFA, 0xff0000,
			0xC48206, 0x0 };
	private static final int numbers[] = new int[] { -1, 1, 2, 3, 4, 5, 6, 7,
			8, 9, 10, 11, 12, 13 };
	
	public RunnikubController() {
		
	}
	
	public void generateStone() {
		stones.clear();
		for(int color : colors) {
			for(int number: numbers) {
				stones.add(new Stone(number, color));
			}
		}
	}
	
	public Stone getNewStone() {
		Stone s = null;
		while(!s.isOnPlayerBoard() && !s.isOnTable()) {
			s = stones.get(random.nextInt(stones.size()));
		}
		return s;
	}
}
