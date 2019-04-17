import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemigo2 extends Thread{

	private PApplet app;
	private PVector pos,pos2,pos3,pos4;
	private PVector vel,vel2,vel3;
	private boolean vivo;
	private boolean fallo2;
	private PImage nave1, nave2,nave3;
	private Logica log;
	
	public Enemigo2(PApplet app, Logica log) {
		this.app=app;
		this.log=log;
		
		pos = new PVector(app.random(1,app.width-1),app.random(1,499));
		vel = new PVector(0,7);
		
		pos2 = new PVector(0,230);
		pos3 = new PVector(app.width*2,100);
		pos4 = new PVector(app.width*2,app.height/2);
		vel2 = new PVector(4,4);
		
		vivo = true;
		fallo2 =false;
		nave2 = app.loadImage("Nave2.png");
		
	}
	
	public void run() {
		while(vivo) {
			try {
				sleep(16);
				mover();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void pintar(int posx, int posy) {
	//	posx = (int) pos.x;
	//	posy = (int) pos.y;
		app.image(nave2, pos.x, pos.y);
		app.stroke(255,255,255);
		
		
		
	}

	
	
	public void mover() {
		
		if(pos.y <=0 || pos.y >=500) {
			vel.y*=-1;
		}
		
		pos.add(vel);
		
		/*if(pos.x <=-10) {
			pos.x = app.width*2;
		}*/
		
		
	}
	
public PVector getPos() {
		
		return pos;
	}

public void validarDisparo2(ArrayList<Enemigo2> ene2) {
	ArrayList<Enemigo2> enemigos2 = ene2;
	for (int i = enemigos2.size() - 1; i >= 0; i--) {
		Enemigo2 e2 = enemigos2.get(i);
		if (PApplet.dist(app.mouseX-55, app.mouseY-55, e2.getPos().x, e2.getPos().y) < 50) {
			
			}else {
				fallo2= true;
				
			}
}
}

public boolean getFallo2() {
	return fallo2;
}

}

