package self.edu.game.states;

import self.edu.engine.Game;
import self.edu.engine.gfx.Screen;
import self.edu.engine.states.GameState;
import self.edu.game.Ship;

public class Play extends GameState {

	private Ship ship;

	public Play(Game game) {
		super(game);
		ship = new Ship(50, 50);
	}

	@Override
	public void tick() {
		ship.tick();
	}

	@Override
	public void render(Screen screen) {
		ship.render(screen);
	}

	@Override
	public void reset() {

	}

	@Override
	public String toString() {
		return "Play";
	}

}
