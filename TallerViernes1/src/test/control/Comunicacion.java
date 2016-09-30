package test.control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable{
	private DatagramSocket socket;
	private final int PORT = 5000;
	private String ipCliente;
	private InetAddress gia;
	
	public Comunicacion(){
		// Initialization
		try {
			System.out.println("Starting socket at port "+PORT);
			socket = new DatagramSocket(PORT);
			System.out.println(InetAddress.getLocalHost().toString());
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(byte[] data, InetAddress destAddress, int destPort){
		DatagramPacket packet = new DatagramPacket(data, data.length, destAddress, destPort);
		try {
			System.out.println("Sending data to "+destAddress.getHostAddress()+":"+destPort);
			socket.send(packet);
			System.out.println("Data was sent");
		} catch (IOException e) {
			// Error sending the packet
			e.printStackTrace();
		}
	}
	
	public void mandarMensajote(Object o){
		byte[] serializado=serializar(o);
		sendMessage(serializado, gia, PORT);
	}
	
	public DatagramPacket receiveMessage(){
		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(packet);
			System.out.println("Data received from "+packet.getAddress()+":"+packet.getPort());
			ipCliente= packet.getAddress().toString();
			 gia = packet.getAddress();
			return packet;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {
		while(true){
			try{
			System.out.println("hilo iniciado");
			// Control that the socket is still listening
			if(socket!=null){
				
				
				DatagramPacket receivedPacked = receiveMessage();
			
Object receivedObject = deserializar(receivedPacked.getData());
				
				// Validate that there are no errors with the data
				if(receivedObject!=null){
					// Notify the observers that new data has arrived and pass the data to them
					setChanged();
					notifyObservers(receivedObject);
					clearChanged();
				}
			}
			Thread.sleep(100);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		}
		
}
	private Object deserializar(byte[] bytes) {
		Object data = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			data = ois.readObject();

			// close streams
			ois.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return data;
}
	
	private byte[] serializar(Object data) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(data);
			bytes = baos.toByteArray();

			// Close streams
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	

}

