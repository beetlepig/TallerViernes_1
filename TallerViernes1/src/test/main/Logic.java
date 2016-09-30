package test.main;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import test.control.ControlBaseDatos;
import test.modelo.Bolita;

public class Logic {
	private PApplet app;
	private ArrayList<Bolita> bolitas;
	private ControlBaseDatos controlBD;
	
	public Logic(PApplet app){
		this.app= app;
		this.controlBD= new ControlBaseDatos("data"+File.separator+"bolitas.xml");
		this.bolitas = controlBD.leerBolitas();
	}

	public void pintar() {
		// TODO Auto-generated method stub
		for (Bolita b : bolitas) {
			app.fill(b.getR(), b.getG(), b.getB());
			app.ellipse(b.getX(), b.getY(), b.getRadio()*2, b.getRadio()*2);
		}
		
	}

	public void mousePressed() {
		Bolita nueva = new Bolita(app.mouseX, app.mouseY);
		bolitas.add(nueva);
		controlBD.agregarBolita(nueva);
		
	}
	
	
	

}
