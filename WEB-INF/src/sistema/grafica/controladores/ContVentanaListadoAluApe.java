package sistema.grafica.controladores;

import java.util.ArrayList;

import java.rmi.RemoteException;
import sistema.excepciones.AlumnoException;

import sistema.logica.ICapaLogica;
import sistema.logica.valueObjects.VOAlumnos;
import sistema.logica.valueObjects.VOAlumno;


import sistema.grafica.ventanas.VentanaListadoAluApe;



public class ContVentanaListadoAluApe {
	
	private ICapaLogica interfazFachada;
	private VentanaListadoAluApe ventListadoAluApe;
	
	public ContVentanaListadoAluApe(VentanaListadoAluApe ventListAluApe) {
		ventListadoAluApe = ventListAluApe;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public ArrayList<VOAlumno> cargarDatos(String ape){
		VOAlumnos voas = new VOAlumnos();
		ArrayList<VOAlumno> listado = new ArrayList<VOAlumno>();
		try {
			//Obtengo el listado de asignaturas.
			voas = interfazFachada.listadoAlumnoApellido(ape);
			listado = voas.getVOAlumnosArray();
		}
		catch(AlumnoException aluEx) {
			ventListadoAluApe.mostrarError(aluEx.toString());
		}
		catch(RemoteException remEx) {
			ventListadoAluApe.mostrarError(remEx.toString());
		}
		return listado;
	}

}
