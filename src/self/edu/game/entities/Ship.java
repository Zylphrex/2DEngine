package self.edu.game.entities;

import self.edu.engine.entity.Mob;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Sprite;
import self.edu.engine.gfx.Spritesheet;
import self.edu.engine.input.Keyboard;
import self.edu.game.states.Play;

public class Ship extends Mob {

	public static final Spritesheet SPRITESHEET = new Spritesheet("/ship.png");
	public static final Sprite STATIONARY = new Sprite(SPRITESHEET, 0, 0, 32,
			32);
	public static final Sprite MOVING = new Sprite(SPRITESHEET, 0, 1, 32, 32);

	private static final float ROTATION = (float) (15 / 360.0 * 2.0 * Math.PI);

	private Play p;

	private int ticks = 0;
	private float angle = -(float) (Math.PI / 2.0);

	private boolean isMoving;

	private int xh, yh;

	public Ship(Play p, int x, int y) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.setH();
		this.speed = 0;
		this.maxSpeed = 15;
		this.acceleration = .5;
	}

	@Override
	public void tick() {
		if (++ticks % 4 == 0) {

			if (Keyboard.isPressed(Keyboard.K_UP)) {
				accelerate();
				isMoving = true;
			} else {
				decelerate();
				isMoving = false;
			}

			if (Keyboard.isPressed(Keyboard.K_LEFT)) {
				angle -= ROTATION;
				isMoving = true;
			} else if (Keyboard.isPressed(Keyboard.K_RIGHT)) {
				angle += ROTATION;
				isMoving = true;
			}

			if (ticks % 8 == 0 && Keyboard.isPressed(Keyboard.K_SPACE))
				p.addEntity(new Bullet(p, xh, yh, angle));

			move(speed, angle);
			reposition();
			setH();
		}
	}

	private void reposition() {
		int width = p.getGame().getPixelsWidth();
		int height = p.getGame().getPixelsHeight();

		if (x < -MOVING.getWidth())
			x = width;
		else if (x >= width)
			x = -MOVING.getWidth();

		if (y < -MOVING.getHeight())
			y = height;
		else if (y >= height)
			y = -MOVING.getHeight();;
	}

	private void setH() {
		this.xh = 28;
		this.yh = 16;

		double cos = Math.cos(angle);
		double sin = Math.sin(angle);

		int xx = (int) x + STATIONARY.getWidth() / 2;
		int yy = (int) y + STATIONARY.getHeight() / 2;
		int xxx = xh - STATIONARY.getWidth() / 2;
		int yyy = yh - STATIONARY.getHeight() / 2;

		xh = (int) (xx + (xxx * cos + yyy * -sin));
		yh = (int) (yy + (yyy * sin + yyy * cos));
	}

	@Override
	public void render(Screen screen) {
		screen.render(isMoving ? MOVING : STATIONARY, (int) x, (int) y, angle);
	}
}
