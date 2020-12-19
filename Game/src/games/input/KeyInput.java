package games.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import games.entity.Enemy;
import games.entity.GameObject;
import games.entity.ID;
import games.main.Game;
import games.main.Handler;
import games.states.StateID;

public class KeyInput extends KeyAdapter {

	Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		handler.setKey(true, e.getKeyCode());

//		if (key == KeyEvent.VK_ESCAPE) {
//			System.exit(0);
//		}

		if (key == KeyEvent.VK_E) {
			if (handler.isDebug())
				handler.setDebug(false);
			else if (!handler.isDebug())
				handler.setDebug(true);
		}

		if (key == KeyEvent.VK_G) {
			Game.gs = StateID.Game;
		}

		if (key == KeyEvent.VK_T) {
			if (handler.isShowTiles())
				handler.setShowTiles(false);
			else if (!handler.isShowTiles())
				handler.setShowTiles(true);
		}

		if (handler.isKeyPressed(KeyEvent.VK_ESCAPE))
			System.exit(0);

		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject temp = handler.getObjects().get(i);
			
			
		}
	}

	public void keyReleased(KeyEvent e) {
		handler.setKey(false, e.getKeyCode());
	}

}
