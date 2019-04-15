import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemigo2 extends Thread{

	private PApplet app;
	private PVector pos,pos2,pos3,pos4;
	private PVector vel,vel2,vel3;
	private boolean vivo;
	private PImage nave1, nave2,nave3;
	private Logica log;
	
	public Enemigo2(PApplet app, Logica log) {
		this.app=app;
		this.log=log;
		
		pos = new PVector(300,-50);
		vel = new PVector(4,4);
		
		pos2 = new PVector(0,230);
		pos3 = new PVector(app.width*2,100);
		pos4 = new PVector(app.width*2,app.height/2);
		vel2 = new PVector(4,4);
		vivo = true;
		nave2 = app.loadImage("Nave2.png");
		
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
		app.image(nave2, pos.x-posx, pos.y);
		app.stroke(255,255,255);
		
		
		
	}

	
	
	public void mover() {
		
		if(pos.y <=300) {
			pos.y=pos.y+vel.y;
		}else if(pos.y>300) {
			pos.y=pos.y-vel.y;
		}
		
		/*if(pos.x <=-10) {
			pos.x = app.width*2;
		}*/
		
		
	}

}

