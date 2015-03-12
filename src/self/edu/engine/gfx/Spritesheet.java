package self.edu.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private int width, height;
	private int[] pixels;
	
	public Spritesheet(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image == null)
			return;
		
		width = image.getWidth();
		height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}
	
	public int[] getSprite(int x, int y, int width, int height) {
		int[] pixels = new int[width * height];
		
		for (int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				pixels[j + i * width] = getPixel(x + j, y + i);
		
		return pixels;
	}
	
	public int getPixel(int x, int y) {
		return pixels[x + y * width];
	}
	
}
