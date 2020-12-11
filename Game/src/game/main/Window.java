package game.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	
	public Window(Game game, int width, int height) {
		
		JFrame f = new JFrame();
		
		Dimension d = new Dimension(width, height);
		
		f.setMaximumSize(d);
		f.setPreferredSize(d);
		f.setMinimumSize(d);
		
		f.setResizable(false);
//		f.setUndecorated(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(3);
		
		f.add(game);
		
		f.setVisible(true);
		
	}
	
}
