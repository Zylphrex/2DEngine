package self.edu.game;

import self.edu.engine.entity.Mob;
import self.edu.engine.gfx.Animation;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Sprite;
import self.edu.engine.gfx.Spritesheet;
import self.edu.engine.input.Keyboard;

public class Ship extends Mob {

	private static Spritesheet spritesheet = new Spritesheet("/ship.png");
	private static Sprite stationary = new Sprite(spritesheet, 0, 0, 32, 32);
	private static Sprite moving = new Sprite(spritesheet, 0, 1, 32, 32);

	private int ticks = 0;
	private double angle = 0;

	public Ship(int x, int y) {
		this.x = x;
		this.y = y;
		this.speed = 0;
		this.maxSpeed = 10;
		this.acceleration = .5;

		// this will not work here... change later
		this.animation = new Animation(new Sprite[] { stationary, moving });
		this.animation.setOrder(new int[] { 0, 1 });
	}

	@Override
	public void tick() {
		if (++ticks % 4 == 0) {

			this.animation.tick();

			if (Keyboard.isPressed(Keyboard.K_LEFT))
				angle -= 5;
			else if (Keyboard.isPressed(Keyboard.K_RIGHT))
				angle += 5;
			if (Keyboard.isPressed(Keyboard.K_UP))
				accelerate();
			else
				decelerate();

			move(speed, (int) angle);
		}
	}

	@Override
	public void render(Screen screen) {
		screen.render(animation.getSprite(), (int) x, (int) y, angle);
	}
}
