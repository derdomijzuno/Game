package game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;
import game.states.GameState;

public class KeyInput extends KeyAdapter {

	Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		if (key == KeyEvent.VK_E) {
			if (handler.isDebug())
				handler.setDebug(false);
			else if (!handler.isDebug())
				handler.setDebug(true);
		}

		if (key == KeyEvent.VK_G) {
			Game.gs = GameState.Game;
		}

		if (key == KeyEvent.VK_A) {
			if (Game.gs != GameState.aStar)
				Game.gs = GameState.aStar;
			else {
				Game.gs = GameState.Game;
			}
		}

		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject temp = handler.getObjects().get(i);

		}
	}

	public void keyReleased(KeyEvent e) {

	}

}
