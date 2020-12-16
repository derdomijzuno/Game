package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import game.controller.Controller;
import game.core.CollisionBox;
import game.core.Position;
import game.core.Size;
import game.gfx.BufferedImageLoader;
import game.gfx.SpriteLibrary;
import game.main.Game;
import game.main.Handler;
import game.map.Pathfinder;
import game.states.GameState;

public class Player extends MovingEntity {

	Handler handler;
	String debug;

	public Player(Position pos, Size size, ID id, Controller controller, Handler handler, SpriteLibrary spriteLibrary) {
		super(pos, size, id, controller, spriteLibrary);
		this.handler = handler;
		debug = " .";
		setSpeed(5);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage(getSprite(), pos.intX(), pos.intY(), size.getWidth(), size.getHeight(), null);
//		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());

		if (handler.isDebug()) {
			g.setColor(Color.GREEN);
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(getHitBox().getBounds());
		}
	}

	@Override
	public void tick() {
		super.tick();
		handleCollisions();

	}

	@Override
	protected void handleCollisions() {
		checkWorldBounds();
	}

	private void checkWorldBounds() {

		if (pos.getX() + size.getWidth() > GameState.map.getTiles().length * Game.tileSize) {
			pos.setX(GameState.map.getTiles().length * Game.tileSize - size.getWidth());
		}
		if (pos.getX() < 0) {
			pos.setX(0);
		}
		if (pos.getY() + size.getHeight() > GameState.map.getTiles()[0].length * Game.tileSize) {
			pos.setY(GameState.map.getTiles()[0].length * Game.tileSize - size.getHeight());
		}
		if (pos.getY() < 0) {
			pos.setY(0);
		}

	}

	@Override
	public CollisionBox getHitBox() {
		return new CollisionBox(new Rectangle(pos.intX(), pos.intY(), size.getWidth(), size.getHeight()));
	}

}
