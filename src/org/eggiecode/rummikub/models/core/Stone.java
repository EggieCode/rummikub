package org.eggiecode.rummikub.models.core;

import java.io.Serializable;

public class Stone implements Serializable {
	private final int number;

	private final int color;
	private boolean onPlayerBoard = false;
	private boolean onTable = false;

	private final int numStone;

	public Stone(int number, int color, int numStone) {
		this.number = number;
		this.color = color;
		this.numStone = numStone;
	}


	public boolean isOnPlayerBoard() {
		return onPlayerBoard;
	}

	public void setOnPlayerBoard(boolean onPlayerBoard) {
		this.onPlayerBoard = onPlayerBoard;
	}

	public boolean isOnTable() {
		return onTable;
	}

	public void setOnTable(boolean onTable) {
		this.onTable = onTable;
	}

	public int getNumber() {
		return number;
	}

	public int getColor() {
		return color;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this)
			return true;
		if (!(obj instanceof Stone))
			return false;
		return ((Stone) obj).color == color && ((Stone) obj).number == number && ((Stone) obj).numStone == numStone;
	}
}
