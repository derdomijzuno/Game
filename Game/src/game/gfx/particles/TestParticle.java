package game.gfx.particles;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.main.Handler;

public class TestParticle extends Particle {

	public TestParticle(int x, int y, int width, int height) {
		super(x, y, width, height);
		alpha = 1;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		alpha -= 0.02f;
		if(alpha <= 0) {
			Handler.particles.remove(this);
		}
	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); 
	}

}
