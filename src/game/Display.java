package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import graphics.Render;
import graphics.Screen;

public class Display extends Canvas implements Runnable{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static String Title = "CopyCraft !!1!1!";
	
	public Thread thread;
	public Boolean running = false;
	private Render render;
	private Screen screen;
	private BufferedImage img;
	private int[] pixels;
	
	public Display() {
		screen = new Screen(WIDTH,HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		
	}
	
	public void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	
	private void stop() {
		if (!running) {
			return;
		}

		running = false;

		try {
			thread.join();
		}

		catch (Exception e) {
			e.getStackTrace();
			System.exit(0);
		}

	}
	
	public void run() {
		while(running) {
			tick();
			render();
		}
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.render();
		
		for(int p = 0; p < WIDTH * HEIGHT;p++) {
			pixels[p] = screen.pixels[p];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Display game = new Display();
		JFrame frame = new JFrame();

		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle(Title);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game.start();

	}
}