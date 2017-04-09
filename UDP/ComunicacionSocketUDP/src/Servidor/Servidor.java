package Servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Cliente.ObjetoEnvio;


public class Servidor 
{
	public static void main(String args[]) throws IOException {
		try{
			DatagramSocket serverSocket = new DatagramSocket(9876);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while(true)
			{
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				ObjetoEnvio objRecibido = recibirObjeto(receivePacket.getData());
				System.out.println("Objeto Recibido:" + objRecibido.toString());
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
