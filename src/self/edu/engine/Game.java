package self.edu.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import self.edu.engine.gfx.Screen;
import self.edu.engine.input.Keyboard;
import self.edu.engine.states.GameStateManager;

public abstract class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// window
	private JFrame frame;

	// game states
	private GameStateManager gsm;

	// dim
	private int width, height, scale;

	// gfx
	private BufferedImage image;
	private int[] pixels;
	private Screen screen;

	// thread
	private boolean running;
	private Thread thread;

	/**
	 * make sure to override this method, call super.init() in it and add any
	 * gamestates to the game here
	 */
	public void init() {
		gsm = GameStateManager.getInstance();
		addKeyListener(Keyboard.getInstance());
		setGfx();
		show();
		requestFocusInWindow();
	}

	/**
	 * set dimension of game canvas in pixels by defining width, height, scale
	 * 
	 * @param width
	 * @param height
	 * @param scale
	 */
	public void setDimension(int width, int height, int scale) {
		this.width = width;
		this.height = height;
		this.scale = scale;

		Dimension d = new Dimension(width * scale, height * scale);

		this.setMaximumSize(d);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
	}

	public void setGfx() {
		if ((width | height | scale) == 0) // checks if size has been set
			return;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		screen = new Screen(width, height);
	}

	public void show() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;

		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		init();

		final double nsPerTick = 1e9 / 60;

		long then = System.nanoTime();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - then) / nsPerTick;
			then = now;

			while (delta >= 1) {
				delta--;
				tick();
			}

			buffer();
		}

	}

	public void tick() {
		gsm.getCurrentState().tick();
	}

	public void render() {
		gsm.getCurrentState().render(screen);
	}

	public void buffer() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		screen.clear();
		render();
		screen.display(pixels);

		g.drawImage(image, 0, 0, width * scale, height * scale, null);

		g.dispose();
		bs.show();
	}

	public int getPixelsWidth() {
		return width;
	}

	public int getPixelHeight() {
		return height;
	}

	public int getScale() {
		return scale;
	}

	public GameStateManager getGsm() {
		return gsm;
	}

	public Screen getScreen() {
		return screen;
	}

}