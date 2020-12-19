package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.controller.Controller;
import game.core.CollisionBox;
import game.core.Position;
import game.core.Size;
import game.gfx.AnimationManager;
import game.gfx.SpriteLibrary;
import game.gfx.particles.TestParticle;
import game.main.Game;
import game.main.Handler;
import game.states.GameState;

public class Player extends MovingEntity {

	Handler handler;
	String debug;

	public Player(Position pos, Size size, ID id, Controller controller, Handler handler, SpriteLibrary spriteLibrary) {
		super(pos, size, id, controller, spriteLibrary);
		this.handler = handler;
		debug = " .";

		this.animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
		setSpeed(5);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage(getSprite(), pos.intX(), pos.intY(), size.getWidth(), size.getHeight(), null);

		if (handler.isDebug()) {
			g.setColor(Color.GREEN);
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(getHitBox().getBounds());

			g.setColor(Color.BLUE);
			g2d.draw(getHitBox().getBoundsTop());
			g.setColor(Color.CYAN);
			g2d.draw(getHitBox().getBoundsBottom());
			g.setColor(Color.RED);
			g2d.draw(getHitBox().getBoundsLeft());
			g.setColor(Color.YELLOW);
			g2d.draw(getHitBox().getBoundsRight());
		}
	}

	@Override
	public void tick() {
		super.tick();
		handleCollisions();

		if (handler.isKeyPressed(KeyEvent.VK_P)) {
			Handler.particles.add(new TestParticle(pos.intX(), pos.intY(), 20, 20));
		}

	}

	@Override
	protected void handleCollisions() {
		checkWorldBounds();
		WallCollisions();

	}

	private void WallCollisions() {
		for (GameObject temp : handler.getObjects()) {
			if (temp instanceof Obstacle) {
				if (getHitBox().getBoundsTop().intersects(temp.getHitBox().getBounds())) {
					pos.setY(temp.getPos().getY() + temp.getSize().getHeight());
				}
				if (getHitBox().getBoundsBottom().intersects(temp.getHitBox().getBounds())) {
					pos.setY(temp.getPos().getY() - getSize().getHeight());
				}
				if (getHitBox().getBoundsLeft().intersects(temp.getHitBox().getBounds())) {
					pos.setX(temp.getPos().getX() + temp.getSize().getWidth());
				}
				if (getHitBox().getBoundsRight().intersects(temp.getHitBox().getBounds())) {
					pos.setX(temp.getPos().getX() - temp.getSize().getWidth());
				}
			}
		}

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
