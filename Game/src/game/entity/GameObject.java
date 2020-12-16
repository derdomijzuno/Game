package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.core.CollisionBox;
import game.core.Position;
import game.core.Size;

public abstract class GameObject {

	protected Size size;
	protected Position pos;
	protected ID id;
	protected CollisionBox hitBox;

	public GameObject(Position pos, Size size, ID id) {
		this.size = size;
		this.pos = pos;
		this.id = id;
	}

	public abstract CollisionBox getHitBox();

	public abstract void render(Graphics g);

	public abstract void tick();
	
	public abstract BufferedImage getSprite();

	// Getters and Setters

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

}
