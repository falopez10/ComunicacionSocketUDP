package Cliente;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class ClienteUDP { 
	
	private int numObjetos;
	
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;
	private int numero ;
	
	
	public int getNum() {
		return numero;
	}
	
	public ClienteUDP(String num, int puerto, int pNumero) {
		try {
			this.com(num, puerto);
			this.numero = pNumero;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumObjetos() {
		return numObjetos;
	}
	
	
	public void com(String numObjetos,int puerto) throws Exception 
	{ 
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
		clientSocket = new DatagramSocket(); 
		IPAddress = InetAddress.getByName("localhost"); 
		byte[] sendData = new byte[1024]; 
		byte[] receiveData = new byte[1024]; 
		//ENVIO
		//String input = inFromUser.readLine(); 
		enviarNroObjetos(numObjetos, sendData,puerto);
		enviarObjetos(numObjetos, sendData,puerto);
		
		//RECEPCION: Parece no ser necesaria
		/*
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
		clientSocket.receive(receivePacket); 
		String modifiedSentence = new String(receivePacket.getData()); 
		System.out.println("FROM SERVER:" + modifiedSentence); 
		*/
		clientSocket.close(); 
	} 

	
	private void enviarNroObjetos(String numObjetos, byte[] sendData, int puerto)
	{
		
	}
	
	private void enviarObjetos(String input, byte[] sendData, int puerto) throws IOException
	{
		int numeroObjetos = 0;
		try{
		numeroObjetos = Integer.parseInt(input);
		numObjetos = numeroObjetos;
		}catch(NumberFormatException e)
		{
			System.out.println("No ingres� un numero de objetos valido");
			return;
		}
		//DatagramPacket sendPacket = new DatagramPacket(BigInteger.valueOf(numObjetos).toByteArray(), sendData.length, IPAddress, puerto);
		//clientSocket.send(sendPacket); 
		for (int i = 1; i <= numeroObjetos; i++) 
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			String marcaTiempo = df.format(d);
			ObjetoEnvio objetoActual = new ObjetoEnvio(i, marcaTiempo);
			objetoActual.setTiempoDeEnvio(d);
			objetoActual.setNumeroCliente(numero);
			System.out.println(numero+"EnCU");
			//PREPARAR bytes a enviar
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			try {
			  out = new ObjectOutputStream(bos);   
			  out.writeObject(objetoActual);
			  out.flush();
			  sendData = bos.toByteArray();
			  
			  System.out.println("Tama�o de sendData = " + sendData.length);
			} finally {
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
						clientSocket.send(sendPacket); 
			}
		}
	}
}