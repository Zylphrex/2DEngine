package self.edu.engine.states;

import java.util.ArrayList;

public class GameStateManager {

	private static GameStateManager gsm;

	private ArrayList<GameState> gameStates;
	private int currentState;

	private GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = 0;
	}

	public void addState(GameState gameState) {
		gameStates.add(gameState);
	}

	public void setCurrentState(int currentState) {
		if (0 > currentState || currentState <= gameStates.size())
			return;
		this.currentState = currentState;
	}

	public void setCurrentState(GameState gameState) {
		int i = gameStates.indexOf(gameState);
		if (i == -1)
			return;
		this.currentState = i;
	}

	public GameState getCurrentState() {
		if (gameStates.size() == 0)
			return null;
		return gameStates.get(currentState);
	}

	public static GameStateManager getInstance() {
		if (gsm == null)
			gsm = new GameStateManager();

		return gsm;
	}

}
