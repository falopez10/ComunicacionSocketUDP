package Servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Servidor 
{
	public static void main(String argv[]) throws Exception
	{	

		//Creamos el socket que escucha en el puerto 6789
		ServerSocket welcomeSocket = new ServerSocket(6789);

		//Establecemos el tamaño del buffer
		welcomeSocket.setReceiveBufferSize(12);

		//Archivo
		File archivo;

		//Establecemos el timeout del socket
		welcomeSocket.setSoTimeout(30000);

		

		//Rutas de los archivos a descargar
		while(true) 
		{
			try {

				Socket conn = welcomeSocket.accept();

				DataInputStream flujo = new DataInputStream(conn.getInputStream());

				String r = flujo.readUTF();

				System.out.println(r);
				
				


				DataOutputStream salida = new DataOutputStream(conn.getOutputStream());
				
//				salida.writeUTF("Se envio el archivo...");
				conn.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Socket timed out!");
				break;
			}		     
		}

	}
}
