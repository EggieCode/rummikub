package org.eggiecode.rummikub.models.game;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class StoneSetModel implements GameModel{
	private StoneSet stoneSet;

	public StoneSetModel(StoneSet stoneSet) {
		this.stoneSet = stoneSet;
	}

	public StoneSet getStoneSet() {
		return stoneSet;
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
