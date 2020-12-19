package games.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import games.core.CollisionBox;
import games.core.Position;
import games.core.Size;

public class Obstacle extends GameObject {

	public Obstacle(Position pos, Size size, ID id) {
		super(pos, size, id);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());
	}

	@Override
	public void tick() {
	}

	@Override
	public CollisionBox getHitBox() {
		return new CollisionBox(new Rectangle(pos.intX(), pos.intY(), size.getWidth(), size.getHeight()));
	}

	@Override
	public BufferedImage getSprite() {
		return null;
	}

}
