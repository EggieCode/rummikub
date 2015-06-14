package org.eggiecode.rummikub.view;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;
import org.eggiecode.rummikub.models.serverbrowser.ServerItem;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.Path.Step;

public class ServerSelectState extends BasicGameState {
	public static int ID = 2;

	private ArrayList<ServerItem> items = new ArrayList();
	private ArrayList<ServerBroadcastReply> servers;
	private ServerItem selectedServer;

	private boolean connecting = false;

	@Override
	public void init(GameContainer container, StateBasedGame stateBasedGame)
			throws SlickException {
		// TODO Auto-generated method stub
		Game game = (Game) stateBasedGame;
		servers = game.getRunnibkubController().getServerFound();

		game.getRunnibkubController().searchServer();
	}

	@Override
	public void render(GameContainer container, StateBasedGame stateBasedGame,
			Graphics g) throws SlickException {
		// TODO Auto-generated method stub

		Game game = (Game) stateBasedGame;
		g.translate(0, 50);

		g.setColor(Color.orange);
		g.drawRect(50, 0, container.getWidth() - 100, 30);
		g.drawLine(container.getWidth() - 300, 0, container.getWidth() - 300,
				30);
		g.drawLine(container.getWidth() - 150, 0, container.getWidth() - 150,
				30);
		g.drawString("IP:", container.getWidth() - 290, 5);
		g.drawString("PLAYERS:", container.getWidth() - 140, 5);

		g.drawString("SERVER NAME:", 70, 5);

		g.fillRect(50, 0, container.getWidth() - 100, 3);
		g.fillRect(50, 27, container.getWidth() - 100, 3);
		g.translate(0, 2);
		for (ServerItem i : items) {
			g.translate(0, 30);
			i.draw(container, (Game) game, g);
		}
		g.translate(0, (-30 * items.size()) - 52);

		if (connecting) {
			if (game.getRunnibkubController().isConnected()
					&& !game.getRunnibkubController().isGameReady()) {

				g.drawString("Waiting for other player!",
						container.getWidth() / 2 - 200,
						container.getHeight() / 2);
			} else {
				g.drawString("Connecting to "
						+ selectedServer.getServerReply().getName(),
						container.getWidth() / 2 - 200,
						container.getHeight() / 2);

			}
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame stateBasedGame,
			int delta) throws SlickException {
		Game game = (Game) stateBasedGame;
		// TODO Auto-generated method stub
		if (!connecting) {
			if (servers.size() != items.size()) {
				ArrayList<ServerBroadcastReply> notExisting = (ArrayList<ServerBroadcastReply>) servers
						.clone();
				for (ServerItem i : items) {
					if (servers.contains(i.getServerReply())) {
						notExisting.remove(i.getServerReply());
					} else {
						items.remove(i);
					}
				}
				for (ServerBroadcastReply reply : notExisting) {
					ServerItem i = new ServerItem(reply);
					i.init(container, (Game) game);
					if (items.size() == 0) {
						i.setSelected(true);
						this.selectedServer = i;
					}
					items.add(i);
				}
			}

			if (game.getInputController().getInputDataPressed().isMoveDown()) {
				selectedServer.setSelected(false);
				selectedServer = items.get((items.indexOf(selectedServer) + 1)
						% items.size());
				selectedServer.setSelected(true);

			}
			if (game.getInputController().getInputDataPressed().isMoveUp()) {
				selectedServer.setSelected(false);
				int i = (items.indexOf(selectedServer) - 1) % items.size();
				if (i < 0)
					i = -i;
				selectedServer = items.get(i);

				selectedServer.setSelected(true);
			}

			if (game.getInputController().getInputDataPressed().isFire()) {
				connecting = true;

				game.getRunnibkubController().connect(
						selectedServer.getServerReply());

			}
		} else {
			if (!game.getRunnibkubController().isConnecting()
					&& !game.getRunnibkubController().isConnected())
				connecting = false;
			if (game.getRunnibkubController().isGameReady()) {
				
				game.enterState(GameState.ID);
			}
		}

		for (ServerItem i : items)
			i.update(container, (Game) game, delta);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
