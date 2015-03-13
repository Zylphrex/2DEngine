package self.edu.game.states;

import self.edu.engine.Game;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Text;
import self.edu.engine.input.Keyboard;
import self.edu.engine.states.GameState;
import self.edu.game.AsteroidsGame;

public class Over extends GameState {

	private String[] options = { "Play Again!", "Menu" };
	private int option, ticks;

	public Over(Game game) {
		super(game);
	}

	@Override
	public void tick() {
		if (++ticks % 8 == 0) {
			if (Keyboard.isPressed(Keyboard.K_UP)) {
				if (option == 0)
					option = options.length - 1;
				else
					option = (option - 1) % options.length;
			} else if (Keyboard.isPressed(Keyboard.K_DOWN))
				option = Math.abs((option + 1) % options.length);
			else if (Keyboard.isPressed(Keyboard.K_ENTER))
				enterSelection();
		}
	}

	private void enterSelection() {
		if (options[option].equals("Play Again!")) {
			((AsteroidsGame) getGame()).startGame();
		} else if (options[option].equals("Menu")) {
			((AsteroidsGame) getGame()).returnToMenu();
		}
	}

	String text = "Game Over";
	String text2;

	@Override
	public void render(Screen screen) {
		int titleScale = 8;
		int scoreScale = 6;
		int optionScale = 4;

		int x = screen.getWidth() / 2 - Text.getWidth(text) * titleScale / 2;
		int y = screen.getHeight() / 6;

		Text.render(screen, text, x, y, titleScale, 0xffff);
		y += Text.HEIGHT * titleScale + 50;

		x = screen.getWidth() / 2 - Text.getWidth(text2) * scoreScale / 2;

		Text.render(screen, text2, x, y, scoreScale, 0xff);

		y += Text.HEIGHT * scoreScale + 50;

		for (int i = 0; i < options.length; i++) {
			boolean selected = option == i;
			String text = selected ? "> " + options[i] + " <" : options[i];
			int col = selected ? 0xff00 : 0xffffff;

			x = screen.getWidth() / 2 - Text.getWidth(text) * optionScale / 2;
			Text.render(screen, text, x, y, optionScale, col);
			y += Text.HEIGHT * optionScale + 25;
		}
	}

	@Override
	public void reset() {
		setScore(0);
		option = 0;
	}

	public void setScore(int score) {
		text2 = "Score: " + score;
	}

}
