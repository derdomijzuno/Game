package game.entity;

import game.controller.Controller;
import game.core.Motion;
import game.core.Position;
import game.core.Size;
import game.main.GameObject;

public abstract class MovingEntity extends GameObject{
	
	private Controller controller;
	private Motion movement;
	
	
	public MovingEntity(Position pos, Size size, ID id, Controller controller) {
		super(pos, size, id);
		this.controller=controller;
		this.movement = new Motion(2);
	}

	@Override
	public void tick() {
		movement.update(controller);
		pos.apply(movement);
	}
	

}
