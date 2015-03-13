package self.edu.game.entities;

import self.edu.game.states.Play;

public class AsteroidL extends Asteroid {

	public AsteroidL(Play p, int x, int y, float angle) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = 1;
		this.size = 3;
		adjustSpawn();
	}

	@Override
	public boolean hasCollided(double xd, double yd) {
		return false;
	}

	@Override
	protected void destroy() {
		p.addEntity(new AsteroidM(p, (int) x, (int) y,
				(int) (angle - Math.PI / 6)));
		p.addEntity(new AsteroidM(p, (int) x, (int) y,
				(int) (angle + Math.PI / 6)));
	}
}
