package game.objects;

import java.awt.Color;
import java.awt.Graphics;

import game.main.GameObject;

public class Player extends GameObject{

	
	public Player(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x,y,50,50);
	}

	@Override
	protected void tick() {
		x+=5;
	}

}
