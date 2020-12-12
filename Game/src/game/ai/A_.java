package game.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import game.map.Tile;
import game.map.Tileset;
import game.objects.ID;

public class A_ {

	Field[][] field;
	
	Tileset ts;

	private boolean path_found = false;

	Field start, end;
	Field path_start;
	
	Tile startTile, endTile;

	LinkedList<Field> openList = new LinkedList<Field>();
	LinkedList<Field> closedList = new LinkedList<Field>();
	LinkedList<Tile> path = new LinkedList<Tile>();

	public A_() {
		
	}
	
	public LinkedList<Tile> getPath(Tileset ts, Tile start, Tile end){
		field = new Field[ts.getW()][ts.getH()];
		startTile = start;
		endTile = end;
		this.ts=ts;
		
		initMap(ts);
		start();
		
		return path;
	}

	private void initMap(Tileset ts) {
		
		openList.clear();
		closedList.clear();
		path.clear();
		
		int w = ts.getW();
		int h = ts.getH();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				
				field[xx][yy] = new Field(xx, yy);

				if (ts.getTile(xx, yy).getId()==ID.Obstacle)
					field[xx][yy].setFt(Fieldtype.obstacle);
				else if (ts.getTile(xx, yy) == endTile) {
					field[xx][yy].setFt(Fieldtype.end);
					end = field[xx][yy];
				} else if (ts.getTile(xx, yy) == startTile) {
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

			if (!closedList.contains(end))
				path_found = true;

		} while (!closedList.contains(end) || openList.isEmpty());

		if (path_found) {
			path_start = (closedList.get(closedList.size() - 2));
			path.add(ts.getTileset()[path_start.getX()][path_start.getY()]);

			while (path_start.getPrev() != start) {
				path.add(ts.getTileset()[path_start.getPrev().getX()][path_start.getPrev().getY()]);
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
