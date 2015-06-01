package org.eggiecode.rummikub.models.menu;

import java.awt.event.ActionEvent;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.controllers.SettingsController;
import org.eggiecode.rummikub.models.GameActionListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AudioMenu extends Menu implements GameActionListener {

	public boolean sfxMenuSelected = false;
	OptionsMenu optionsMenu;
	VolumeBar sfxBar;
	VolumeBar musicBar;
	SettingsController settingsController;

	public AudioMenu(OptionsMenu OptionsMenu) {
		// TODO Auto-generated constructor stub
		this.optionsMenu = OptionsMenu;
		

	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub

		addMenuItem(new MenuItem(100 * 2, "SFX"));
		sfxBar = new VolumeBar(100 * 3, game.getSettingsController().getConfig().getSoundSFX());
		addMenuItem(new MenuItem(100 * 4, "Music"));
		musicBar = new VolumeBar(100 * 5, game.getSettingsController().getConfig().getSoundMusic());
		addMenuItem(new MenuItem(100 * 6, "Back"));
		for (MenuItem item : menuItems)
			item.addActionListener(this);
		super.init(container, game);
		
		sfxBar.init(container, game);
		musicBar.init(container, game);
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		super.draw(container, game, g);
		sfxBar.draw(container, game, g);
		musicBar.draw(container, game, g);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		if (this.getSelectedMenuItem() == 0) {
			sfxBar.update(container, game, delta);
			game.getSettingsController().getConfig()
					.setSoundSFX(sfxBar.getBarSelected() / 10);
		}
		if (this.getSelectedMenuItem() == 1) {
			musicBar.update(container, game, delta);
			game.getSettingsController().getConfig()
					.setSoundMusic(musicBar.getBarSelected() / 10);
		}
	}

	@Override
	public void actionPerformed(GameContainer container, Game game, int delta,
			ActionEvent e) throws SlickException {
		switch (this.getSelectedMenuItem()) {
		case 0:
			sfxMenuSelected = true;
			break;
		case 1:
			break;
		case 2:
			optionsMenu.audioMenuSelected = false;
			break;

		}
	}
}