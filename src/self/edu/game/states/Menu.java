package self.edu.game.states;

import self.edu.engine.Game;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Text;
import self.edu.engine.input.Keyboard;
import self.edu.engine.states.GameState;
import self.edu.game.Asteroids;

public class Menu extends GameState {

	private String[] options = { "Start", "Help", "Quit" };
	private int option = 0;

	private int ticks = 0;

	public Menu(Game game) {
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
		if (options[option].equals("Start"))
			((Asteroids) getGame()).startGame();
		else if (options[option].equals("Quit"))
			System.exit(0);
	}

	String[] texts = { "Use arrow keys to change selection",
			"and enter key to finalize." };
	String text = "By Zylphrex";

	@Override
	public void render(Screen screen) {
		int titleScale = 8;
		int optionsScale = 4;
		int textScale = 2;

		Text.render(screen, "Asteroids",
				screen.getWidth() / 2 - Text.getWidth("Asteroids") * titleScale
						/ 2, screen.getHeight() / 16, titleScale, 0x2244ff);

		int y = screen.getHeight() / 3;
		for (int i = 0; i < options.length; i++) {
			boolean selected = i == option;
			int col;
			String text = options[i];
			if (selected) {
				col = 0xff00;
				text = "> " + text + " <";
			} else
				col = 0xffffff;
			int x = screen.getWidth() / 2 - Text.getWidth(text) * optionsScale
					/ 2; // centers text
			Text.render(screen, text, x, y, optionsScale, col);
			y += Text.HEIGHT * optionsScale * 2;
		}

		y -= 15;
		for (int i = 0; i < texts.length; i++) {
			int x = screen.getWidth() / 2 - Text.getWidth(texts[i]) * textScale
					/ 2; // centers text
			Text.render(screen, texts[i], x, y, textScale, 0xffff);
			y += Text.HEIGHT * textScale;
		}

		y = screen.getHeight() - 5 - Text.HEIGHT * textScale;
		int x = screen.getWidth() - 5 - Text.getWidth(text) * textScale;
		Text.render(screen, text, x, y, textScale, 0x800080);
	}

	@Override
	public void reset() {
		ticks = 0;
		option = 0;
	}

	@Override
	public String toString() {
		return "Menu";
	}
}
