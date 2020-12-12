package game.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.ai.A_Visualization;
import game.main.Handler;

public class MouseInput extends MouseAdapter {

	private Handler handler;

	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX());
		int my = (int) (e.getY());

		System.out.println("MX: " + mx + " | MY: " + my);
		if (handler.isDebug())
			A_Visualization.a_.start();
	}

}
