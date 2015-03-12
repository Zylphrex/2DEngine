package self.edu.game.states;

import self.edu.engine.gfx.Fonts;
import self.edu.engine.gfx.Screen;
import self.edu.engine.input.Keyboard;
import self.edu.engine.states.GameState;

public class Menu extends GameState {

	private Keyboard keyboard = Keyboard.getInstance();

	private String[] states = { "Start", "Quit" };
	private int state = 0;

	private int ticks = 0;

	@Override
	public void tick() {
		if (++ticks % 8 == 0)
			if (keyboard.isPressed(Keyboard.K_UP))
				state = Math.abs((state - 1) % states.length);
			else if (keyboard.isPressed(Keyboard.K_DOWN))
				state = Math.abs((state + 1) % states.length);
	}

	@Override
	public void render(Screen screen) {
		Fonts.render(screen, states[state], 0, 0);
	}

}
