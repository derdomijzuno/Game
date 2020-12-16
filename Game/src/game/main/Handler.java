package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import game.entity.GameObject;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private boolean[] keys = new boolean[255];
	private boolean debug = true, showTiles = true;

	private int mx, my;

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.render(g);
		}
	}

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.tick();
		}
	}

	public void addObject(GameObject temp) {
		objects.add(temp);
	}

	public void removeObject(GameObject temp) {
		objects.remove(temp);
	}

	// Getters and Setters

	public LinkedList<GameObject> getObjects() {
		return objects;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getMx() {
		return mx;
	}

	public int getMy() {
		return my;
	}

	public void setMx(int mx) {
		this.mx = mx;
	}

	public void setMy(int my) {
		this.my = my;
	}

	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode];
	}
	
	public void setKey(boolean pressed, int keyCode) {
		keys[keyCode] = pressed;
	}

	public boolean isShowTiles() {
		return showTiles;
	}

	public void setShowTiles(boolean showTiles) {
		this.showTiles = showTiles;
	}

}
