package org.eggiecode.rummikub.models.game;

import java.awt.Color;

import org.eggiecode.rummikub.client.Game;
import org.eggiecode.rummikub.models.GameModel;
import org.eggiecode.rummikub.models.core.Stone;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class StoneModel implements GameModel {
	// -1 is joker
	private Stone stone;
	private Vector2f position;
	private Image stoneImg;
	private UnicodeFont font;
	private Vector2f textPosition;
	private String number;

	private Rectangle border;
	private boolean isInitialized = false;

	public StoneModel(Stone s, Vector2f position) {
		super();
		this.stone = s;
		if (this.position == null)
			this.position = new Vector2f();
		else
			this.position = position;
	}

	@Override
	public void init(GameContainer container, Game game) throws SlickException {
		// TODO Auto-generated method stub
		font = new UnicodeFont(new java.awt.Font(java.awt.Font.DIALOG,
				java.awt.Font.PLAIN, 20));
		font.addAsciiGlyphs();
		font.addGlyphs(400, 600);
		font.getEffects().add(new ColorEffect(new Color(stone.getColor())));
		font.loadGlyphs();
		font.setPaddingLeft(0);

		number = String.valueOf(stone.getNumber());
		if (stone.getNumber() == -1) {
			number = "J";
		}
		if (number.length() == 2) {
			textPosition = new Vector2f(12, 18);
		} else {
			textPosition = new Vector2f(20, 18);
		}
		border = new Rectangle(position.x - 1, position.y - 1, 51, 76);
		isInitialized = true;
		if (position == null)
			position = new Vector2f();
	}

	@Override
	public void draw(GameContainer container, Game game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(org.newdawn.slick.Color.white);
		g.fillRect(position.x, position.y, 50, 75);
		g.setColor(org.newdawn.slick.Color.black);
		g.draw(border);
		font.drawString(position.x + textPosition.x, position.y
				+ textPosition.y, number);
	}

	@Override
	public void update(GameContainer container, Game game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	public Stone getStone() {
		return stone;
	}

	public Vector2f getPosition() {
		return position;
	}

	public boolean isVectorCollision(int x, int y) {
		return border.contains(x, y);

	}

	public void setPosition(float x, float y) {
		if (position == null)
			position = new Vector2f();
		this.position.x = x;
		this.position.y = y;
		border.setX(x);
		border.setY(y);
	}

	public boolean isInitialized() {
		return isInitialized;
	}
}