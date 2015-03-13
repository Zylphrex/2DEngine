package self.edu.game.entities;

import self.edu.engine.entity.Projectile;
import self.edu.engine.gfx.Screen;
import self.edu.engine.gfx.Sprite;
import self.edu.game.states.Play;

public class Bullet extends Projectile {

	public static final Sprite BULLET = new Sprite(3, 3, new int[] {
			0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff,
			0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff });

	private Play p;

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
		shouldRemove = x < 0 || x >= p.getGame().getPixelsWidth() || y < 0
				|| y >= p.getGame().getPixelsHeight();
	}

	@Override
	public void render(Screen screen) {
		screen.render(BULLET, (int) x, (int) y);
	}

}
