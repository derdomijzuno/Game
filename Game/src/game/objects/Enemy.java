package game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import game.ai.A_;
import game.main.Game;
import game.main.GameObject;
import game.map.Tile;

public class Enemy extends GameObject {

	private A_ pathfinder;
	LinkedList<Tile> path;

	private boolean up, down, left, right;

	private int velX, velY;
	int moves = 0;

	public static boolean do_path = false;

	public Enemy(int x, int y, ID id) {
		super(x, y, id);
		pathfinder = new A_();

	}

	private void pathfinding() {
		if (path == null) {
			for (int xx = 0; xx < Game.ts.getW(); xx++) {
				for (int yy = 0; yy < Game.ts.getH(); yy++) {
					if (Game.ts.getTile(xx, yy).getId() == ID.Player) {
						path = pathfinder.getPath(Game.ts, Game.ts.getTile(x, y), Game.ts.getTile(xx, yy));
						moves = path.size() - 1;
					}
				}
			}
		}

		Tile current = Game.ts.getTile(x, y);
		Tile next = path.get(moves);

		if (current.getX() < next.getX()) {
			left = false;
			right = true;
		} else if (current.getX() > next.getX()) {
			right = false;
			left = true;
		} else {
			right = false;
			left = false;
		}
		if (current.getY() < next.getY()) {
			up = false;
			down = true;
		} else if (current.getY() > next.getY()) {
			down = false;
			up = true;
		}else {
			down = false;
			up = false;
		}

		if (moves > 0) {
			moves--;
		} else {
			path.clear();
			up = false;
			down = false;
			right = false;
			left = false;
		}

	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x * 20, y * 20, 20, 20);
	}

	@Override
	protected void tick() {

		if (do_path) {
			pathfinding();
		}

		move();

		x += velX;
		y += velY;
	}

	private void move() {
		velX = 0;
		velY = 0;
		if (up) {
			velY = -1;
		} else if (!down) {
			velY = 0;
		}
		if (left) {
			velX = -1;
		} else if (!right) {
			velX = 0;
		}
		if (right) {
			velY = 1;
		} else if (!left) {
			velY = 0;
		}
		if (down) {
			velY = 1;
		} else if (!up) {
			velY = 0;
		}
	}

}
