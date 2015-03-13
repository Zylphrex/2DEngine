package self.edu.game.entities;

import self.edu.engine.entity.Projectile;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Sprite;
import self.edu.engine.gfx.Spritesheet;

public abstract class Asteroid extends Projectile {

	public static final Spritesheet SPRITESHEET = new Spritesheet(
			"/asteroid.png");
	public static final Sprite ASTEROID = new Sprite(SPRITESHEET, 0, 0, 32, 32);

	protected int size;

	@Override
	public void tick() {
		move(speed, angle);
		if (!shouldRemove)
			shouldRemove = x < -ASTEROID.getWidth() * size
					|| x >= p.getGame().getPixelsWidth()
					|| y < -ASTEROID.getHeight() * size
					|| y >= p.getGame().getPixelsHeight();

		if (shouldRemove)
			destroy();
	}

	@Override
	public void render(Screen screen) {
		screen.render(ASTEROID, (int) x, (int) y, size);
	}

	protected void adjustSpawn() {
		if (x == 0)
			x -= ASTEROID.getWidth() * size;
		if (y == 0)
			y -= ASTEROID.getHeight() * size;
	}

	public boolean collided(int x, int y) {
		return x >= this.x && x <= this.x + size * ASTEROID.getWidth()
				&& y >= this.y && y <= this.y + size * ASTEROID.getHeight();
	}

	public void remove() {
		shouldRemove = true;
	}

	protected abstract void destroy();

	public int getScore() {
		return size * 10;
	}

}
