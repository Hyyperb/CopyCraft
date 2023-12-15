package graphics;

import java.util.Random;

public class Screen extends Render {
	
	private Render test;
	
	
	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		test = new Render(100, 100);
		for(int p = 0; p < 100 * 100; p++) {
			test.pixels[p] = random.nextInt();
		}
	}
	
	public void render() {
		
		for(int i=0; i<600*600; i++) {
			pixels[i] = 0;
		}
		
		
		int x = (int)(Math.sin(System.currentTimeMillis()%2000.0/2000*Math.PI*2)*200)+220;
		int y = 300;
		draw(test, x, y);
	}
}
