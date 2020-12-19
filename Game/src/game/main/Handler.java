package game.main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import game.entity.GameObject;
import game.gfx.particles.Particle;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public static LinkedList<Particle> particles = new LinkedList<Particle>();
	private boolean[] keys = new boolean[255];
	private boolean[] mousePressed = new boolean[14];
	private boolean debug = true, showTiles = true;

	private int mx, my;

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.render(g);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(g);
		}
	}

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.tick();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
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
	
	public boolean isMousePressed(int button) {
		return mousePressed[button];
	}
	
	public void setMousePressed(boolean pressed, int button) {
		mousePressed[button] = pressed;
	}
	
	public boolean isShowTiles() {
		return showTiles;
	}

	public void setShowTiles(boolean showTiles) {
		this.showTiles = showTiles;
	}

}
