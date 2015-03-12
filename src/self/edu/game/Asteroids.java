package self.edu.game;

import self.edu.engine.Game;
import self.edu.engine.states.GameState;
import self.edu.game.states.Menu;

public class Asteroids extends Game {

	private static final long serialVersionUID = 1L;
	
	// game states
	private GameState menu = new Menu();
	
	public Asteroids(int width, int height, int scale) {
		init();
		setDimension(width, height, scale);
		setGfx();
		show();
	}
	
	@Override
	public void init() {
		super.init();
		getGsm().addState(menu);
	}

}
