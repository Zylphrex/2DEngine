package self.edu.engine.states;

import self.edu.engine.Game;
import self.edu.engine.interfaces.Renderable;
import self.edu.engine.interfaces.Tickable;

public abstract class GameState implements Tickable, Renderable{

	private Game game;
	
	public GameState(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
	
	public abstract void reset();

}
