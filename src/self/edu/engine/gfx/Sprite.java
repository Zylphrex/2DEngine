package self.edu.engine.gfx;

public class Sprite {

	private int width, height;
	private int[] pixels;

	public Sprite(Spritesheet sheet, int row, int column, int width, int height) {
		this.width = width;
		this.height = height;

		this.pixels = sheet.getSprite(column * width, row * height, width,
				height);
	}
	
	public Sprite(int width, int height, int pixels[]) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPixel(int x, int y) {
		return pixels[x + y * width];
	}

}
