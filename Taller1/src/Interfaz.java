import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Interfaz extends Thread {

	private PApplet app;
	private int muertos =0;
	private int x;
	private int y;
	private PVector pos;
	private Logica log;
	private PImage mira, carga0, carga1, carga2, carga3, personaje, explosion;
	private int energia = 4;
	private int puntaje =0;
	
	private boolean fallo3,fallo2,fallo1;
	private boolean acierto1,acierto2,acierto3;
	private boolean recargando;
	
	
	public Interfaz(PApplet app,Logica log) {
		this.app=app;
		this.log = log;
		mira = app.loadImage("Mira.png");
		personaje = app.loadImage("Personaje.png");
		carga0 = app.loadImage("Carga0.png");
		carga1 = app.loadImage("Carga1.png");
		carga2 = app.loadImage("Carga2.png");
		carga3 = app.loadImage("Carga3.png");
		explosion = app.loadImage("Explosion.png");
		
		x=app.mouseX-55;
		y=app.mouseY-55;
		fallo1 =false;
		fallo2 =false;
		fallo3 =false;
		
		acierto1 = false;
		acierto2 =false;
		acierto3 =false;
	}
	
	
	public void pintar() {
		if(acierto1 ==true) {
			destellar();
			if(app.frameCount % 10==0) {
				acierto1 =false;
			}
		}
		
		if(acierto2 ==true) {
			destellar();
			if(app.frameCount % 10==0) {
				acierto2 =false;
			}
		}
		
		if(acierto3 ==true) {
			destellar();
			if(app.frameCount % 10==0) {
				acierto3 =false;
			}
		}
		
		
		if(fallo1 ==true) {
			miss();
			if(app.frameCount % 10==0) {
				fallo1 =false;
			}
		}
		
		if(fallo2 ==true) {
			miss();
			if(app.frameCount % 10==0) {
				fallo2 =false;
			}
		}
		
		if(fallo3 ==true) {
			miss();
			if(app.frameCount % 10==0) {
				fallo3 =false;
			}
		}
		
		
		switch(energia) {
		case 0:
		app.image(carga0, 25, 580);
		avisaRecarga();
		
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
		
		case 4:
			app.image(carga3, 25, 580);
		break;
		
		}
		pintarRecarga();
		app.textSize(32);
		app.text("PUNTAJE: "+puntaje, 375, 625);
		
		app.image(personaje, 530,600);
		app.image(mira,app.mouseX-55 ,app.mouseY-55);
		
		
	}
	
	public void quitarEnergia() {
		if(fallo1 == true && fallo2 == true && fallo3 == true && energia!=0) {
		energia--;
			}
		if(acierto1==true||acierto2 ==true||acierto3 ==true) {
			energia++;
		}
		if(acierto1 == true) {
			puntaje = puntaje+50;
		}
		if(acierto2 == true) {
			puntaje = puntaje+100;
			
		}
		if(acierto3 == true) {
			puntaje = puntaje+20;  
		}
			
		}
	
	
	public void avisaRecarga() {
		app.fill(30,30,30);
		app.noStroke();
		app.rect(400,400,400,50,10);
		app.noFill();
		
		app.textSize(25);
		app.fill(255);
		app.text("¡Sin munición restante!", 600, 420);
		app.noFill();
		
		
	}
	
	public void pintarRecarga() {
		if(recargando==true) {
			app.fill(30,30,30);
			app.noStroke();
			app.rect(400,400,400,50,10);
			app.noFill();
			
			app.textSize(25);
			app.fill(255);
			app.text("RECARGANDO...", 600, 420);
			app.noFill();
			
			if(app.frameCount%120==0) {
				recargando=false;
			}
		}
	}
	
	public void recargar() {
		if(app.keyCode=='r' || app.keyCode=='R') {
			energia =3;
			recargando=true;
		}
	}

	public void destellar() {
		app.image(explosion, app.mouseX-70,app.mouseY-70);
	}
	
	public void miss() {
		app.textSize(28);
		//app.fill(255,0,0);
		app.text("MISS", app.mouseX +100,app.mouseY);
		//app.noFill();
	}
	
	public void validarDisparo(ArrayList <Enemigo> ene) {
		ArrayList<Enemigo> enemigos = ene;
		
		for(int i = enemigos.size()-1; i>=0; i--) {
			Enemigo e = enemigos.get(i);
			if(PApplet.dist(app.mouseX-55, app.mouseY-55,e.getPos().x,e.getPos().y)<50  && energia >0) {
				 enemigos.remove(i);
				 acierto1=true;
				 
				
			}else {
				fallo1= true;
			}
			
		}
	}
	
	public void validarDisparo2(ArrayList <Enemigo2> ene2) {
		ArrayList<Enemigo2> enemigos2 = ene2;
		
		for(int i = enemigos2.size()-1; i>=0; i--) {
			Enemigo2 e2 = enemigos2.get(i);
			if(PApplet.dist(app.mouseX-55, app.mouseY-55,e2.getPos().x,e2.getPos().y)<50 && energia>0) {
				 enemigos2.remove(i);
				 acierto3 = true;
			}else {
				fallo2= true;
			}
			
		}
	}
	
	public void validarDisparo3(ArrayList <Enemigo3> ene3) {
		ArrayList<Enemigo3> enemigos3 = ene3;
		
		for(int i = enemigos3.size()-1; i>=0; i--) {
			Enemigo3 e3 = enemigos3.get(i);
			if(PApplet.dist(app.mouseX-55, app.mouseY-55,e3.getPos().x,e3.getPos().y)<50 && energia >0) {
				 enemigos3.remove(i);
				 acierto3 = true;
			}else {
				fallo3=true;
		
			}	
			
		}
	}
	
	public int getMuertos() {
		return muertos;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public int getPuntaje() {
		return puntaje;
	}
	
}
