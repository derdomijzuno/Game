package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private boolean debug = false;
	
	private int mx,my;

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
	
	public LinkedList<GameObject> getObjects(){
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
	
	
}
