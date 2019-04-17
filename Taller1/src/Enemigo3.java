import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemigo3 extends Thread{

	private PApplet app;
	private PVector pos,pos2,pos3,pos4;
	private PVector vel,vel2,vel3;
	private boolean vivo;
	private PImage nave1, nave2,nave3;
	private Logica log;
	
	private int posx2;
	private int posy2;
	private int posy3;
	private int posx3;
	
	private boolean fallo3;
	public Enemigo3(PApplet app, Logica log) {
		this.app=app;
		this.log=log;
		
		pos = new PVector(app.random(1,1100),app.random(1,499));
		vel = new PVector(0,4);
		
		pos2 = new PVector(0,230);
		pos3 = new PVector(app.width*2,100);
		pos4 = new PVector(app.width*2,app.height/2);
		vel2 = new PVector(4,4);
		vivo = true;
		
		posx2 =(int) (pos.x+50);
		posy2 = (int) (pos.y+50);
		posy3 = (int) (pos.y-50);
		posx3 = (int) (pos.x-50);
		nave3 = app.loadImage("Nave3.png");
		
		fallo3 = false;
		
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
	
	public void pintar() {
	//	posx = (int) pos.x;
	//	posy = (int) pos.y;
		app.image(nave3, pos.x, pos.y,80,80);
		app.stroke(255,255,255);
		
		
		
	}

	
	
	public void mover() {
		
		
		
		if(pos.x <=posx2 && pos.y<posy2) {
			pos.x++;
			
		}
		if(pos.x>=posx2) {
			
			if(pos.y<posy2) {
				pos.y++;
			}
		}
		
		if(pos.y>posy2) {
			pos.x--;
		}
		
		if(pos.x <=posx3) {
			pos.y--;
			
		}
		
		
			
			
		}
		
		
		/*if(pos.x <=-10) {
			pos.x = app.width*2;
		}*/
		
		
	
public PVector getPos() {
		
		return pos;
	}



public void validarDisparo3(ArrayList<Enemigo3> ene3) {
	ArrayList<Enemigo3> enemigos3 = ene3;
	for (int i = enemigos3.size() - 1; i >= 0; i--) {
		Enemigo3 e3 = enemigos3.get(i);
		if (PApplet.dist(app.mouseX-55, app.mouseY-55, e3.getPos().x, e3.getPos().y) < 50) {
			
			}
}
}

public boolean getFallo3() {
	return fallo3;
}

}

