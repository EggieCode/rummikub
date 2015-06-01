package org.eggiecode.rummikub.models.yaml;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteSheet {
	private String name;
	private String image;
	private Image slickImage;
	private org.newdawn.slick.SpriteSheet slickSpriteSheet;
	private String animations;
	private int height;
	private int width;
	private int spaceing;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAnimations() {
		return animations;
	}

	public void setAnimations(String animations) {
		this.animations = animations;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getSpaceing() {
		return spaceing;
	}

	public void setSpaceing(int spaceing) {
		this.spaceing = spaceing;
	}

	public void loadImage() throws SlickException {
		slickImage = new Image("assets/" + image);
		slickSpriteSheet = new org.newdawn.slick.SpriteSheet(slickImage, height, width,spaceing);
	}

	public Image getSlickImage() {
		return slickImage;
	}

	public org.newdawn.slick.SpriteSheet getSlickSpriteSheet() {
		return slickSpriteSheet;
	}
	
	
}
