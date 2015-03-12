package self.edu.game;

public class Launcher {

	public static void main(String[] args) {
		int width = 720;
		Asteroids a = new Asteroids(width, width / 16 * 9, 1);
		a.start();
	}

}
