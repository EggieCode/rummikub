package org.eggiecode.rummikub.models.menu;

import java.awt.event.ActionEvent;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameActionListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class OptionsMenu extends Menu implements GameActionListener {

	boolean audioMenuSelected = false;
	boolean creditMenuSelected = false;
	MainMenu mainMenu;
	AudioMenu audioMenu;
	float sizeX, sizeY;
	int timer;

	public OptionsMenu(MainMenu mainMenu) {
		// TODO Auto-generated constructor stub
		this.mainMenu = mainMenu;
		audioMenu = new AudioMenu(this);
		
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub

		addMenuItem(new MenuItem(100 * 2, "Audio"));
		addMenuItem(new MenuItem(100 * 3, "Video"));
		addMenuItem(new MenuItem(100 * 4, "Credits"));
		addMenuItem(new MenuItem(100 * 5, "Back"));
		for (MenuItem item : menuItems)
			item.addActionListener(this);
		super.init(container, game);
		audioMenu.init(container, game);
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		if (audioMenuSelected) {
			audioMenu.draw(container, game, g);
		} else if (creditMenuSelected) {
		} else
			super.draw(container, game, g);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if (audioMenuSelected) {
			audioMenu.update(container, game, delta);
		} else
			super.update(container, game, delta);

		if (timer > 50){
			creditMenuSelected = false;
			timer = 0;
		}
		else if(creditMenuSelected == true)
			timer++;

	}

	@Override
	public void actionPerformed(GameContainer container, Game game, int delta,
			ActionEvent e) throws SlickException {
		switch (this.getSelectedMenuItem()) {
		case 0:
			audioMenuSelected = true;
			break;
		case 1:
			break;
		case 2:
			creditMenuSelected = true;
			break;
		case 3:
			mainMenu.optionsMenuSelected = false;
			break;
		}
	}
}