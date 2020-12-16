package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import game.core.CollisionBox;
import game.core.Position;
import game.core.Size;
import game.gfx.AnimationManager;
import game.gfx.SpriteLibrary;
import game.main.Game;
import game.main.Handler;
import game.map.Pathfinder;
import game.states.GameState;

public class Enemy extends MovingEntity {

	List<Position> path;
	Handler handler;

	double velX;
	double velY;

	String debug;

	private boolean targetReached = true;

	public Enemy(Position pos, Size size, ID id, Handler handler, SpriteLibrary spriteLibrary) {
		super(pos, size, id, null, spriteLibrary);
		this.handler = handler;
		setSpeed(2);

		debug = ".";
	}

	private void setPath(Position target) {
		path = Pathfinder.findPath(target, getPos(), GameState.map);
		if (path.size() >= 1)
			targetReached = false;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());

		if (handler.isDebug()) {
			if (path != null && path.size() > 0) {
				g.setColor(Color.CYAN);
				for (int i = 0; i < path.size(); i++) {
					g.fillRect(path.get(i).intX() - Game.tileSize / 4, path.get(i).intY() - Game.tileSize / 4,
							Game.tileSize / 2, Game.tileSize / 2);
				}
			}

			g.setColor(Color.GREEN);
			Position gridPos = Position.ofGridPosition(pos.gridX(), pos.gridY());
			debug = "X: " + (gridPos.getX() / Game.tileSize - 0.5) + " | Y: " + (gridPos.getY() / Game.tileSize - 0.5);
			g.drawString(debug, pos.intX(), pos.intY());

		}

		if (handler.isDebug()) {
			g.setColor(Color.GREEN);
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(getHitBox().getBounds());
		}

	}

	private void followPath(List<Position> path) {

		if (!targetReached) {

			if (path.size() <= 1) {
				targetReached = true;
				path.clear();
				return;
			}

			Position nextPos = path.get(path.size() - 1);

			if (nextPos.getX() - Game.tileSize / 2 < pos.getX()) {
				velX = -speed;
			}
			if (nextPos.getX() - Game.tileSize / 2 > pos.getX()) {
				velX = speed;
			}
			if (nextPos.getY() - Game.tileSize / 2 < pos.getY()) {
				velY = -speed;
			}
			if (nextPos.getY() - Game.tileSize / 2 > pos.getY()) {
				velY = speed;
			}

			if (nextPos.gridX() == pos.gridX() && nextPos.gridY() == pos.gridY()) {
				path.remove(nextPos);
			}
		}
	}

	@Override
	public void tick() {

		velX = 0;
		velY = 0;

		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject temp = handler.getObjects().get(i);

			if (temp.getId() == ID.Player) {
				if (handler.isKeyPressed(KeyEvent.VK_B)) {
					setPath(temp.getPos());
					continue;
				}
			}
		}

		followPath(path);
		move();

		handleCollisions();

		pos.setX(pos.getX() + velX);
		pos.setY(pos.getY() + velY);

	}

	private void move() {

		if (handler.isKeyPressed(KeyEvent.VK_DOWN)) {
			velY = 5;
		}
		if (handler.isKeyPressed(KeyEvent.VK_UP)) {
			velY = -5;
		}
		if (handler.isKeyPressed(KeyEvent.VK_RIGHT)) {
			velX = 5;
		}
		if (handler.isKeyPressed(KeyEvent.VK_LEFT)) {
			velX = -5;
		}
	}

	@Override
	protected void handleCollisions() {
		checkWorldBounds();

		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject temp = handler.getObjects().get(i);

			if (temp.getId() == ID.Obstacle) {
				if (getHitBox().collidesWith(temp.getHitBox())) {
//					velX *= -1;
//					velY *= -1;
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
