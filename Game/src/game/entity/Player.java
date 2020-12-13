package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

import game.controller.Controller;
import game.core.Position;
import game.core.Size;
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
		path = Pathfinder.findPath(this.pos, target, GameState.map);
	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());

		if (path != null) {
			for (int i = 0; i < path.size(); i++) {
				g.setColor(Color.CYAN);
				g.fillRect(path.get(i).gridX() * 50,path.get(i).gridY() * 50, 50, 50);
			}
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (handler.isKeyPressed(KeyEvent.VK_B)) {
			pathfinding(new Position(100, 100));
		}
	}

}
