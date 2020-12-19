package game.gfx.particles;

import java.awt.Graphics;
import java.util.Random;

public abstract class Particle {

	protected int x, y;
	protected int velX, velY;
	protected int width, height;

	protected float alpha;

	protected Random random;

	public Particle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		random = new Random();

		int zahl1 = random.nextInt(2);

		if (zahl1 == 1) {
			int zahl2 = random.nextInt(4);
			this.velX = zahl2;
		} else if (zahl1 == 0) {
			int zahl2 = random.nextInt(4);
			this.velX = -zahl2;
		}

		int zahl3 = random.nextInt(4);
		this.velY = -zahl3;

	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
