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

	private int ticks = 0;
	private float mAngle = (float) (Math.PI / 2.0);
	private float fAngle = mAngle;

	private boolean isMoving;
	private boolean spacePressed;

	public Ship(Play p, int x, int y) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.speed = 0;
		this.maxSpeed = 12;
		this.acceleration = .5;
		this.deceleration = .2;
	}

	@Override
	public void move(double xd, double yd) {
		if (xd != 0 && yd != 0) {
			move(xd, 0);
			move(0, yd);
			return;
		}

		if (!hasCollided(xd, yd)) {
			x += xd;
			y += yd;
		} else {
			p.gameOver();
		}
	}

	@Override
	public void tick() {
		spacePressed = !spacePressed ? Keyboard.isPressed(Keyboard.K_SPACE)
				: true;

		if (++ticks % 4 == 0) {
			if (Keyboard.isPressed(Keyboard.K_LEFT)) {
				fAngle += ROTATION;
				isMoving = true;
			} else if (Keyboard.isPressed(Keyboard.K_RIGHT)) {
				fAngle -= ROTATION;
				isMoving = true;
			}

			if (Keyboard.isPressed(Keyboard.K_UP)) {
				accelerate();
				isMoving = true;
				mAngle = fAngle;
			} else {
				decelerate();
				isMoving = false;
			}

			if (ticks % 32 == 0 && spacePressed) {
				p.addEntity(new Bullet(p, (int) x + STATIONARY.getWidth() / 2,
						(int) y + STATIONARY.getHeight() / 2, fAngle));
				spacePressed = false;
			}

			move(speed, mAngle);
			reposition();
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
			y = -MOVING.getHeight();
		;
	}

	@Override
	public void render(Screen screen) {
		screen.render(isMoving ? MOVING : STATIONARY, (int) x, (int) y, fAngle);
	}

	@Override
	public boolean hasCollided(double xd, double yd) {
		int xMin = (int) (x + 4);
		int yMin = (int) (y + 4);
		int xMax = xMin + 24;
		int yMax = yMin + 24;

		for (int i = 0; i < 24; i++) {
			if (p.crash(xMin + i, yMin))
				return true;
			if (p.crash(xMin + i, yMax))
				return true;
			if (p.crash(xMin, yMin + i))
				return true;
			if (p.crash(xMax, yMin + i))
				return true;
		}

		return false;
	}
}
