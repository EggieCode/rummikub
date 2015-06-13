package org.eggiecode.rummikub.view;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.game.PlayerTable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public static final int ID = 1;
	private PlayerTable playerTable;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame stateBasedGame)
			throws SlickException {
		// TODO Auto-generated method stub
		Game game = (Game) stateBasedGame;
		game.getRunnibkubController().startGame();
		playerTable = new PlayerTable();
		playerTable.init(container,  game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		playerTable.draw(container, (Game) game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		playerTable.update(container, (Game) game, delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	public void unpause() {
		// TODO Auto-generated method stub
		
	}

}
