package org.eggiecode.rummikub.client;

import java.io.FileNotFoundException;

import org.eggiecode.rummikub.controllers.AnimationsController;
import org.eggiecode.rummikub.controllers.FontController;
import org.eggiecode.rummikub.controllers.InputController;
import org.eggiecode.rummikub.controllers.RunnikubController;
import org.eggiecode.rummikub.controllers.SettingsController;
import org.eggiecode.rummikub.view.GameState;
import org.eggiecode.rummikub.view.StartState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.yamlbeans.YamlException;

public class Game extends StateBasedGame {
	AnimationsController animationsController;
	SettingsController settingsController;
	InputController inputController;
	FontController fontController;
	
	RunnikubController runnikubController;
	
	public Game(String name) {
		super(name);
		animationsController = new AnimationsController();
		settingsController = new SettingsController();
		inputController = new InputController(this);
		fontController = new FontController();
		runnikubController = new RunnikubController();
		
		this.addState(new StartState());
		this.addState(new GameState());
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		try {
			animationsController.loadData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new SlickException(e.getMessage());
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SlickException(e.getMessage());
		}
		
		settingsController.loadConfig();
		fontController.loadFonts();
		
	}
	
	@Override
	protected void preUpdateState(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		super.preUpdateState(container, delta);
		inputController.preUpdateState(container, delta);
	}
	
	public AnimationsController getAnimationsController() {
		return animationsController;
	}

	public SettingsController getSettingsController() {
		return settingsController;
	}

	public InputController getInputController() {
		return inputController;
	}

	public FontController getFontController() {
		return fontController;
	} 
	
	public RunnikubController getRunnibkubController() {
		return runnikubController;
	}

}
