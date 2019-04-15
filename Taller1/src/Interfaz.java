import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Interfaz extends Thread {

	private PApplet app;
	private PVector pos;
	private Logica log;
	private PImage mira, carga0, carga1, carga2, carga3, personaje;
	private int energia = 3;
	
	public Interfaz(PApplet app,Logica log) {
		this.app=app;
		this.log = log;
		mira = app.loadImage("Mira.png");
		personaje = app.loadImage("Personaje.png");
		carga0 = app.loadImage("Carga0.png");
		carga1 = app.loadImage("Carga1.png");
		carga2 = app.loadImage("Carga2.png");
		carga3 = app.loadImage("Carga3.png");
		
	}
	
	
	public void pintar() {
		switch(energia) {
		case 0:
		app.image(carga0, 25, 580);
		break;
		
		case 1:
		app.image(carga1, 25, 580);
		break;
			
		case 2:
		app.image(carga2, 25, 580);
		break;
		
		case 3:
		app.image(carga3, 25, 580);
		break;
		
		}
		
		app.image(personaje, 525,600);
		app.image(mira, app.mouseX-55, app.mouseY-55);
		
		
	}
	
	public void rotar() {
	
	}
	
	public void moverMira() {
		
	}
	
	public void destellar() {
		
	}
	
}
