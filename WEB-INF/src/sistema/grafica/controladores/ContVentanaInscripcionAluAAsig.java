package sistema.grafica.controladores;


import java.rmi.RemoteException;
import sistema.excepciones.AlumnoException;
import sistema.excepciones.AsignaturaException;
import sistema.excepciones.InscripcionException;

import sistema.logica.ICapaLogica;

import sistema.grafica.ventanas.VentanaInscripcionAluAAsig;


public class ContVentanaInscripcionAluAAsig {
	private ICapaLogica interfazFachada;
	private VentanaInscripcionAluAAsig ventInscripcion;
	
	public ContVentanaInscripcionAluAAsig(VentanaInscripcionAluAAsig vent) {
		ventInscripcion = vent;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void inscribirAlumnoAsig(long ced, String cod, int monto){
		try {
			interfazFachada.inscripcionAsignatura(ced, cod, monto);
			ventInscripcion.mostrarResultado("Alumno inscripto con éxito");
		}
		catch (AsignaturaException asigEx) {
			ventInscripcion.mostrarError(asigEx.darMensaje());
		}
		catch (AlumnoException aluEx) {
			ventInscripcion.mostrarError(aluEx.darMensaje());
		}
		catch (InscripcionException insEx) {
			ventInscripcion.mostrarError(insEx.darMensaje());
		}
		catch (RemoteException remEx) {
			ventInscripcion.mostrarError(remEx.toString());
		}
	}
	
}
