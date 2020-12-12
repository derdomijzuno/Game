package game.ai;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class A_ {

	BufferedImage map;
	Field[][] field;

	private boolean path_found = false;

	Field start, end;
	Field path_start;

	LinkedList<Field> openList = new LinkedList<Field>();
	LinkedList<Field> closedList = new LinkedList<Field>();
	LinkedList<Field> path = new LinkedList<Field>();

	public A_(BufferedImage map) {
		this.map = map;
		field = new Field[map.getWidth()][map.getHeight()];
		initMap(map);
	}

	private void initMap(BufferedImage map) {
		int w = map.getWidth();
		int h = map.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = map.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				field[xx][yy] = new Field(xx, yy);

				if (red == 0 && green == 0 && blue == 0)
					field[xx][yy].setFt(Fieldtype.obstacle);
				else if (red == 255 && green == 0 && blue == 0) {
					field[xx][yy].setFt(Fieldtype.end);
					end = field[xx][yy];
				} else if (red == 0 && green == 0 && blue == 255) {
					field[xx][yy].setFt(Fieldtype.start);
					start = field[xx][yy];
				} else
					field[xx][yy].setFt(Fieldtype.normal);

			}
		}
	}

	public Field[][] getMap() {
		return field;
	}

	public void start() {
		openList.add(start);

		do {
			Field min = getMinField();

			closedList.add(min);
			openList.remove(min);

			checkFields(min);

			if (!closedList.contains(end)) {
				path_found = true;

			}

		} while (!closedList.contains(end) || openList.isEmpty());

		if (path_found) {
			path_start = (closedList.get(closedList.size() - 2));
			path.add(path_start);

			while (path_start.getPrev() != start) {
				path.add(path_start.getPrev());
				path_start = path_start.getPrev();
			}
		}
	}

	private void checkFields(Field min) {
		try {

			checkField(min.getX() - 1, min.getY() - 1, min, true);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX(), min.getY() - 1, min, false);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX() + 1, min.getY() - 1, min, true);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX() - 1, min.getY(), min, false);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX() + 1, min.getY(), min, false);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX() - 1, min.getY() + 1, min, true);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX(), min.getY() + 1, min, false);
		} catch (Exception e) {
		}
		try {

			checkField(min.getX() + 1, min.getY() + 1, min, true);
		} catch (Exception e) {
		}
	}

	private void checkField(int x, int y, Field min, boolean diagonal) {
		if (field[x][y].getFt() == Fieldtype.obstacle || closedList.contains(field[x][y])) {

		} else {
			if (!openList.contains(field[x][y])) {
				openList.add(field[x][y]);
				field[x][y].setPrev(min);
				if (diagonal)
					field[x][y].setG(14);
				else {
					field[x][y].setG(10);
				}
				field[x][y].setH(calcH(field[x][y]));
				field[x][y].setF(field[x][y].getG() + field[x][y].getH());
			} else {
				if (min.getG() < field[x][y].getPrev().getG()) {
					field[x][y].setPrev(min);
					if (diagonal)
						field[x][y].setG(14);
					else {
						field[x][y].setG(10);
					}
					field[x][y].setF(field[x][y].getG() + field[x][y].getH());

					Collections.sort(closedList, new Comparator<Field>() {

						@Override
						public int compare(Field o1, Field o2) {
							return o1.getF() - o2.getF();
						}
					});
				}
			}
		}
	}

	private int calcH(Field f) {
		int h = (f.getX() - end.getX() + f.getY() - end.getY()) * 10;
		if (h < 0)
			h *= -1;

		return h;
	}

	private Field getMinField() {
		Field min = openList.get(0);

		for (int i = 0; i < openList.size(); i++) {
			if (openList.get(i).getF() < min.getF()) {
				min = openList.get(i);
			}
		}

		return min;
	}

	int tick = 1;
	public void tick() {
		if (path_found) {
			if (tick <= path.size()) {
				path.get(tick-1).setFt(Fieldtype.path);
				tick++;
			}
		}
	}

}

class Field {

	public Field(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int x, y;
	private int F, G, H;
	private Field prev;
	private Fieldtype ft;

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public Field getPrev() {
		return prev;
	}

	public void setPrev(Field prev) {
		this.prev = prev;
	}

	public Fieldtype getFt() {
		return ft;
	}

	public void setFt(Fieldtype ft) {
		this.ft = ft;
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

}
