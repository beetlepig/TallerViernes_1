package test.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import processing.data.XML;
import test.modelo.Bolita;

public class ControlBaseDatos {
	private String rutaBD;
	private XML appData;
	
	public ControlBaseDatos(String rutaBD){
		this.rutaBD = rutaBD;
		prepareExitHandler();
		// validar si la ruta existe
		File archivo = new File(rutaBD);
		if(archivo.exists()){
			try {
				this.appData = new XML(archivo);
			} catch (IOException | ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.appData = new XML("bolitas");
		}
		
	}
	
	public void agregarBolita(Bolita b){
		XML bolitaXMl = new XML("bolita");
		bolitaXMl.setInt("x", b.getX());
		bolitaXMl.setInt("y", b.getY());
		bolitaXMl.setInt("r", b.getR());
		bolitaXMl.setInt("g", b.getG());
		bolitaXMl.setInt("b", b.getB());
		bolitaXMl.setInt("radio", b.getRadio());
		this.appData.addChild(bolitaXMl);
		
	}
	
	public ArrayList<Bolita> leerBolitas(){
		ArrayList<Bolita> list = new ArrayList<Bolita>();
		XML[] bolitasXML = appData.getChildren("bolita");
		for (int i = 0; i < bolitasXML.length; i++) {
			int x = bolitasXML[i].getInt("x");
			int y = bolitasXML[i].getInt("y");
			int r = bolitasXML[i].getInt("r");
			int g = bolitasXML[i].getInt("g");
			int b = bolitasXML[i].getInt("b");
			int radio = bolitasXML[i].getInt("radio");
		
			Bolita vieja = new Bolita(x, y, r, g, b, radio);
			list.add(vieja);
		}
		return list;
	}
	private XML getXML
	(){
		
		return appData;
	}
	
	private String getRuta(){
		return rutaBD;
	}
	
	private void prepareExitHandler () {

		Runtime.getRuntime().addShutdownHook(new Thread(cosas()));

		}
	
	private Runnable cosas(){
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
				appData.save(new File(rutaBD));
				System.out.println("XML guardado");
				
			}
		};
		
		return r;
		
	}

	
	
}
