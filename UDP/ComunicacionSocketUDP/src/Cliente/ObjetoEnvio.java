package Cliente;

import java.io.Serializable;

public class ObjetoEnvio implements Serializable
{
	private int numeroSecuencia;
	
	private String marcaTiempo;
	
	public ObjetoEnvio(int numeroSecuencia, String marcaTiempo)
	{
		this.numeroSecuencia = numeroSecuencia;
		this.marcaTiempo = marcaTiempo;
	}
	
	public String toString()
	{
		return numeroSecuencia +":"+marcaTiempo;
	}
	
}
