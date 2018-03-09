package sistema.grafica.controladores;

import sistema.logica.ICapaLogica;
import java.rmi.RemoteException;
import sistema.grafica.ventanas.VentanaMontoRecaudado;
import sistema.excepciones.AlumnoException;


public class ContVentanaMontoRecaudado
{
	private ICapaLogica interfazFachada;
	private VentanaMontoRecaudado ventMontRec;
	
	public ContVentanaMontoRecaudado(VentanaMontoRecaudado venMontoRec)
	{
		ventMontRec=venMontoRec;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public float ObtenerMontoRecaudado(long ci, int anioLec)
	{
		float res=0;
		try
		{
			res=interfazFachada.montoTotalPorInscripciones(ci, anioLec);
		}
		catch(AlumnoException alEx)
		{
			ventMontRec.mostrarError(alEx.darMensaje());
		}
		catch(RemoteException remEx)
		{
			ventMontRec.mostrarError(remEx.toString());
		}
		
		return res;
	}
	
}
