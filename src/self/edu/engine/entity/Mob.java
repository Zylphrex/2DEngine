package self.edu.engine.entity;


public abstract class Mob extends Entity {

	protected double speed, maxSpeed, acceleration, deceleration;

	public void accelerate() {
		if (speed <= maxSpeed)
			speed += acceleration;
	}

	public void decelerate() {
		if (speed > 0)
			speed -= deceleration;
		else
			speed = 0;
	}

	public void move(double xd, double yd) {
		if (xd != 0 && yd != 0) {
			move(xd, 0);
			move(0, yd);
			return;
		}

		if (!hasCollided(xd, yd)) {
			x += xd;
			y += yd;
		}
	}

	/**
	 * move the mob d distance in the specified angle
	 * 
	 * @param d
	 *            - displacement
	 * @param angle
	 *            - angle in degrees positive x axis as 0;
	 */
	public void move(double d, float a) {
		double xd = d * Math.cos(a);
		double yd = -d * Math.sin(a);

		move(xd, yd);
	}

	public abstract boolean hasCollided(double xd, double yd);
}
