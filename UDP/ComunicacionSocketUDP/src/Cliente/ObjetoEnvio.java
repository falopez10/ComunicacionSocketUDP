package Cliente;

import java.io.Serializable;
import java.util.Date;

public class ObjetoEnvio implements Serializable
{
	private int numeroSecuencia;
	
	private String marcaTiempo;
	
	private Date tiempoDeEnvio;
	
	private int numeroCliente;
	
	public int getNumeroCliente() {
		return numeroCliente;
	}
	
	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	
	public ObjetoEnvio(int numeroSecuencia, String marcaTiempo)
	{
		this.numeroSecuencia = numeroSecuencia;
		this.marcaTiempo = marcaTiempo;
	}
	
	public String toString()
	{
		return numeroSecuencia +":"+marcaTiempo;
	}
	
	public int getNumeroSecuencia() {
		return numeroSecuencia;
	}
	
	public void setTiempoDeEnvio(Date tiempoDeEnvio) {
		this.tiempoDeEnvio = tiempoDeEnvio;
	}
	
	public Date getTiempoDeEnvio() {
		return tiempoDeEnvio;
	}
	
}
