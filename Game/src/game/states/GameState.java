package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.controller.PlayerController;
import game.core.Position;
import game.core.Size;
import game.entity.Enemy;
import game.entity.ID;
import game.entity.Obstacle;
import game.entity.Player;
import game.gfx.BufferedImageLoader;
import game.main.Camera;
import game.main.Game;
import game.main.Handler;
import game.map.GameMap;

public class GameState extends State {

	Camera camera;
	public static GameMap map;

	BufferedImage level;

	public GameState(Handler handler) {
		super(handler);
	}

	public void init() {
		camera = new Camera(0, 0);
		BufferedImageLoader loader = new BufferedImageLoader();

		level = loader.loadImage("/test.png/");
		map = new GameMap(level);

		loadMap(map);
	}

	private void loadMap(GameMap map) {
		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				if (!map.getTiles()[x][y].isWalkable()) {
					handler.addObject(new Obstacle(new Position(x * Game.tileSize, y * Game.tileSize),
							new Size(Game.tileSize, Game.tileSize), ID.Enemy));
				}
			}
		}

		handler.addObject(new Player(new Position(10 * Game.tileSize, 1 * Game.tileSize),
				new Size(Game.tileSize, Game.tileSize), ID.Player, new PlayerController(handler), handler));

		handler.addObject(new Enemy(new Position(12 * Game.tileSize, 1 * Game.tileSize),
				new Size(Game.tileSize, Game.tileSize), ID.Enemy));

	}

	@Override
	public void tick() {
		for (int i = 0; i < handler.getObjects().size(); i++) {
			if (handler.getObjects().get(i).getId() == ID.Player) {
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

		handler.render(g);
		
		if(handler.isDebug()) {
			for (int x = 0; x < map.getTiles().length; x++) {
				g.drawLine(x * Game.tileSize, 0, x * Game.tileSize, Game.WindowHeight);
			}
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				g.drawLine(0, y * Game.tileSize, Game.WindowWidth, y * Game.tileSize);
			}
		}

		g2d.translate(camera.getX(), camera.getY());

		if (handler.isDebug()) {
			g.setColor(Color.RED);
			String mousePos = "MX: " + handler.getMx() + " | MY: " + handler.getMy();
			g.drawString(mousePos, 50, 50);
			
		}

	}

}
