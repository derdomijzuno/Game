package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

import game.controller.Controller;
import game.core.Position;
import game.core.Size;
import game.main.Game;
import game.main.GameObject;
import game.main.Handler;
import game.map.Pathfinder;
import game.states.GameState;

public class Player extends MovingEntity {

	Handler handler;
	List<Position> path;

	public Player(Position pos, Size size, ID id, Controller controller, Handler handler) {
		super(pos, size, id, controller);
		this.handler = handler;
	}

	private void pathfinding(Position target) {
		Position gridPos = pos.ofGridPosition(pos.intX() / Game.tileSize, pos.intY() / Game.tileSize);
		path = Pathfinder.findPath(pos, target, GameState.map);
	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());

		if (path != null) {
			for (int i = 0; i < path.size(); i++) {
				g.setColor(Color.CYAN);
				g.fillRect(path.get(i).gridX() * Game.tileSize, path.get(i).gridY() * Game.tileSize, Game.tileSize,
						Game.tileSize);
			}
		}
	}

	@Override
	public void tick() {
		super.tick();
		checkBounds();

		if (handler.isKeyPressed(KeyEvent.VK_B)) {
			for (int i = 0; i < handler.getObjects().size(); i++) {
				GameObject temp = handler.getObjects().get(i);
				if (temp.getId() == ID.Enemy) {
					if (path == null)
						pathfinding(new Position(temp.getPos().gridX() * Game.tileSize,
								temp.getPos().gridY() * Game.tileSize));
				}
			}
		}

	}

	private void checkBounds() {

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

}
