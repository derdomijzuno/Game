package game.main;

import java.awt.Graphics;

import game.objects.ID;

public abstract class GameObject {

	protected int x,y;
	protected ID id;
	
	public GameObject(int x, int y, ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	protected abstract void render(Graphics g);
	protected abstract void tick();

	// Getters and Setters
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	
}
