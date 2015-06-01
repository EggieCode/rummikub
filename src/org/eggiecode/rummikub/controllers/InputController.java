package org.eggiecode.rummikub.controllers;


import org.eggiecode.rummikub.client.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class InputController {

	private Game game;
	private InputData inputDataKeyDown;
	private InputData inputDataKeyPressed;

	public InputController(Game game) {
		this.game = game;
		inputDataKeyDown = new InputData();
		inputDataKeyPressed = new InputData();
	}

	public void preUpdateState(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
		inputDataKeyDown.moveDown = input.isKeyDown(Input.KEY_DOWN)
				|| input.isKeyDown(Input.KEY_S);
		inputDataKeyDown.moveUp = input.isKeyDown(Input.KEY_UP)
				|| input.isKeyDown(Input.KEY_W);
		inputDataKeyDown.moveRight = input.isKeyDown(Input.KEY_RIGHT)
				|| input.isKeyDown(Input.KEY_D);
		inputDataKeyDown.moveLeft = input.isKeyDown(Input.KEY_LEFT)
				|| input.isKeyDown(Input.KEY_A);
		inputDataKeyDown.fire = input.isKeyDown(Input.KEY_SPACE)
				|| input.isKeyDown(Input.KEY_ENTER);
		inputDataKeyDown.switchProjectile = input.isKeyDown(Input.KEY_CAPITAL);
		inputDataKeyDown.paused = input.isKeyDown(Input.KEY_ESCAPE);
		inputDataKeyDown.switchProjectile = input.isKeyDown(Input.KEY_CAPITAL);
		
		

		inputDataKeyPressed.moveDown = input.isKeyPressed(Input.KEY_DOWN)
				|| input.isKeyDown(Input.KEY_S);
		inputDataKeyPressed.moveUp = input.isKeyPressed(Input.KEY_UP)
				|| input.isKeyDown(Input.KEY_W);
		inputDataKeyPressed.moveRight = input.isKeyPressed(Input.KEY_RIGHT)
				|| input.isKeyDown(Input.KEY_D);
		inputDataKeyPressed.moveLeft = input.isKeyPressed(Input.KEY_LEFT)
				|| input.isKeyDown(Input.KEY_A);
		inputDataKeyPressed.fire = input.isKeyPressed(Input.KEY_SPACE)
				|| input.isKeyPressed(Input.KEY_ENTER);
		inputDataKeyPressed.switchProjectile = input.isKeyDown(Input.KEY_CAPITAL);
		inputDataKeyPressed.paused = input.isKeyPressed(Input.KEY_ESCAPE);
		
		if(inputDataKeyDown.moveDown && inputDataKeyDown.moveRight)
			inputDataKeyDown.angle = 45;
		else if(inputDataKeyDown.moveRight && inputDataKeyDown.moveUp)
			inputDataKeyDown.angle = 135;
		else if(inputDataKeyDown.moveUp && inputDataKeyDown.moveLeft)
			inputDataKeyDown.angle = 225;
		else if(inputDataKeyDown.moveLeft && inputDataKeyDown.moveDown)
			inputDataKeyDown.angle = 315;
		else if(inputDataKeyDown.moveDown)
			inputDataKeyDown.angle = 0;
		else if(inputDataKeyDown.moveRight)
			inputDataKeyDown.angle = 90;
		else if(inputDataKeyDown.moveUp)
			inputDataKeyDown.angle = 180;
		else if(inputDataKeyDown.moveLeft)
			inputDataKeyDown.angle = 270;
	}

	public InputData getInputDataDown() {
		return inputDataKeyDown;
	}

	public InputData getInputDataPressed() {
		return inputDataKeyPressed;
	}

	public class InputData {

		private boolean switchProjectile;
		private boolean moveUp = false;
		private boolean moveDown = false;
		private boolean moveRight = false;
		private boolean moveLeft = false;
		private boolean fire = false;
		private boolean paused = false;
		private float angle = 0;
		
		
		public boolean isMoveUp() {
			return moveUp;
		}

		public boolean isMoveDown() {
			return moveDown;
		}

		public boolean isMoveRight() {
			return moveRight;
		}

		public boolean isMoveLeft() {
			return moveLeft;
		}

		public boolean isFire() {
			return fire;
		}
		public boolean isSwitchProjectile() {
			return switchProjectile;
		}

		public boolean isPaused() {
			return paused;
		}

		public float getAngle() {
			return angle;
		}
	}
}
