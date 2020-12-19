package games.entity;

import java.awt.image.BufferedImage;

import games.controller.Controller;
import games.core.Direction;
import games.core.Motion;
import games.core.Position;
import games.core.Size;
import games.gfx.AnimationManager;
import games.gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {

	protected Controller controller;
	protected Motion motion;
	protected AnimationManager animationManager;
	protected Direction direction;

	protected double speed;

	public MovingEntity(Position pos, Size size, ID id, Controller controller, SpriteLibrary spriteLibrary) {
		super(pos, size, id);
		this.controller = controller;
		this.motion = new Motion(speed);
		this.direction = Direction.S;
	}

	protected abstract void handleCollisions();

	@Override
	public void tick() {
		motion.update(controller, speed);
		pos.apply(motion);
		manageDirection();
		decideAnimation();
		animationManager.update(direction);
	}
	
	private void decideAnimation() {
		if(motion.isMoving()) {
			animationManager.playAnimation("walk");
		}else {
			animationManager.playAnimation("stand");
		}
	}

	private void manageDirection() {
		if (motion.isMoving()) {
			this.direction = Direction.fromMotion(motion);
		}
	}

	public double getSpeed() {
		return speed;
	}

	@Override
	public BufferedImage getSprite() {
		return animationManager.getSprite();
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Controller getController() {
		return controller;
	}
	
	

}
