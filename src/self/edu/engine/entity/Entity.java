package self.edu.engine.entity;

import self.edu.engine.interfaces.Renderable;
import self.edu.engine.interfaces.Tickable;
import self.edu.game.states.Play;

public abstract class Entity implements Tickable, Renderable {

	// location
	protected double x, y;
	
	protected Play p;

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
