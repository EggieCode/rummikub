package org.eggiecode.rummikub.models.menu;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class VolumeBar implements GameModel {
	protected ArrayList<Rectangle> rectangles = new ArrayList();

	Rectangle rect1;
	float x, y;
	int barSelected = 4;

	VolumeBar(float y, float barSelected) {
		this.barSelected = (int) (barSelected * 10);
		this.y = y;
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		x = (container.getWidth() / 2) - (20 * 11) + 5;

		for (int i = 0; i < 11; i++) {
			rectangles.add(new Rectangle(x + i * 40, y, 30, 30));
		}

	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		for (int i = 0; i < rectangles.size(); i++)
			if (i == barSelected) {
				g.fill(rectangles.get(i));
				g.draw(rectangles.get(i));
			} else
				g.draw(rectangles.get(i));

	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {

		if (game.getInputController().getInputDataPressed().isMoveLeft()) {
			if (barSelected == 0) {
			} else
				barSelected--;

		} else if (game.getInputController().getInputDataPressed()
				.isMoveRight()) {
			if (barSelected == rectangles.size() - 1) {
			} else
				barSelected++;

		}

	}

	public int getBarSelected() {
		return barSelected;
	}

	public void setBarSelected(int barSelected) {
		this.barSelected = barSelected;
	}

}
