package sistema.grafica.controladores;



import java.rmi.RemoteException;
import sistema.excepciones.AlumnoException;

import sistema.grafica.ventanas.VentanaListadoEgresados;

import sistema.logica.ICapaLogica;
import sistema.logica.valueObjects.VOEgresados;
import sistema.logica.valueObjects.VOAlumnos;



public class ContVentanaListadoEgresado 
{
	private ICapaLogica interfazFachada;
	private VentanaListadoEgresados ventEgr;
	
	public ContVentanaListadoEgresado(VentanaListadoEgresados ventEgresados)
	{
		ventEgr=ventEgresados;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	
	public void generarListado(boolean esParcial)
	{
		if(esParcial)
		{
			VOAlumnos voAls=new VOAlumnos();
			try
			{
				voAls=interfazFachada.listadoEgresadosParcial();
			}
			catch(RemoteException remEx)
			{
				ventEgr.mostrarError(remEx.toString());
			}
			catch(AlumnoException alEx)
			{
				ventEgr.mostrarError(alEx.darMensaje());
			}
			
			ventEgr.mostrarVOEgrParcial(voAls);
		}
		else
		{
			VOEgresados voEgr=new VOEgresados();
			try
			{
				voEgr=interfazFachada.listadoEgresadosCompleto();
			}
			catch(RemoteException remEx)
			{
				ventEgr.mostrarError(remEx.toString());
			}
			catch(AlumnoException alEx)
			{
				ventEgr.mostrarError(alEx.darMensaje());
			}
			
			ventEgr.mostrarVOEgrCompleto(voEgr);
		}
	}
	
	
	
	
}