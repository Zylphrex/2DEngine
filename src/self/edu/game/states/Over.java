package self.edu.game.states;

import self.edu.engine.Game;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Text;
import self.edu.engine.states.GameState;

public class Over extends GameState {

	public Over(Game game) {
		super(game);
	}

	@Override
	public void tick() {

	}

	String text = "Game Over";
	String text2;

	@Override
	public void render(Screen screen) {
		int titleScale = 8;
		int scoreScale = 4;

		int x = screen.getWidth() / 2 - Text.getWidth(text) * titleScale / 2;
		int y = screen.getHeight() / 6;

		Text.render(screen, text, x, y, titleScale, 0xffff);
		y += Text.HEIGHT * titleScale + 50;

		x = screen.getWidth() / 2 - Text.getWidth(text2) * scoreScale / 2;

		Text.render(screen, text2, x, y, scoreScale, 0xffffff);
	}

	@Override
	public void reset() {

	}

	public void setScore(int score) {
		text2 = "Score: " + score;
	}

}
