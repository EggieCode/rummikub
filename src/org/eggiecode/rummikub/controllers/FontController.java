package org.eggiecode.rummikub.controllers;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class FontController {
	static UnicodeFont fontMenu = new UnicodeFont(new java.awt.Font(java.awt.Font.DIALOG,
			java.awt.Font.PLAIN, 40));
	
	public void loadFonts() throws SlickException {

		fontMenu.addAsciiGlyphs();
		fontMenu.addGlyphs(400, 600);
		fontMenu.getEffects().add(new ColorEffect(new java.awt.Color(93,163,188)));
		fontMenu.loadGlyphs();
	}
	
	public UnicodeFont getFontMenu() {
		return fontMenu;
	}
}
