package org.eggiecode.rummikub.models.game;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.eggiecode.rummikub.models.core.Stone;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class StoneModel implements GameModel {
	// -1 is joker
	private Stone stone;
	private Point position;
	private Image stoneImg;
	
	public StoneModel(Stone s) {
		super();
		this.stone = s;
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

}