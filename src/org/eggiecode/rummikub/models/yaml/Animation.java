package org.eggiecode.rummikub.models.yaml;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Animation {
	private String name;
	private String sheet;
	private SpriteSheet spriteSheet;
	private ArrayList<AnimationImage> images;

	private org.newdawn.slick.Animation animation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public ArrayList<AnimationImage> getImages() {
		return images;
	}

	public void setImages(ArrayList<AnimationImage> images) {
		this.images = images;
	}

	public org.newdawn.slick.Animation getAnimation() {
		return animation;

	}

	public void setAnimation(org.newdawn.slick.Animation animation) {
		this.animation = animation;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public void generateAnimation() {
		// TODO Auto-generated method stub
		ArrayList<Image> slickImages = new ArrayList<Image>();
		int[] durations = new int[images.size()];
		for(int i = 0; i < images.size(); i++) {
			AnimationImage img = images.get(i);
			Image sImg = spriteSheet.getSlickSpriteSheet().getSubImage(
					img.getImageX(),
				
					img.getImageY()
			);
			slickImages.add(sImg);
			durations[i] = img.getSpeed();
		}
		
		this.animation = new org.newdawn.slick.Animation(slickImages.toArray(new Image[0]), durations);
		
	}
}