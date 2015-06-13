package org.eggiecode.rummikub.models.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class StoneSet implements Serializable {

	public static void main(String[] ars) {
		StoneSet s = new StoneSet();

		if (s.isValid())
			System.out.println("Is goed");
		else
			System.out.println("Is fout");

	}

	public void add(Stone stone) {
		set.add(stone);

	}

	private LinkedList<Stone> set = new LinkedList();
	public Stone[] getStones() {
		return set.toArray(new Stone[0]);
	}
	public boolean isValid() {
		if(set.size() < 3)
			return false;
		if (validateNumberInOrder()){
			return true; 
		}
		if (validateNumbersASC()) {
			return true;
		}
		if (validateNumbersDESC()) {
			
			return true;
		}
		return false;

	}

	private boolean validateNumbersDESC() {

		boolean isValid = true;
		boolean joker = false;

		// Cijfer van laag naar hoog
		int value = set.get(set.size() - 1).getNumber() -1; // 1 (0)
		int color = set.get(set.size() - 1).getColor();

		for (int i = set.size() - 1; i >= 0; i--) { // 1 2 3
			Stone s = set.get(i);
		
			if (joker == true && s.getNumber() == -1) {
				return false;
			}
			if (s.getNumber() == -1)
				joker = true;
			if (color != s.getColor() && s.getNumber() != -1)
				return false;

			if (s.getNumber() - 1 == value|| s.getNumber() != -1) {
				value = s.getNumber();
			} else {
				return false;
			}
		}

		return true;
	}

	private boolean validateNumbersASC() {
		boolean isValid = true;
		boolean joker = false;

		// Cijfer van laag naar hoog
		int value = set.get(0).getNumber() -1; // 1 (0)
		int color = set.get(0).getColor();
		for (Stone s : set) { // 1 2 3

			if (joker == true && s.getNumber() == -1) {
				return false;
			}
			if (s.getNumber() == -1)
				joker = true;
			if (color != s.getColor() && s.getNumber() != -1)
				return false;

			if (s.getNumber() - 1 == value || s.getNumber() != -1) {
				value = s.getNumber();
			} else {
				return false;
			}
		}

		return true;
	}

	private boolean validateNumberInOrder() {
		boolean joker = false;
		int value = set.get(0).getNumber();
		ArrayList<Integer> colors = new ArrayList();
		// Cijfers het zelfde
		for (Stone s : set) {
			if (joker == true && value == -1) {
				return false;
			}
			if (s.getNumber() == -1)
				joker = true;
			if (colors.contains(s.getColor()) && s.getNumber()!= -1)

			{
				return false;
			}
			colors.add(s.getColor());
			if (value != s.getNumber() && s.getNumber() != -1) {
				return false;
			}
		}
		return true;

	}

}
