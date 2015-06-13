package org.eggiecode.rummikub.models.game;

import java.util.ArrayList;
import java.util.LinkedList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class PlayerTable implements GameModel {

	private ArrayList<StoneModel> stones = new ArrayList();
	private ArrayList<StoneModel> tmpStones = new ArrayList();

	private LinkedList<StoneModel> stoneDek = new LinkedList();

	private Button putinTable;

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		int x = 10;
		int y = container.getHeight() - 290;
		for (Stone s : game.getRunnibkubController().getPlayer().getStones()) {
			stones.add(new StoneModel(s, new Vector2f(x, y)));
			x += 65;
			if (x + 300 > container.getWidth()) {
				y += 100;
				x = 10;
			}
		}

		for (StoneModel s : stones) {
			s.init(container, game);
		}
		putinTable = new Button(new Vector2f(container.getWidth() / 3 * 2,
				container.getHeight() - 375), "Put in table", 175, 40);
		putinTable.init(container, game);
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

		g.setColor(new Color(0x8B4513));
		g.fillRect(0, container.getHeight() - 300, container.getWidth(), 300);

		g.setColor(new Color(0xf1f1f1));
		g.fillRect(container.getWidth() - 200, container.getHeight() - 300,
				200, 300);
		g.setColor(new Color(0xffffff));
		g.drawRect(0, container.getHeight() - 300, container.getWidth(), 1);

		g.fillRect(container.getWidth() / 3 - 30, container.getHeight() - 390,
				container.getWidth() / 3, 90);

		for (StoneModel m : stones)
			m.draw(container, game, g);
		putinTable.draw(container, game, g);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

		Input input = container.getInput();
		StoneModel selectedStone = null;

		boolean fixOrder = false;

		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
			for (StoneModel s : stones) {
				if (s.isVectorCollision(input.getMouseX(), input.getMouseY())) {
					if (!stoneDek.contains(s)) {
						selectedStone = s;
					} else {
						fixOrder = true;
						stoneDek.remove(s);
					}
					break;
				}
			}

			if (putinTable.contains(input.getMouseX(), input.getMouseY())) {
				StoneSet set = new StoneSet();

				for (StoneModel s : stoneDek) {
					set.add(s.getStone());
				}

				if (game.getRunnibkubController().addToTable(set)) {
					for (StoneModel s : stoneDek) {
						stones.remove(s);
					}
					stoneDek.clear();
				}
			}

		}

		if (selectedStone != null) {
			fixOrder = true;
			selectedStone.setPosition(
					container.getWidth() / 3 + (stoneDek.size() * 60) - 15,
					container.getHeight() - 383);

			this.stoneDek.add(selectedStone);
		}

		if (fixOrder) {

			int x = 10;
			int y = container.getHeight() - 290;
			for (StoneModel s : stones) {
				if (!stoneDek.contains(s)) {
					s.setPosition(x, y);
					x += 65;
					if (x + 300 > container.getWidth()) {
						y += 100;
						x = 10;
					}
				}
			}
		}

		putinTable.update(container, game, delta);

	}

}
