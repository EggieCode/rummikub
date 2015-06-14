package org.eggiecode.rummikub.view;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.game.PlayerTable;
import org.eggiecode.rummikub.models.game.TableModel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public static final int ID = 1;
	private PlayerTable playerTable;
	private TableModel table;

	@Override
	public void init(GameContainer container, StateBasedGame stateBasedGame)
			throws SlickException {
		// TODO Auto-generated method stub
		Game game = (Game) stateBasedGame;
		playerTable = new PlayerTable();
		playerTable.init(container, game);
		table = new TableModel();
		table.init(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		

		Game game = (Game) stateBasedGame;
		playerTable.draw(container, (Game) game, g);
		table.draw(container, (Game) game, g);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame stateBasedGame,
			int delta) throws SlickException {
		Game game = (Game) stateBasedGame;

		playerTable.update(container, (Game) game, delta);
		table.update(container, (Game) game, delta);
		
		if(game.getRunnibkubController().getEndGame() != null) {
			game.enterState(EndGameState.ID);
		}
		
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
