package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

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
	
	public LinkedList<GameObject> getObjects(){
		return objects;
	}
}
