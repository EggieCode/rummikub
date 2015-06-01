package org.eggiecode.rummikub.models;

import org.eggiecode.rummikub.client.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface GameModel {

	public void init(GameContainer container, Game game)
			throws SlickException;

	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException;

	
	public void update(GameContainer container, Game game, int delta)
			throws SlickException;

}
