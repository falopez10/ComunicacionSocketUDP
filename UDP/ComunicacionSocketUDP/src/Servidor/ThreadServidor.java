package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ThreadServidor extends Thread
{
	private Socket socket;

	private int idSessio;
	public ThreadServidor(Socket socket, int id) {
		this.socket = socket;
		this.idSessio = id;
	
	}
	public void desconnectar() {
		try {
			socket.close();
		} catch (IOException ex) {
			System.out.println("Se termina la conexión");
		}
	}
	@Override
	public void run() {

		try{
		DatagramSocket serverSocket = new DatagramSocket(9876); 
		byte[] receiveData = new byte[1024]; 
		byte[] sendData  = new byte[1024]; 
		while(true) 
		{ 
			DatagramPacket receivePacket = 
					new DatagramPacket(receiveData, receiveData.length); 
			serverSocket.receive(receivePacket);
			System.out.println("se recibio el paquete: " + receivePacket);
			String sentence = new String(receivePacket.getData()); 
			InetAddress IPAddress = receivePacket.getAddress(); 
			int port = receivePacket.getPort(); 
			String capitalizedSentence = 
					sentence.toUpperCase(); 
			sendData = capitalizedSentence.getBytes(); 
			DatagramPacket sendPacket = 
					new DatagramPacket(sendData, sendData.length, IPAddress, 
							port); 
			serverSocket.send(sendPacket); 
		} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		desconnectar();
	} 
}


