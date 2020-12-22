package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import game.ai.AIManager;
import game.controller.EnemyController;
import game.core.CollisionBox;
import game.core.Position;
import game.core.Size;
import game.gfx.AnimationManager;
import game.gfx.SpriteLibrary;
import game.gfx.particles.TestParticle;
import game.main.Game;
import game.main.Handler;
import game.map.Pathfinder;
import game.states.GameState;

public class Enemy extends MovingEntity {

	List<Position> path;
	Handler handler;
	Position target;

	AIManager aiManager;

	String debug;

	public Enemy(Position pos, Size size, ID id, Handler handler, SpriteLibrary spriteLibrary) {
		super(pos, size, id, new EnemyController(handler), spriteLibrary);
		this.handler = handler;
		setSpeed(10);

		aiManager = new AIManager();

		this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
		debug = ".";
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(getSprite(), pos.intX(), pos.intY(), size.getWidth(), size.getHeight(), null);

		if (handler.isDebug()) {
			if (path != null && path.size() > 0) {
				g.setColor(Color.CYAN);
				for (int i = 0; i < path.size(); i++) {
					g.fillRect(path.get(i).intX() - Game.tileSize / 4, path.get(i).intY() - Game.tileSize / 4,
							Game.tileSize / 2, Game.tileSize / 2);
				}
			}

			g.setColor(Color.GREEN);
			debug = "X: " + pos.getX() + " | Y: " + pos.getY();
			g.drawString(debug, pos.intX(), pos.intY());

			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(getHitBox().getBounds());
		}

	}

	@Override
	public void tick() {

		aiManager.tick(this);

		super.tick();

		WallCollisions();

		if (handler.isKeyPressed(KeyEvent.VK_P)) {
			Handler.particles.add(new TestParticle(pos.intX(), pos.intY(), 20, 20));
		}

		if (handler.isMousePressed(MouseEvent.BUTTON3)) {
			setTarget(new Position(handler.getMx(), handler.getMy()));
		}

		if (target != null && aiManager.getTarget() == null) {
			aiManager.setTarget(target);
		}

	}

	private void WallCollisions() {
		for (GameObject temp : handler.getObjects()) {
			if (temp instanceof Player) {
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

	public void setTarget(Position target) {
		this.target = target;
	}

	@Override
	public CollisionBox getHitBox() {
		return new CollisionBox(new Rectangle(pos.intX(), pos.intY(), size.getWidth(), size.getHeight()));
	}

}
