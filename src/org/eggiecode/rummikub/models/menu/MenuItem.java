package org.eggiecode.rummikub.models.menu;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameActionListener;
import org.eggiecode.rummikub.models.GameModel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

public class MenuItem implements GameModel {
	UnicodeFont font1;
	private int x;
	private int y;
	private boolean selected = false;
	private String text;
	private int height;
	private int width;
	private ArrayList<GameActionListener> actionListeners = new ArrayList();
	public MenuItem( int y, String text) throws SlickException {
		this.y = y;
		this.text = text;

	}

	@Override
	public void init(GameContainer container, Game game)
			throws SlickException {
		// TODO Auto-generated method stub
		
		font1 = game.getFontController().getFontMenu();
		height = font1.getHeight(text);
		width = font1.getWidth(text);

		x = container.getWidth() / 2 - width / 2;

	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.yellow);
		font1.drawString(x, y, text );
		if (selected) {
			g.drawRect(x - 5, y - 5, width + 15, height + 15);
		}
		
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(selected && game.getInputController().getInputDataPressed().isFire())
			for(GameActionListener listener : actionListeners)
				listener.actionPerformed(container, game, delta, null);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void addActionListener(GameActionListener listener) {
		actionListeners.add(listener);
	}
}
