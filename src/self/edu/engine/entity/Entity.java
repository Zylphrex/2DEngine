package self.edu.engine.entity;

import self.edu.engine.interfaces.Renderable;
import self.edu.engine.interfaces.Tickable;

public abstract class Entity implements Tickable, Renderable {

	// location
	protected double x, y;

	protected boolean shouldRemove;

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean shouldRemove() {
		return shouldRemove;
	}

}
