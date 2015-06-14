package org.eggiecode.rummikub.view;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.core.EndGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndGameState extends BasicGameState {
	
	public static final int ID = 3;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		Game game = (Game) stateBasedGame;
		EndGame endGame = game.getRunnibkubController().getEndGame();
		if(endGame.getWinner().getID() == game.getRunnibkubController().getPlayer().getID()) {
			g.drawString("You Win!!", container.getWidth() /2 -100, container.getHeight()/2);
		} else {
			
			g.drawString("You Lost!!", container.getWidth() /2 -100, container.getHeight()/2);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
