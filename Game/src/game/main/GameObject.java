package game.main;

import java.awt.Graphics;

import game.core.Position;
import game.core.Size;
import game.entity.ID;

public abstract class GameObject {

	protected Size size;
	protected Position pos;
	protected ID id;

	public GameObject(Position pos, Size size,  ID id) {
		this.size = size;
		this.pos = pos;
		this.id = id;
	}

	protected abstract void render(Graphics g);

	protected abstract void tick();

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
