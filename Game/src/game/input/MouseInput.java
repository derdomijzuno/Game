package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.ai.A_Visualization;
import game.main.Game;
import game.main.Handler;
import game.states.GameState;
import game.states.Menu;
import game.ui.ButtonCollision;

public class MouseInput implements MouseListener, MouseMotionListener {

	private Handler handler;
	private boolean active = false;
	int mx;
	int my;

	ButtonCollision bc = new ButtonCollision();

	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		mx = (int) e.getX();
		my = (int) e.getY();

		System.out.println("MX: " + mx + " | MY: " + my);
		if (Game.gs == GameState.aStar)
			if (!active) {
				A_Visualization.a_.start();
				active = true;
			}

		if (Game.gs == GameState.Menu) {
			if (bc.inside(e.getX(), e.getY(), Menu.btn1)) {
				Menu.btn1.setActive(true);
			} else {
				Menu.btn1.setActive(false);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = (int) e.getX();
		my = (int) e.getY();

		handler.setMx(mx);
		handler.setMy(my);

		if (Game.gs == GameState.Menu) {
			if (bc.inside(e.getX(), e.getY(), Menu.btn1)) {
				Menu.btn1.setHover(true);
			} else {
				Menu.btn1.setHover(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
