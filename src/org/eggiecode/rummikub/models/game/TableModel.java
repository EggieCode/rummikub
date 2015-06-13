package org.eggiecode.rummikub.models.game;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TableModel implements GameModel{
	private ArrayList<StoneSetModel> stoneSets = new ArrayList();

	public TableModel() {
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		stoneSets = game.getModelController().getTableStoneSets();
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		for(StoneSetModel s : stoneSets)
			s.draw(container, game, g);
			
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		for(StoneSetModel s : stoneSets)
			s.update(container, game, delta);
	}
}
