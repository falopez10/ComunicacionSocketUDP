package Cliente;

import java.io.*; 
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 
public class ClienteUDP { 
	
	private int numObjetos;
	
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;
	private int numero = 0;
	
	public int getNum() {
		return numero;
	}
	
	public ClienteUDP(String num, int puerto) {
		try {
			this.com(num, puerto);
			this.numero++;
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
	
	private void enviarObjetos(String input, byte[] sendData, int puerto) throws IOException
	{
		int numeroObjetos = 0;
		try{
		numeroObjetos = Integer.parseInt(input);
		numObjetos = numeroObjetos;
		}catch(NumberFormatException e)
		{
			System.out.println("No ingresó un numero de objetos valido");
			return;
		}
		for (int i = 1; i <= numeroObjetos; i++) 
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			String marcaTiempo = df.format(d);
			ObjetoEnvio objetoActual = new ObjetoEnvio(i, marcaTiempo);
			objetoActual.setTiempoDeEnvio(d);
			objetoActual.setNumeroCliente(numero);
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
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
			clientSocket.send(sendPacket); 
		}
	}
}