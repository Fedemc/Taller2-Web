package sistema.grafica.controladores;

import sistema.logica.ICapaLogica;

import java.io.IOException;
import java.rmi.RemoteException;
import sistema.excepciones.PersistenciaException;

import sistema.grafica.ventanas.VentanaRespaldo;


public class ContVentanaRespaldo
{
	private ICapaLogica interfazFachada;
	private VentanaRespaldo ventResp;
	
	public ContVentanaRespaldo(VentanaRespaldo venRes)
	{
		ventResp=venRes;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void Respaldar()
	{
		try
		{
			interfazFachada.respaldarDatos();
			ventResp.mostrarResultado("Respaldo exitoso!");
		}
		catch(PersistenciaException perEx)
		{
			ventResp.mostrarResultado(perEx.darMensaje());
		}
		catch(RemoteException remEx)
		{
			ventResp.mostrarResultado(remEx.toString());
		}
		catch(IOException ioEx)
		{
			ventResp.mostrarResultado(ioEx.toString());
		}
		
	}
	

}
