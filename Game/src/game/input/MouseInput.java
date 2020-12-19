package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.main.Camera;
import game.main.Game;
import game.main.Handler;
import game.states.Menu;
import game.states.StateID;
import game.ui.ButtonCollision;

public class MouseInput implements MouseListener, MouseMotionListener {

	private Handler handler;
	private Camera cam;
	int mx;
	int my;

	

	public MouseInput(Handler handler, Camera cam) {
		this.handler = handler;
		this.cam = cam;
	}

	public void mousePressed(MouseEvent e) {
		if (Game.gs == StateID.Game) {
			mx = (int) e.getX() + (int)cam.getX();
			my = (int) e.getY() + (int)cam.getY();
		} else {
			mx = (int) e.getX();
			my = (int) e.getY();
		}

		System.out.println("MX: " + mx + " | MY: " + my);

		
		
		handler.setMousePressed(true, e.getButton());

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Game.gs == StateID.Game) {
			mx = (int) e.getX() + (int)cam.getX();
			my = (int) e.getY() + (int)cam.getY();
		} else {
			mx = (int) e.getX();
			my = (int) e.getY();
		}

		handler.setMx(mx);
		handler.setMy(my);

		
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
		handler.setMousePressed(false, e.getButton());
	}

}
