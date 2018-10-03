
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1350, HEIGHT = 950;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Hud hud;
	private KeyInput keyInput;
	
	
	public Game() {
		hud = new Hud(WIDTH);
		handler = new Handler(hud,HEIGHT,WIDTH);
		keyInput = new KeyInput(handler,hud);
		this.addKeyListener(keyInput);
		this.addMouseListener(keyInput);
		
		new Window(WIDTH, HEIGHT, "Simulator", this);

	}
	

	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		System.out.println("Startet");

	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}


	}
	
	public void run() {
		this.requestFocus();//muss nicht das Fenster anklicken
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis()-timer >1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		stop();
	}
	
	public void tick() {
		handler.tick();
		hud.tick();
	}
	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game();
		System.out.println("Erstellte Game");

	}
	


}

