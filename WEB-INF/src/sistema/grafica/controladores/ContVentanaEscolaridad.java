package sistema.grafica.controladores;


import java.rmi.RemoteException;
import sistema.excepciones.AlumnoException;
import sistema.excepciones.InscripcionException;

import sistema.grafica.ventanas.VentanaEscolaridad;

import sistema.logica.valueObjects.VOInscripciones;
import sistema.logica.ICapaLogica;


public class ContVentanaEscolaridad 
{
	ICapaLogica interfazFachada;
	VentanaEscolaridad ventEscolaridad;
	
	public ContVentanaEscolaridad(VentanaEscolaridad ventEsc)
	{
		ventEscolaridad=ventEsc;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public VOInscripciones crearListadoEscolaridad(long ced, boolean esCompleto)
	{
		VOInscripciones voins=new VOInscripciones();
		
		try
		{
			if(esCompleto)
			{
				voins=interfazFachada.consultaEscolaridadCompleta(ced);
			}
			else
			{
				voins=interfazFachada.consultaEscolaridadParcial(ced);
			}
			
		}
		catch(RemoteException remEx)
		{
			ventEscolaridad.mostrarError(remEx.toString());
		}
		catch(AlumnoException aluEx)
		{
			ventEscolaridad.mostrarError(aluEx.darMensaje());
		}
		catch(InscripcionException inscrEx)
		{
			ventEscolaridad.mostrarError(inscrEx.darMensaje());
		}
		
		return voins;
	}
}
