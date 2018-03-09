package sistema.grafica.controladores;

import java.rmi.RemoteException;
import sistema.excepciones.AlumnoException;
import sistema.excepciones.InscripcionException;

import sistema.grafica.ventanas.VentanaRegistroCalificacion;
import sistema.grafica.controladores.ContSingleton;

import sistema.logica.ICapaLogica;


public class ContRegistroCalificacion
{

	private ICapaLogica interfazFachada;
	private VentanaRegistroCalificacion ventRegCalif;
	
	public ContRegistroCalificacion(VentanaRegistroCalificacion vRegCal) 
	{
		ventRegCalif = vRegCal;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void registrarCalificacion (long ced, int codIns, int nota) throws AlumnoException, InscripcionException, RemoteException {
		
		try {
			interfazFachada.registrarResultadoAsignatura(ced, codIns, nota);
			ventRegCalif.mostrarResultado("Calificacion ingresada!");
		}
		catch (InscripcionException insEx) {
			ventRegCalif.mostrarError(insEx.darMensaje());
		}
		catch (AlumnoException aluEx) {
			ventRegCalif.mostrarError(aluEx.darMensaje());
		}
		catch (RemoteException remEx) {
			ventRegCalif.mostrarError(remEx.toString());
		}
	}
	
	
	

	
}
