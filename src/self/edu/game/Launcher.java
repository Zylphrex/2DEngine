package self.edu.game;

public class Launcher {

	public static void main(String[] args) {
		int width = 720;
		AsteroidsGame a = new AsteroidsGame(width, width / 16 * 9, 1);
		a.init();
		a.start();
	}

}
