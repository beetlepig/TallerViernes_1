package test.main;

import processing.core.PApplet;

public class Main extends PApplet {
	private Logic logic;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}
	
	public void settings(){
		size(500, 500);
	}
	
	public void setup(){
		logic = new Logic(this);
	}
	
	public void draw(){
		logic.pintar();
	}
	
	public void mousePressed(){
		logic.mousePressed();
	}

}
