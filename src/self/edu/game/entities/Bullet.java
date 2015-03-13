package self.edu.game.entities;

import self.edu.engine.entity.Projectile;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Sprite;
import self.edu.game.states.Play;

public class Bullet extends Projectile {

	public static final Sprite BULLET = new Sprite(3, 3, new int[] {
			0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff,
			0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff });

	public Bullet(Play p, int x, int y, float angle) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = 20;
	}

	@Override
	public void tick() {
		move(speed, angle);
		if (!shouldRemove)
			shouldRemove = x < -BULLET.getWidth()
					|| x >= p.getGame().getPixelsWidth()
					|| y < -BULLET.getHeight()
					|| y >= p.getGame().getPixelsHeight();

	}

	@Override
	public void render(Screen screen) {
		screen.render(BULLET, (int) x, (int) y);
	}

	@Override
	public boolean hasCollided(double xd, double yd) {
		int xMin = (int) x;
		int yMin = (int) y;
		int xMax = xMin + 2;
		int yMax = yMin + 2;

		for (int i = 0; i < 3; i++) {
			if (p.hit(xMin + i, yMin) || p.hit(xMin + i, yMax)
					|| p.hit(xMin, yMin + i) || p.hit(xMax, yMin + i)) {
				shouldRemove = true;
				return true;
			}
		}

		return false;
	}
}
