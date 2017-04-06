package Servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Servidor 
{
	public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(9876);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                System.out.println("No se muere antes del accept");
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ((ThreadServidor) new ThreadServidor(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
