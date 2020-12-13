package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import game.core.Position;
import game.core.Size;
import game.main.Game;
import game.main.GameObject;
import game.map.Tile;

public class Enemy extends GameObject {

	LinkedList<Tile> path;

	private boolean up, down, left, right;

	private int velX, velY;
	int moves = 0;

	public static boolean do_path = false;

	public Enemy(Position pos, Size size, ID id) {
		super(pos, size, id);
	}

	private void pathfinding() {

		Tile current = Game.ts.getTile(pos.intX(), pos.intY());
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
		} else {
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
		g.fillRect(pos.intX(), pos.intY(), size.getWidth(), size.getHeight());
	}

	@Override
	protected void tick() {

		move();

		pos.setX(pos.getX() + velX);
		pos.setY(pos.getY() + velY);
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
