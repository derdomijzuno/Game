package game.ui;

import java.awt.Color;
import java.awt.Graphics;

public class Button {

	public int x, y, width, height;
	public boolean hover, active;

	public Button(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.hover = false;
		this.active = false;
	}

	public void render(Graphics g) {

		if (hover) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(x, y, width, height);

	}

	// Getters and Setters
	public void setActive(Button btn) {
		btn.setActive(true);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
