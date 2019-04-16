import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

public class Logica  implements Observer{

	int pantalla =2;
	int s;
	private PApplet app;
	private PVector pos;
	private PImage carga0, carga1, carga2, carga3, contenedor, disparar, explosion, fondo, guardar, instrucciones, instrucciones2, mira, nave1, nave2, nave3, personaje, puntero, recargar, iniciarJuego, menu;
	private Interfaz inter;
	private PFont font1;
	private String ip;
	private ControlServidor ref;
	
	private ArrayList<Enemigo2> enemigos2;
	private ArrayList<Enemigo> enemigos;
	
	
	public Logica(PApplet app) {
		this.app=app;
		ref=ControlServidor.getRef();
		ref.addObserver(this);
		ip=ref.getIp().getHostAddress();
		System.out.println(ip);
		enemigos = new ArrayList<Enemigo>();
		enemigos2 = new ArrayList<Enemigo2>();
		font1 = app.createFont("PixelOperatorHB8.tff", 48);
		
		carga0 = app.loadImage("Carga0.png");
		carga1 = app.loadImage("Carga1.png");
		carga2 = app.loadImage("Carga2.png");
		carga3 = app.loadImage("Carga3.png");
		contenedor = app.loadImage("Contenedor.png");
		disparar = app.loadImage("Disparar.png");
		explosion = app.loadImage("Explosion.png");
		fondo = app.loadImage("Fondo.png");
		guardar = app.loadImage("Guardar.png");
		instrucciones = app.loadImage("Instrucciones.png");
		instrucciones2 = app.loadImage("Instrucciones2.png");

		nave1 = app.loadImage("Nave1.png");
		nave2 = app.loadImage("Nave2.png");
		nave3 = app.loadImage("Nave3.png");
		personaje = app.loadImage("Personaje.png");
		puntero = app.loadImage("Puntero.png");
		recargar = app.loadImage("Recargar.png");
		iniciarJuego = app.loadImage("Recurso3.png");
		menu = app.loadImage("Recurso4.png");
		
		inter = new Interfaz(app,this);
		
		agregarEnemigos();
		agregarEnemigos2();
		
		
	}
	
	public void agregarEnemigos() {
		 for (int i =0; i < 10;i++) {
			 enemigos.add(new Enemigo(app,this));
			 
		 }
	}
	public void agregarEnemigos2() {
			 for (int i =0; i < 3;i++) {
				 enemigos2.add(new Enemigo2(app,this));
				 
			 }
		
	}
	
	public void levantarHiloEnemigo() {
		for (int i =0; i < enemigos.size();i++) {
			 enemigos.get(i).start();
			 
		 }
		
		
	}
	
	
	public void dibujar() {
		switch(pantalla) {
		case 2:
			app.image(iniciarJuego, -10, 0);
			
			
			
		break;
		
		
		case 3:
			app.image(menu, -10, 0);
		break;
		
		case 4:
			app.image(instrucciones, -10, 0);
		break; 
		
		case 5:
			app.image(instrucciones2, -10, 0);
		break; 
		
		case 6:
			app.image(fondo, -10, 0);
			app.image(contenedor, 965, 580);
			s = app.frameCount /60;
			app.fill(255);
			app.textFont(font1);
			app.textSize(80);
			app.textAlign(app.CENTER,app.CENTER);
			app.text(30-s,1070, 615);
			app.noFill();
			inter.pintar();
			
			for(int i=0; i< enemigos.size();i++) {
				enemigos.get(i).pintar(150*i,1);
				enemigos.get(i).mover();
				enemigos.get(i).pintar2(150*i,1);
				enemigos.get(i).mover2();
				enemigos.get(i).pintar3(150*i,1);
				enemigos.get(i).mover3();	
			}
			
	/*	Aun no esta completo
	 * 
	 * 	for(int i=0; i< enemigos2.size();i++) {
				enemigos2.get(i).pintar(200*i,1);
				enemigos2.get(i).mover();
				
			}*/
			
		break; 
		
		case 7:
		app.textAlign(app.CENTER,app.CENTER);
		app.fill(255,0,0);
		app.text("HAS MUERTO", app.width/2, app.height/2);
		app.noFill();
		break;
		
		}
		
		
	}

	public void clickear() {
		pantalla++;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String[] msg=(String[]) arg1;
		if(msg[0].matches("d")){
			//disparar();
			
		}
		
	
		if(msg[0].matches("r")){
			//recargar();
			
		}
		
		 if(msg[0].matches("Mover")) {
		String angulo =msg[0];
		String fuerza =msg[2];
		//PVector pos  
		 }
		
		
	}
	
	
	
	
	
	
}
