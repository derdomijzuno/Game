package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.core.Position;
import game.core.Size;
import game.main.GameObject;

public class Obstacle extends GameObject {

	public Obstacle(Position pos, Size size, ID id) {
		super(pos, size, id);
	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());
	}

	@Override
	protected void tick() {
	}

}
