package self.edu.game.states;

import java.util.ArrayList;

import self.edu.engine.Game;
import self.edu.engine.entity.Entity;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Text;
import self.edu.engine.states.GameState;
import self.edu.game.AsteroidsGame;
import self.edu.game.entities.Asteroid;
import self.edu.game.entities.AsteroidL;
import self.edu.game.entities.AsteroidM;
import self.edu.game.entities.AsteroidS;
import self.edu.game.entities.Ship;

public class Play extends GameState {

	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private Ship ship;

	private int ticks = 0;

	private int score = 0;

	public Play(Game game) {
		super(game);
	}

	@Override
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if (e.shouldRemove()) {
				entities.remove(i);
				i--;
			}
		}

		if (++ticks % 45 == 0) {
			int x, y;
			float angle;

			switch ((int) (Math.random() * 4)) {
			default:
			case 0: // top
				x = (int) (Math.random() * getGame().getPixelsWidth());
				y = 0;
				angle = (float) (Math.random() * -Math.PI);
				break;
			case 1: // bottom
				x = (int) (Math.random() * getGame().getPixelsWidth());
				y = getGame().getPixelsHeight();
				angle = (float) (Math.random() * Math.PI);
				break;
			case 2: // left
				x = 0;
				y = (int) (Math.random() * getGame().getPixelsHeight());
				angle = (float) (Math.random() * Math.PI - Math.PI / 2);
				break;
			case 3: // right
				x = getGame().getPixelsWidth();
				y = (int) (Math.random() * getGame().getPixelsHeight());
				angle = (float) (Math.random() * Math.PI + Math.PI / 2);
				break;
			}

			switch ((int) (Math.random() * 3)) {
			default:
			case 0:
				addEntity(new AsteroidS(this, x, y, angle));
				break;
			case 1:
				addEntity(new AsteroidM(this, x, y, angle));
				break;
			case 2:
				addEntity(new AsteroidL(this, x, y, angle));
				break;
			}
		}

	}

	@Override
	public void render(Screen screen) {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(screen);
		}

		Text.render(screen, "Score: " + score, 10, 10, 2, 0xffffff);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	@Override
	public void reset() {
		ticks = score = 0;
		entities = new ArrayList<Entity>();
		ship = new Ship(this, getGame().getPixelsWidth() / 2
				- Ship.STATIONARY.getWidth() / 2, getGame().getPixelsHeight()
				/ 2 - Ship.STATIONARY.getHeight() / 2);
		addEntity(ship);
	}

	public boolean hit(int x, int y) {
		for (Entity e : entities) {
			if (e instanceof Asteroid) {
				Asteroid a = ((Asteroid) e);
				if (a.collided(x, y)) {
					a.remove();
					score += a.getScore();
					return true;
				}
			}
		}
		return false;
	}

	public boolean crash(int x, int y) {
		for (Entity e : entities) {
			if (e instanceof Asteroid && ((Asteroid) e).collided(x, y))
				return true;
		}
		return false;
	}

	public void increaseScore(int score) {
		this.score += score;
	}

	public void gameOver() {
		((AsteroidsGame) getGame()).gameOver(score);
	}

}
