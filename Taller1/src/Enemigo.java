import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemigo extends Thread{

	private PApplet app;
	private PVector pos,pos2,pos3,pos4;
	private PVector vel,vel2,vel3;
	private boolean vivo;
	private PImage nave1, nave2,nave3;
	private Logica log;
	
	public Enemigo(PApplet app, Logica log) {
		this.app=app;
		this.log=log;
		
		pos = new PVector(app.width*2,app.height/2);
		vel = new PVector(4,4);
		
		pos2 = new PVector(0,230);
		pos3 = new PVector(app.width*2,100);
		pos4 = new PVector(app.width*2,app.height/2);
		vel2 = new PVector(4,4);
		vivo = true;
		
		nave1 = app.loadImage("Nave1.png");
		nave2 = app.loadImage("Nave2.png");
		nave3 = app.loadImage("Nave3.png");
	}
	
	public void run() {
		while(vivo) {
			try {
				sleep(16);
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void pintar(int posx, int posy) {
	//	posx = (int) pos.x;
	//	posy = (int) pos.y;
		app.image(nave1, pos.x-posx, pos.y);
		app.stroke(255,255,255);
		
		
		
	}
	
	public void pintar2(int posx, int posy) {
		app.image(nave1, pos2.x-posx, pos2.y);
		app.stroke(255,255,255);
		
	}
	
	public void pintar3(int posx, int posy) {
		app.image(nave1, pos.x-posx, pos3.y);
		app.stroke(255,255,255);
		
	}
	
	public void mover() {
		
		if(pos.x >-100) {
			pos.x=pos.x-vel.x;
		}
		
		/*if(pos.x <=-10) {
			pos.x = app.width*2;
		}*/
		
		
	}
	
	public void mover2() {
		
		if(pos2.x <=app.width*2.5) {
			pos2.x=pos2.x+vel.x;
		}
			
	}
	
	public void mover3() {
		
		if(pos.x >-100) {
			pos.x=pos.x-vel.x;
		}
			}
}