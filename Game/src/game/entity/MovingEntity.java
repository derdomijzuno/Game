package game.entity;

import java.awt.image.BufferedImage;

import game.controller.Controller;
import game.core.Direction;
import game.core.Motion;
import game.core.Position;
import game.core.Size;
import game.gfx.AnimationManager;
import game.gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {

	private Controller controller;
	private Motion motion;
	private AnimationManager animationManager;
	private Direction direction;

	protected double speed;

	public MovingEntity(Position pos, Size size, ID id, Controller controller, SpriteLibrary spriteLibrary) {
		super(pos, size, id);
		this.controller = controller;
		this.motion = new Motion(speed);
		this.direction = Direction.S;
		animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
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

}
