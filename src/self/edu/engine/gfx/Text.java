package self.edu.engine.gfx;

public class Text {

	private static String chars = //
	/*	  */" !\"#$%&'()*+,-./01" //
			+ "23456789:;<=>?@ABC" //
			+ "DEFGHIJKLMNOPQRSTU" //
			+ "VWXYZ[\\]^_`abcdefg"//
			+ "hijklmnopqrstuvwxy"//
			+ "z{|}~";//

	public static final int WIDTH = 7;
	public static final int HEIGHT = 9;

	private static Spritesheet sheet = new Spritesheet("/font.png");
	private static Sprite[] sprites = new Sprite[chars.length()];

	static {
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = new Sprite(sheet, i / 18, i % 18, WIDTH, HEIGHT);
	}

	private Text() {

	}

	public static void render(Screen screen, String text, int x, int y) {
		for (int i = 0; i < text.length(); i++)
			screen.renderText(sprites[chars.indexOf(text.charAt(i))], x + i
					* WIDTH, y, 1, 0xff000000);
	}

	public static void render(Screen screen, String text, int x, int y,
			int color) {
		for (int i = 0; i < text.length(); i++)
			screen.renderText(sprites[chars.indexOf(text.charAt(i))], x + i
					* WIDTH, y, 1, color);
	}

	public static void render(Screen screen, String text, int x, int y,
			int scale, int color) {
		for (int i = 0; i < text.length(); i++)
			screen.renderText(sprites[chars.indexOf(text.charAt(i))], x + i
					* WIDTH * scale, y, scale, color);
	}

	public static int getWidth(String text) {
		return text.length() * WIDTH;
	}

}
