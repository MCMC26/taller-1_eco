import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

public class Logica implements Observer {

	int pantalla = 2;
	int s;
	int error;
	private PApplet app;
	private PVector pos;
	private PImage carga0, carga1, carga2, carga3, contenedor, disparar, explosion, fondo, guardar, instrucciones,
			instrucciones2, mira, nave1, nave2, nave3, personaje, puntero, recargar, iniciarJuego, menu, fin;
	private Interfaz inter;
	private PFont font1;
	private String ip;
	private ControlServidor ref;

	private ArrayList<Enemigo3> enemigos3;
	private ArrayList<Enemigo2> enemigos2;
	private ArrayList<Enemigo> enemigos;

	public Logica(PApplet app) {
		this.app = app;
		error = 0;
		ref = ControlServidor.getRef();
		ref.addObserver(this);
		ip = ref.getIp().getHostAddress();
		System.out.println(ip);
		enemigos = new ArrayList<Enemigo>();
		enemigos2 = new ArrayList<Enemigo2>();
		enemigos3 = new ArrayList<Enemigo3>();
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
		fin = app.loadImage("Final.png");

		menu = app.loadImage("Recurso4.png");

		inter = new Interfaz(app, this);

		agregarEnemigos();
		agregarEnemigos2();
		agregarEnemigos3();

	}

	public void agregarEnemigos() {
		for (int i = 0; i < 10; i++) {
			enemigos.add(new Enemigo(app, this));
		}
	}

	public void agregarEnemigos2() {
		for (int i = 0; i < 5; i++) {
			enemigos2.add(new Enemigo2(app, this));
		}
	}

	public void agregarEnemigos3() {
		for (int i = 0; i < 7; i++) {
			enemigos3.add(new Enemigo3(app, this));
		}
	}

	public void levantarHiloEnemigo() {
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).start();
		}
	}

	public void levantarHiloEnemigo2() {
		for (int i = 0; i < enemigos2.size(); i++) {
			enemigos2.get(i).start();
		}
	}

	public void teclear() {
		inter.recargar();
	}

	public void dibujar() {
		switch (pantalla) {
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
			s = app.frameCount / 60;
			app.fill(255);
			app.textFont(font1);
			app.textSize(80);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(30 - s, 1070, 615);
			app.noFill();

			for (int i = 0; i < enemigos.size(); i++) {
				enemigos.get(i).pintar(150 * i, 1);
				enemigos.get(i).mover();

			}

			for (int i = 0; i < enemigos2.size(); i++) {
				enemigos2.get(i).pintar(200 * i, 1);
				enemigos2.get(i).mover();

			}

			for (int i = 0; i < enemigos3.size(); i++) {
				enemigos3.get(i).pintar();
				enemigos3.get(i).mover();

			}

			if (error == 3) {
				app.fill(255, 0, 0);
				app.background(255, 0, 0);
				// app.text("MISS",inter.getX(),inter.getY());
				app.text("MISS", app.mouseX, app.mouseY);

				app.noFill();

			}

			inter.pintar();
			if (s >= 30) {
				pantalla++;
			}
			break;

		case 7:
			app.textAlign(app.CENTER, app.CENTER);
			app.image(fin, 0, 0);
			app.textSize(40);
			app.fill(255);
			app.text(inter.getPuntaje(), app.width / 2, app.height / 2);
			app.noFill();
			break;

		}

	}

	public void clickEnergía() {
		inter.quitarEnergia();
	}

	public void clickear() {
		if (pantalla != 6) {
			pantalla++;
			app.frameCount=0;
		}
		
		

		if (pantalla == 6) {
			synchronized (inter) {
				inter.validarDisparo(enemigos);
				for (int i = 0; i < enemigos.size(); i++) {
					enemigos.get(i).validarDisparo(enemigos);
					boolean miss = enemigos.get(i).getFallo1();
					}

				}

			}
			
		}
	

	public void clickear2() {

		if (pantalla == 6) {
			synchronized (inter) {
				inter.validarDisparo2(enemigos2);
				for (int i = 0; i < enemigos2.size(); i++) {
					enemigos2.get(i).validarDisparo2(enemigos2);
					boolean miss2 = enemigos2.get(i).getFallo2();
				}
			}
		}
	}

	public void clickear3() {

		if (pantalla == 6) {
			synchronized (inter) {
				inter.validarDisparo3(enemigos3);
				for (int i = 0; i < enemigos3.size(); i++) {
					enemigos3.get(i).validarDisparo3(enemigos3);
					boolean miss3 = enemigos3.get(i).getFallo3();
				}
			}

		}
	}

	/*
	 * public void disparar() { for(int i=0; i< enemigos.size();i++) {
	 * enemigos.get(i).validarDisparo();
	 * 
	 * // TODO Auto-generated method stub } }
	 */

	@Override
	public void update(Observable arg0, Object arg1) {
		String[] msg = (String[]) arg1;
		if (msg[0].matches("d")) {
			// disparar();

		}

		if (msg[0].matches("r")) {
			// recargar();

		}

		if (msg[0].matches("Mover")) {
			String angulo = msg[0];
			String fuerza = msg[2];
			// PVector pos
		}

	}

}
