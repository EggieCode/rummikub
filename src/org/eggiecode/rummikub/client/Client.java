package org.eggiecode.rummikub.client;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Client {
	public static final String gamename = "Rimmikub";

	public static AppGameContainer appgc;
	public static Game game;

	public static void main(String[] args) {
		try {
			appgc = new AppGameContainer(game = new Game(gamename));
			appgc.setDisplayMode(1200, 900, false);
			appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
