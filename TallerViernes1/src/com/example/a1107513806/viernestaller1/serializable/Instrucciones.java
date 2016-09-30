package com.example.a1107513806.viernestaller1.serializable;

import java.io.Serializable;

public class Instrucciones implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public float x;
	public float y;
	public float tamano;
	public float r,g,b;
	
	
	public Instrucciones(float x, float y, float tam, float r, float g, float b){
		this.x=x;
		this.y=y;
		this.tamano=tam;
		this.r=r;
		this.g=g;
		this.b=b;
	}

}
