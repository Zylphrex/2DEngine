package self.edu.game.states;

import self.edu.engine.Game;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Text;
import self.edu.engine.input.Keyboard;
import self.edu.engine.states.GameState;
import self.edu.game.AsteroidsGame;

public class Help extends GameState {

	public Help(Game game) {
		super(game);
	}

	@Override
	public void tick() {
		
		if (Keyboard.isPressed(Keyboard.K_SPACE))
			((AsteroidsGame) getGame()).returnToMenu();
	}

	private String[] texts = { "Use left and right arrow keys to turn",
			"Use the forward arrow key to thrust", "Use the spacebar to fire",
			"Press space to return to the menu" };

	@Override
	public void render(Screen screen) {
		int helpScale = 2;
		int x, y = screen.getHeight()/4;

		for (int i = 0; i < texts.length; i++) {
			x = screen.getWidth() / 2 - Text.getWidth(texts[i]) * helpScale / 2;
			Text.render(screen, texts[i], x, y, helpScale, 0xffffff);
			y += Text.HEIGHT * helpScale + 15;
		}
	}

	@Override
	public void reset() {

	}

}
