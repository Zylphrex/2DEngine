package self.edu.engine.entity;

import self.edu.engine.gfx.Animation;
import self.edu.engine.interfaces.Renderable;
import self.edu.engine.interfaces.Tickable;

public abstract class Mob extends Entity implements Tickable, Renderable {

	protected double speed, maxSpeed, acceleration;
	protected Animation animation;

	public void accelerate() {
		if (speed <= maxSpeed)
			speed += acceleration;
	}

	public void decelerate() {
		if (speed > 0)
			speed -= acceleration;
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
	public void move(double d, int angle) {

		double a = Math.toRadians(angle % 360); // convert to radians

		double xd = d * Math.cos(a);
		double yd = d * Math.sin(a);

		move(xd, yd);
	}

	public boolean hasCollided(double xd, double yd) {
		return false;
	}

}
