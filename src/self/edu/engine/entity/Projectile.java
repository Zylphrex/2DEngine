package self.edu.engine.entity;

import self.edu.engine.interfaces.Renderable;
import self.edu.engine.interfaces.Tickable;

public abstract class Projectile extends Entity implements Tickable, Renderable {

	protected double speed;
	protected float angle;

	public void move(double xd, double yd) {
		if (xd != 0 && yd != 0) {
			move(xd, (double) 0);
			move((double) 0, yd);
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
	 *            - angle in radians positive x axis as 0;
	 */
	public void move(double d, float a) {
		double xd = d * Math.cos(a);
		double yd = -d * Math.sin(a);

		move(xd, yd);
	}

	public abstract boolean hasCollided(double xd, double yd);

}
