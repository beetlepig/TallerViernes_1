package test.modelo;

import java.util.Random;

public class Bolita {
	private int x;
	private int y;
	private int r;
	private int g;
	private int b;
	private int radio;
	
	public Bolita(int x, int y){
		this.x = x;
		this.y = y;
		Random random = new Random();
		int low = 0;
		int high = 255;
		this.r = random.nextInt(high-low) + low;
		this.g = random.nextInt(high-low) + low;
		this.b = random.nextInt(high-low) + low;
		this.radio = 25;
	}
	public Bolita(int x, int y, int r, int g, int b, int radio) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
		this.radio = radio;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	
}
