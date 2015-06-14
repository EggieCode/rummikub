package org.eggiecode.rummikub.models.game;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.effects.FireEmitter;

public class TableModel implements GameModel{
	private ArrayList<StoneSetModel> stoneSets = new ArrayList();
	private boolean fixOrder = false;
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
		{
			s.draw(container, game, g);
			
		g.setColor(Color.yellow);
			g.drawRect(s.getPostion().x -4, s.getPostion().y -4, s.getWidth() + 8 , s.getHeigth() + 4);	
                       
		}
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		fixOrder = game.getModelController().isStonesUpdated();
		
		for(StoneSetModel s : stoneSets)
			s.update(container, game, delta);
		

		
		if(fixOrder)
			fixOrder(container);
	}
	
	private void fixOrder(GameContainer container) {
		fixOrder = false;
		if(stoneSets.size() <= 0)
			return;
		int x = 40;
		int y = 40;
		
		for(int i = 0; i <stoneSets.size(); i++ ) {
			StoneSetModel s = stoneSets.get(i);
					
			s.setPosition(x,y);
			x += s.getWidth() + 15;
			if(i < stoneSets.size() -1 && (x + stoneSets.get(i+1).getWidth()) >= container.getWidth() -30) {
				x = 40;
				y += s.getHeigth() + 10;
			}
			
			
		}
	}
}
