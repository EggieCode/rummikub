package org.eggiecode.rummikub.models.menu;

import java.awt.event.ActionEvent;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameActionListener;
import org.eggiecode.rummikub.view.GameState;
import org.eggiecode.rummikub.view.StartState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class PauseMenu extends Menu implements GameActionListener {
	GameState gameState;

	public PauseMenu(GameState gameScene) {
		this.gameState = gameScene;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub

		addMenuItem(new MenuItem(100 * 2, "Resume"));
		addMenuItem(new MenuItem(100 * 3, "Back to main menu"));
		for (MenuItem item : menuItems)
			item.addActionListener(this);
		super.init(container, game);
		// optionsMenu.init(container, game);
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		super.draw(container, game, g);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		super.update(container, game, delta);

	}

	@Override
	public void actionPerformed(GameContainer container, Game game, int delta,
			ActionEvent e) throws SlickException {
		// TODO Auto-generated method stub
		switch (getSelectedMenuItem()) {
		case 0:
			gameState.unpause();
			break;
		case 1:
			game.enterState(StartState.ID);
			gameState.unpause();
			break;
		default:
			break;
		}
	}

}
