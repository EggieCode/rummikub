package org.eggiecode.rummikub.models.game;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Button implements GameModel{
	
	
	private Vector2f postion;
	private String text;
	private int width;
	private int height;

	private Rectangle box;
	private Color color;
	public Button(Vector2f postion, String text, int width, int height) {
		this.postion = postion;
		this.text = text;
		this.width = width;
		this.height = height;
		this.box = new Rectangle(postion.x, postion.y, width, height);
		this.color = Color.green;
	}
	
	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.draw(box);
		g.setColor(new Color(10,10,10, 40));
		g.fill(box);
		g.setColor(color);
		g.drawString(" >> " + text, postion.x + 10, postion.y + 10);
		
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		if(box.contains(input.getMouseX(),input.getMouseY())) {
			color = Color.magenta;
			
			
		} else{
			color = Color.green;
		}
	}
	
	public boolean contains(int x, int y) {
		return box.contains(x,y);
	}
	
}
