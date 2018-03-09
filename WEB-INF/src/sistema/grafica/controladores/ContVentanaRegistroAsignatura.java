package sistema.grafica.controladores;

import java.rmi.RemoteException;
import sistema.excepciones.AsignaturaException;
import sistema.grafica.ventanas.VentanaRegistroAsignatura;
import sistema.logica.ICapaLogica;
import sistema.logica.asignaturas.Asignatura;

public class ContVentanaRegistroAsignatura {
	
	private ICapaLogica interfazFachada;
	private VentanaRegistroAsignatura ventRegAsignatura;

	public ContVentanaRegistroAsignatura(VentanaRegistroAsignatura asig){
		ventRegAsignatura = asig;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void registrarAsignatura (String codigo, String nombre, String descripcion) {
		
		Asignatura as = new Asignatura(codigo, nombre, descripcion);
		
		try
		{
			interfazFachada.registrarAsignatura(as);
			ventRegAsignatura.mostrarError("Asignatura ingresada correctamente!");
			
		}
		catch(AsignaturaException res)
		{
			ventRegAsignatura.mostrarError(res.darMensaje());;
		} catch (RemoteException e) {
			ventRegAsignatura.mostrarError(e.toString());
		}
	}
	
	
	
}
