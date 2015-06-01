package org.eggiecode.rummikub.models.menu;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public abstract class Menu implements GameModel {
	protected ArrayList<MenuItem> menuItems = new ArrayList();
	int menuItemSelected = 0;
	Sound select;
	Sound accepted;

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		
		for (MenuItem item : menuItems)
			item.init(container, game);
		menuItems.get(0).setSelected(true);
		select = new Sound("assets/sfx/select.wav");
		accepted = new Sound("assets/sfx/accepted.wav");
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		for (MenuItem item : menuItems)
			item.draw(container, game, g);
		
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		for (MenuItem item : menuItems)
			item.update(container, game, delta);
		
		if (game.getInputController().getInputDataPressed().isMoveUp()) {
			menuItems.get(menuItemSelected).setSelected(false);
			select.play(1f,0.2f);
			System.out.println(game.getSettingsController().getConfig().getSoundSFX());
			if (menuItemSelected == 0)
				menuItemSelected = menuItems.size() - 1;
			else
				menuItemSelected--;
			menuItems.get(menuItemSelected).setSelected(true);

		} else if (game.getInputController().getInputDataPressed().isMoveDown()) {
			menuItems.get(menuItemSelected).setSelected(false);
			select.play(1f,0.2f);
			if (menuItemSelected == menuItems.size() - 1)
				menuItemSelected = 0;
			else
				menuItemSelected++;
			menuItems.get(menuItemSelected).setSelected(true);

		}
		
		if (game.getInputController().getInputDataPressed().isFire()) {
			accepted.play();
		}
	}

	protected void addMenuItem(MenuItem item) {
		menuItems.add(item);
	}
	
	protected int getSelectedMenuItem() {
		return menuItemSelected;
	}
}
