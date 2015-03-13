package self.edu.game.states;

import java.util.ArrayList;

import self.edu.engine.Game;
import self.edu.engine.entity.Entity;
import self.edu.engine.gfx.Screen;
import self.edu.engine.states.GameState;
import self.edu.game.entities.Ship;

public class Play extends GameState {

	private ArrayList<Entity> entities = new ArrayList<Entity>();

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
	}

	@Override
	public void render(Screen screen) {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(screen);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	@Override
	public void reset() {
		addEntity(new Ship(this, getGame().getPixelsWidth() / 2
				- Ship.STATIONARY.getWidth() / 2, getGame().getPixelsHeight()
				/ 2 - Ship.STATIONARY.getHeight() / 2));
	}

}
