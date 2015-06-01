package org.eggiecode.rummikub.models;

import java.awt.event.ActionEvent;

import org.eggiecode.rummikub.client.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public interface GameActionListener {
	public void actionPerformed(GameContainer container, Game game, int delta, ActionEvent e) throws SlickException;
}
