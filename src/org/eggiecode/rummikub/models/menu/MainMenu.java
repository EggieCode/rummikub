package org.eggiecode.rummikub.models.menu;

import java.awt.event.ActionEvent;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameActionListener;
import org.eggiecode.rummikub.view.ServerSelectState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainMenu extends Menu implements GameActionListener {
	OptionsMenu optionsMenu;
	boolean optionsMenuSelected = false;
 
	public  MainMenu() {
		// TODO Auto-generated constructor stub
		optionsMenu = new OptionsMenu(this);
	}
	
	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		
		

		addMenuItem(new MenuItem(100 * 2, "Search Server"));
		addMenuItem(new MenuItem(100 * 3, "Options"));
		addMenuItem(new MenuItem(100 * 4, "Exit Game"));
		for (MenuItem item : menuItems)
			item.addActionListener(this);
		super.init(container, game);
		optionsMenu.init(container, game);
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		if (optionsMenuSelected)
			optionsMenu.draw(container, game, g);
		else
			super.draw(container, game, g);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if (optionsMenuSelected)
			optionsMenu.update(container, game, delta);
		else
			super.update(container, game, delta);

	}

	@Override
	public void actionPerformed(GameContainer container, Game game, int delta,
			ActionEvent e) throws SlickException {
		// TODO Auto-generated method stub
		switch (getSelectedMenuItem()) {
		case 0:
			game.enterState(ServerSelectState.ID);
			break;

		case 1:
			optionsMenuSelected = true;
			break;
		case 2:
			System.exit(0);
			break;
		default:
			break;
		}
	}

}
