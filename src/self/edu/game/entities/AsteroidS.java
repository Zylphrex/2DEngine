package self.edu.game.entities;

import self.edu.game.states.Play;

public class AsteroidS extends Asteroid {

	public AsteroidS(Play p, int x, int y, float angle) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = 1;
		this.size = 1;
		adjustSpawn();
	}

	@Override
	public boolean hasCollided(double xd, double yd) {
		return false;
	}

	@Override
	protected void destroy() {
		// do nothing
	}
}
