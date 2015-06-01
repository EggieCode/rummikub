package org.eggiecode.rummikub.models.core;


public class Stone {
	private final int number;

	private final int color;
	private boolean onPlayerBoard = false;
	private boolean onTable = false;

	public Stone(int number, int color) {
		this.number = number;
		this.color = color;
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

}
