package org.eggiecode.rummikub.client;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Client {
	public static final String gamename = "Rimmikub";
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(960,720, false);
            appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
