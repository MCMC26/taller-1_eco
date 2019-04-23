import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica log;

	public static void main(String[] args) {
		PApplet.main("Main");
		// TODO Auto-generated method stub

	}
	public void settings() {
		size(1200,700);
	}
	
	public void setup() {
		log = new Logica(this);
	}
	

	public void draw() {
		
		background(0);
		log.dibujar();
	}
	

	public void keyPressed() {
		log.printear();
	}
}