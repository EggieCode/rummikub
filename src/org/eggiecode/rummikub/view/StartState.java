package org.eggiecode.rummikub.view;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.menu.MainMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartState extends BasicGameState {

	public static final int ID = 0;
private MainMenu mainMenu = new MainMenu();
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		mainMenu.init(container, (Game) game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		mainMenu.draw(container, (Game) game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		mainMenu.update(container, (Game) game, delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
