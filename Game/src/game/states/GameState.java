package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import game.controller.PlayerController;
import game.core.Position;
import game.core.Size;
import game.entity.Enemy;
import game.entity.ID;
import game.entity.Obstacle;
import game.entity.Player;
import game.gfx.BufferedImageLoader;
import game.gfx.SpriteLibrary;
import game.main.Camera;
import game.main.Game;
import game.main.Handler;
import game.main.Time;
import game.map.GameMap;

public class GameState extends State {

	Camera camera;
	public static GameMap map;

	public static Time time;

	BufferedImage level;
	private SpriteLibrary spriteLibrary;

	public GameState(Handler handler, Camera cam) {
		super(handler);
		this.camera = cam;
	}

	public void init() {

		time = new Time();

		level = BufferedImageLoader.loadImage("/level.png/");
		spriteLibrary = new SpriteLibrary();
		map = new GameMap(level, spriteLibrary);

		loadMap(map);
	}

	private void loadMap(GameMap map) {
		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				if (map.getTile(x, y).getTileName() == "wall") {
					handler.addObject(new Obstacle(new Position(x * Game.tileSize, y * Game.tileSize),
							new Size(Game.tileSize, Game.tileSize), ID.Obstacle));
				}
			}
		}

		handler.addObject(
				new Player(new Position(0 * Game.tileSize, 0 * Game.tileSize), new Size(Game.tileSize, Game.tileSize),
						ID.Player, new PlayerController(handler), handler, spriteLibrary));

		handler.addObject(new Enemy(new Position(14 * Game.tileSize, 2 * Game.tileSize),
				new Size(Game.tileSize, Game.tileSize), ID.Enemy, handler, spriteLibrary));

	}

	@Override
	public void tick() {
		sortObjectsByPosition();

		for (int i = 0; i < handler.getObjects().size(); i++) {
			if (handler.getObjects().get(i).getId() == ID.Enemy) {
				camera.tick(handler.getObjects().get(i));
			}
		}

		handler.tick();

	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Game.WindowWidth, Game.WindowHeight);

		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(-camera.getX(), -camera.getY());

		renderMap(map, g);
		handler.render(g);

		if (handler.isDebug() && handler.isShowTiles()) {

			g.setColor(Color.RED);
			for (int x = 0; x < map.getTiles().length; x++) {
				g.drawLine(x * Game.tileSize, 0, x * Game.tileSize, map.getTiles()[0].length * Game.tileSize);
			}
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				g.drawLine(0, y * Game.tileSize, map.getTiles().length * Game.tileSize, y * Game.tileSize);
			}
		}

		g2d.translate(camera.getX(), camera.getY());

		if (handler.isDebug()) {
//			g.setColor(Color.RED);
			String mousePos = "MX: " + handler.getMx() + " | MY: " + handler.getMy();
			g.drawString(mousePos, 50, 50);

		}

	}

	private void renderMap(GameMap map, Graphics g) {
		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				if (map.getTile(x, y).getTileName() == "dirt") {
					g.drawImage(map.getTile(x, y).getSprite(), x * Game.tileSize, y * Game.tileSize, null);
				}
			}
		}
	}

	private void sortObjectsByPosition() {
		handler.getObjects().sort(Comparator.comparing(gameObject -> gameObject.getPos().getY()));
	}

}
