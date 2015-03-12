package self.edu.engine.gfx;

public class Screen {

	private int width, height;
	private int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}

	public void clear() {
		clear(0xff000000);
	}

	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Assumes that the parameter is the same dimension as the screen pixels
	 * 
	 * @param pixels
	 */
	public void display(int[] pixels) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = this.pixels[i];
	}
	
	public void render(Sprite sprite, int x, int y) {
		for (int i = 0; i < sprite.getHeight(); i++) {
			int yy = y + i;
			if (yy < 0)
				continue;
			else if (yy >= height)
				break;
			
			for(int j = 0; j < sprite.getWidth(); j++) {
				int xx = x + j;
				if (xx < 0)
					continue;
				else if (xx >= height)
					break;
				
				buffer(sprite.getPixel(j, i), xx, yy);
			}
		}
	}
	
	/**
	 * Buffers the specified color to the specified coordinate
	 * 
	 * @param col
	 * @param x
	 * @param y
	 */
	public void buffer(int col, int x, int y) {
		if (col == 0xffff00ff || col == 0xff7f007f)
			// two colors used to represent transparent colors, if it is one of
			// these colors, ignore them
			return;

		pixels[y * width + x] = col;
	}
}
