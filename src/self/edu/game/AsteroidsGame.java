package self.edu.game;

import self.edu.engine.Game;
import self.edu.engine.states.GameState;
import self.edu.game.states.Menu;
import self.edu.game.states.Play;

public class AsteroidsGame extends Game {

	private static final long serialVersionUID = 1L;

	// game states
	private GameState menu = new Menu(this);
	private GameState play = new Play(this);

	public AsteroidsGame(int width, int height, int scale) {
		setDimension(width, height, scale);
	}

	@Override
	public void init() {
		super.init();
		getGsm().addState(menu);
		getGsm().addState(play);
	}

	public void startGame() {
		getGsm().setCurrentState(play);
	}

	public void returnToMenu() {
		getGsm().setCurrentState(menu);
	}

}
