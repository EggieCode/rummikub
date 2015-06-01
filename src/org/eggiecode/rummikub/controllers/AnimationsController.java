package org.eggiecode.rummikub.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eggiecode.rummikub.models.yaml.Animation;
import org.eggiecode.rummikub.models.yaml.AnimationImage;
import org.eggiecode.rummikub.models.yaml.SpriteSheet;
import org.newdawn.slick.SlickException;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

public class AnimationsController {

	Map<String, org.eggiecode.rummikub.models.yaml.Animation> animations = new HashMap<String, org.eggiecode.rummikub.models.yaml.Animation>();
	Map<String, SpriteSheet> spriteSheets = new HashMap<String, SpriteSheet>();

	public AnimationsController() {

	}

	public void loadData() throws FileNotFoundException, YamlException,
			SlickException {

		YamlReader reader = new YamlReader(new FileReader(
				"assets/spritesheet.yaml"));
		reader.getConfig().setClassTag("SpriteSheet", SpriteSheet.class);
		Map a = (Map) reader.read();
		if(a == null || a.containsKey("Data"))
			return;
		ArrayList<SpriteSheet> sheets = (ArrayList<SpriteSheet>) a.get("Data");

		for (SpriteSheet sheet : sheets) {
			if (!spriteSheets.containsKey(sheet.getName()))
				spriteSheets.put(sheet.getName(), sheet);
			else
				throw new SlickException("SpriteSheet `" + sheet.getName()
						+ "` does allready exist in the list");
			sheet.loadImage();
		}

		for (Entry<String, SpriteSheet> i : spriteSheets.entrySet()) {
			YamlReader animationsReader = new YamlReader(new FileReader(
					"assets/" + i.getValue().getAnimations().trim()));
			animationsReader.getConfig().setClassTag("Animation",
					Animation.class);
			animationsReader.getConfig().setClassTag("Image",
					AnimationImage.class);

			Map mapAnimation = (Map) animationsReader.read();
			ArrayList<Animation> data = (ArrayList<Animation>) mapAnimation
					.get("Data");

			for (Animation ani : data) {
				if (!this.animations.containsKey(ani.getName()))
					this.animations.put(ani.getName(), ani);
				else
					throw new SlickException("Animation `" + ani.getName() 
							+ "` does allready exist in the list");
				ani.setSpriteSheet(i.getValue());
				ani.generateAnimation();
			}
		}

	}

	public org.newdawn.slick.Animation getAnimation(String name)
			throws SlickException {
		if (animations.containsKey(name))
			return animations.get(name).getAnimation();
		throw new SlickException("Animation `" + name + "` does not exist.");

	}
}