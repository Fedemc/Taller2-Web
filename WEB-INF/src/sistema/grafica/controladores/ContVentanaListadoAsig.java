package sistema.grafica.controladores;

import java.util.ArrayList;

import java.rmi.RemoteException;
import sistema.excepciones.AsignaturaException;

import sistema.grafica.ventanas.VentanaListadoAsig;

import sistema.logica.ICapaLogica;
import sistema.logica.valueObjects.VOAsignaturas;
import sistema.logica.valueObjects.VOAsignatura;



public class ContVentanaListadoAsig {
	
	private ICapaLogica interfazFachada;
	private VentanaListadoAsig ventListadoAsig;
	
	public ContVentanaListadoAsig(VentanaListadoAsig vListAsig) {
		ventListadoAsig = vListAsig;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public ArrayList<VOAsignatura> cargarDatos() {
		VOAsignaturas voas = new VOAsignaturas();
		ArrayList<VOAsignatura> listado = new ArrayList<VOAsignatura>();
		try {
			//Obtengo el listado de asignaturas.
			voas = interfazFachada.listadoAsignaturas();
			listado = voas.getVOAsignaturasArray();
		}
		catch(AsignaturaException asigEx) {
			ventListadoAsig.mostrarError(asigEx.toString());
		}
		catch(RemoteException remEx) {
			ventListadoAsig.mostrarError(remEx.toString());
		}
		return listado;
	}

}
