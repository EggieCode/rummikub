package org.eggiecode.rummikub.server.objects;

import java.io.Serializable;

public enum Command implements Serializable {
	READY, GAME_START, GAME_DONE, PLAYER_TURN, NEW_STONE, STONES_SET_THROW, END_TURN;
}
