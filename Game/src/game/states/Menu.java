package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import game.main.Game;
import game.main.Handler;
import game.ui.Button;
import game.ui.ButtonCollision;

public class Menu extends State {

	Button btn1;
	ButtonCollision bc = new ButtonCollision();
	public Menu(Handler handler) {
		super(handler);

		btn1 = new Button(400, 300, 400, 200);
	}

	@Override
	public void tick() {
		if (Game.gs == StateID.Menu) {
			if (bc.inside(handler.getMx(), handler.getMy(), btn1)) {
				btn1.setHover(true);
			} else {
				btn1.setHover(false);
			}
		}

		if (Game.gs == StateID.Menu) {
			if (bc.inside(handler.getMx(), handler.getMy(), btn1) && handler.isMousePressed(MouseEvent.BUTTON1)) {
				btn1.setActive(true);
			} else {
				btn1.setActive(false);
			}
		}
		
		if (btn1.isActive()) {
			Game.gs = StateID.Game;
			btn1.setActive(false);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WindowWidth, Game.WindowHeight);

		btn1.render(g);
	}

}
