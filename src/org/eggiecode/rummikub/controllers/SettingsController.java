package org.eggiecode.rummikub.controllers;

public class SettingsController {
	private Config config;

	public void loadConfig() {
		config = new Config();
	}

	public Config getConfig() {
		return config;
	}

	public class Config {
		
		private float soundSFX = 0.5f;
		private float soundMusic = 0.3f;

		public float getSoundSFX() {
			return soundSFX;
		}

		public void setSoundSFX(float soundSFX) {
			this.soundSFX = soundSFX;
		}

		public float getSoundMusic() {
			return soundMusic;
		}

		public void setSoundMusic(float soundMusic) {
			this.soundMusic = soundMusic;
		}

	}
}
