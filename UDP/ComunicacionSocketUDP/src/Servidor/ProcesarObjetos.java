package Servidor;

import Cliente.ClienteUDP;

public class ProcesarObjetos 
{
	private ClienteUDP cli;
	
	public int darNumeroObjetos()
	{
		return cli.getNumObjetos();
	}
	
	

}
