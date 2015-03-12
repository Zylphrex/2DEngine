package self.edu.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private static Keyboard keyboard;

	public static final int K_UP = KeyEvent.VK_UP;
	public static final int K_DOWN = KeyEvent.VK_DOWN;
	public static final int K_LEFT = KeyEvent.VK_LEFT;
	public static final int K_RIGHT = KeyEvent.VK_RIGHT;
	public static final int K_ENTER = KeyEvent.VK_ENTER;
	
	// 65536 keys
	private static boolean[] keys = new boolean [65536];
	
	private Keyboard() {

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public static boolean isPressed(int key) {
		return keys[key];
	}
	
	public static Keyboard getInstance() {
		if (keyboard == null)
			keyboard = new Keyboard();
		return keyboard;
	}
}
