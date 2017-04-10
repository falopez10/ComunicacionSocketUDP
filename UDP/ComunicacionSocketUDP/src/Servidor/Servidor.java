package Servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

import Cliente.Cliente;
import Cliente.ClienteUDP;

import Cliente.ObjetoEnvio;

	

public class Servidor 
{
	private static ClienteUDP c;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese el puerto : ");
		
		String resp = br.readLine();
		int n = Integer.parseInt(resp);
		br.close();
		
		try{
			DatagramSocket serverSocket = new DatagramSocket(n);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			InetAddress ip = null;
			int i = 0;
			int numSec = 0;
			int cuantos = 0;
			while(true)
			{
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
			
				ObjetoEnvio objRecibido = recibirObjeto(receivePacket.getData());
				System.out.println("Objeto Recibido:" + objRecibido.toString());
				
				InetAddress IPAddress = receivePacket.getAddress();
				
				//System.out.println(c.getNum());
				//System.out.println(cliente.getNumObjetos());
				File archivo = new File("./recibidos/cliente"+i);
				PrintWriter writer = new PrintWriter(new FileWriter(archivo,true));
				writer.println("-----------Se muestran los objetos recibidos del cliente : "+ i +"-----------");
				Date fechaActual = new Date();
				Date f = objRecibido.getTiempoDeEnvio();
				long resta = fechaActual.getTime()-f.getTime();
				writer.println("Objeto Recibido:" + objRecibido.toString()+ " tiempo : " + resta + " ms.");
				writer.println("\n");
				writer.close();
				
				System.out.println(objRecibido.getNumeroCliente());
				
				if(objRecibido.getNumeroCliente()>i)
				{
					i++;
					ip = IPAddress;
				}
				if(numSec<objRecibido.getNumeroSecuencia())
				{
					numSec = objRecibido.getNumeroSecuencia();
				}
				else if(numSec>objRecibido.getNumeroSecuencia())
				{
					cuantos = numSec - objRecibido.getNumeroSecuencia();
					writer.println("Se pierden: " + cuantos + " objetos.");
				}
				//ENVIAR: No parece necesario
				/*
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String capitalizedSentence = sentence.toUpperCase();
				sendData = capitalizedSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				System.out.println(sendPacket);
				*/
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static ObjetoEnvio recibirObjeto(byte[] receiveData) throws IOException, ClassNotFoundException
	{
		//RECIBIR UN OBJETO...
		ByteArrayInputStream bis = new ByteArrayInputStream(receiveData);
		ObjectInput in = null;
		ObjetoEnvio o = null;
		try {
		  in = new ObjectInputStream(bis);
		  o = (ObjetoEnvio) in.readObject(); 
		} finally {
		  try {
		    if (in != null) {
		      in.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		//...UN OBJETO RECIBIDO
		return o;
		
	}
}
