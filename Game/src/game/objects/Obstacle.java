package game.objects;

import java.awt.Color;
import java.awt.Graphics;

import game.main.GameObject;

public class Obstacle extends GameObject {

	public Obstacle(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x * 20, y * 20, 20, 20);
	}

	@Override
	protected void tick() {

	}

}
