package org.eggiecode.rummikub.models.serverbrowser;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ServerItem implements GameModel {

	private ServerBroadcastReply reply;
	private boolean selected;

	public ServerItem(ServerBroadcastReply reply) {
		this.reply = reply;
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);

		g.drawRect(50, 0, container.getWidth() - 100, 30);
		g.drawLine(container.getWidth() - 300, 0, container.getWidth() - 300,
				30);
		g.drawLine(container.getWidth() - 150, 0, container.getWidth() - 150,
				30);

		g.setColor(Color.orange);
		g.drawString(reply.getName(), 70, 5);

		g.drawString(reply.getAddress().getHostAddress(),
				container.getWidth() - 290, 5);
		g.drawString(String.valueOf(reply.getPlayers()),
				container.getWidth() - 140, 5);

		if (selected) {
			g.drawString(">", 57, 5);

		}

	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	public ServerBroadcastReply getServerReply() {
		// TODO Auto-generated method stub
		return reply;
	}

	public void setSelected(boolean b) {
		// TODO Auto-generated method stub

		this.selected = b;
	}

}
