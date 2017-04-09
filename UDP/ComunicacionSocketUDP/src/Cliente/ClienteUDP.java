package Cliente;

import java.io.*; 
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 
class ClienteUDP { 
	private static DatagramSocket clientSocket;
	private static InetAddress IPAddress;
	public static void main(String args[]) throws Exception 
	{ 
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
		clientSocket = new DatagramSocket(); 
		IPAddress = InetAddress.getByName("localhost"); 
		byte[] sendData = new byte[1024]; 
		byte[] receiveData = new byte[1024]; 
		//ENVIO
		String input = inFromUser.readLine(); 
		enviarObjetos(input, sendData);
		
		//RECEPCION: Parece no ser necesaria
		/*
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
		clientSocket.receive(receivePacket); 
		String modifiedSentence = new String(receivePacket.getData()); 
		System.out.println("FROM SERVER:" + modifiedSentence); 
		*/
		clientSocket.close(); 
	} 
	
	private static void enviarObjetos(String input, byte[] sendData) throws IOException
	{
		int numeroObjetos = 0;
		try{
		numeroObjetos = Integer.parseInt(input);
		}catch(NumberFormatException e)
		{
			System.out.println("No ingresó un numero de objetos valido");
			return;
		}
		for (int i = 1; i <= numeroObjetos; i++) 
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String marcaTiempo = df.format(new Date());
			ObjetoEnvio objetoActual = new ObjetoEnvio(i, marcaTiempo);
			//PREPARAR bytes a enviar
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			try {
			  out = new ObjectOutputStream(bos);   
			  out.writeObject(objetoActual);
			  out.flush();
			  sendData = bos.toByteArray();
			  System.out.println("Tamaño de sendData = " + sendData.length);
			} finally {
			  try {
			    bos.close();
			  } catch (IOException ex) {
			    // ignore close exception
			  }
			}
			//Fin preparar bytes a enviar
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
			clientSocket.send(sendPacket); 
		}
	}
}