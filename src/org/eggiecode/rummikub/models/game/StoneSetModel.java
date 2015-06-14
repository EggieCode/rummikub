package org.eggiecode.rummikub.models.game;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class StoneSetModel implements GameModel{
	private ArrayList<StoneModel> stones = new ArrayList();
	private StoneSet stoneSet;
	private Vector2f postion;
	
	public StoneSetModel(StoneSet stoneSet) {
		this.stoneSet = stoneSet;
		postion = new Vector2f();
	}

	public StoneSet getStoneSet() {
		return stoneSet;
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		int x = 0;
		int y = 0;
		for(Stone s : stoneSet.getStones()) {
			StoneModel stoneModel;
			stones.add(stoneModel = game.getModelController().getStoneModel(s));
			
			stoneModel.setPosition(x, y);
			x += 60;
		}
		
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		g.translate(postion.x, postion.y);
		for(StoneModel s : stones) {
			s.draw(container, game, g);
		}
		g.translate(-postion.x, -postion.y);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		
		
		for(StoneModel s : stones)
			s.update(container, game, delta);
	}

	public void add(StoneModel stoneModel) {
		// TODO Auto-generated method stub
		stones.add(stoneModel);
	}

	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		postion.x = x;
		postion.y =y;
	}

	public int getWidth() {
		return (int) (stones.get(stones.size()-1).getPosition().x + 50); 
		
	}
	
	public int getHeigth() {
		return  85;
	}
	
	public Vector2f getPostion() {
		return postion;
	}
}
