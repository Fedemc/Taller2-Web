package sistema.grafica.controladores;


import java.rmi.RemoteException;
import sistema.excepciones.AlumnoException;

import sistema.grafica.ventanas.VentanaListadoAlumnoDetallado;

import sistema.logica.ICapaLogica;
import sistema.logica.valueObjects.VOAlumnoDetallado;
import sistema.logica.valueObjects.VOBecadoDetallado;


public class ContVentAlumnoDet {

	private ICapaLogica interfazFachada;
	private VentanaListadoAlumnoDetallado ventAlumDet;
	
	public ContVentAlumnoDet(VentanaListadoAlumnoDetallado ventAlum) {
		ventAlumDet = ventAlum;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();	
	}
	
	public void generarListado(long ced)
	{
		//Verifico si es alumno o becado y llamo al metodo correspondiente
		try
		{
			if(interfazFachada.consultaEsBecado(ced))
			{
				//llamo al que devuelve VOBecadoDetallado
				generarVOABecadoDet(ced);
			}
			else
			{
				//llamo la que devuelve el VOAlumnoDetallado
				generarVOAAlumnoDet(ced);
			}
		}
		catch(AlumnoException aluEx)
		{
			ventAlumDet.mostrarError(aluEx.darMensaje());			
		}
		catch(RemoteException remEx)
		{
			ventAlumDet.mostrarError(remEx.toString());
		}
		
	}
	
	private void generarVOAAlumnoDet(long ced) 
	{
		VOAlumnoDetallado alu=new VOAlumnoDetallado();
		
		try
		{
			alu=interfazFachada.listadoAlumnoCedulaComun(ced);
			//lo mando a la ventana
			ventAlumDet.mostrarVOAlumDet(alu);
		}
		catch(AlumnoException aluEx)
		{
			ventAlumDet.mostrarError(aluEx.darMensaje());			
		}
		catch(RemoteException remEx)
		{
			ventAlumDet.mostrarError(remEx.toString());
		}
	}
	
	private void generarVOABecadoDet(long ced) 
	{
		VOBecadoDetallado bec=new VOBecadoDetallado();
		
		try
		{
			bec=interfazFachada.listadoAlumnoCedulaBecado(ced);
			//lo mando a la ventana
			ventAlumDet.mostrarVOBecDet(bec);
		}
		catch(AlumnoException aluEx)
		{
			ventAlumDet.mostrarError(aluEx.darMensaje());			
		}
		catch(RemoteException remEx)
		{
			ventAlumDet.mostrarError(remEx.toString());
		}
	}
		
		
}
