package game.states;

import java.awt.Color;
import java.awt.Graphics;

import game.main.Game;
import game.main.Handler;
import game.ui.Button;

public class Menu extends State {

	public static Button btn1;

	public Menu(Handler handler) {
		super(handler);

		btn1 = new Button(400, 300, 400, 200);
	}

	@Override
	public void tick() {
		if (btn1.isActive()) {
			Game.gs = GameState.Game;
			btn1.setActive(false);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1280, 720);

		btn1.render(g);
	}

}
