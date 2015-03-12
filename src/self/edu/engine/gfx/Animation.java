package self.edu.engine.gfx;

import self.edu.engine.interfaces.Tickable;

public class Animation implements Tickable {

	private Sprite[] sprites;

	private int[] order;

	private int i = 0;

	public Animation(Sprite[] sprites) {
		this.sprites = sprites;
	}

	public void setOrder(int[] order) {
		this.order = order;
	}

	@Override
	public void tick() {
		i = (i + 1) % order.length;
	}

	public Sprite getSprite() {
		return sprites[order[i]];
	}

}
