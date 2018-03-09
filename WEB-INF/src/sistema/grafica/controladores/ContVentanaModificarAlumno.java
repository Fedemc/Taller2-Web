package sistema.grafica.controladores;

import sistema.grafica.ventanas.VentanaModificarAlumno3;
import sistema.logica.ICapaLogica;
import sistema.logica.valueObjects.VOAlumnoDetallado;
import sistema.logica.valueObjects.VOBecadoDetallado;
import sistema.excepciones.AlumnoException;
import java.rmi.RemoteException;

public class ContVentanaModificarAlumno {
	private ICapaLogica interfazFachada;
	private VentanaModificarAlumno3 ventModAlu;
	
	public ContVentanaModificarAlumno(VentanaModificarAlumno3 ventModificarAlumno3) {
		ventModAlu = ventModificarAlumno3;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void modificarDatosAlumno(long ced, String dom, int tel, String email) {
		try {
			interfazFachada.modificarDatosAlumno(ced, dom, tel, email);
			ventModAlu.mostrarResultado("Modificación exitosa");
			//ventModAlu.limpiarCampos();
		}
		catch (AlumnoException aluEx) {
			ventModAlu.mostrarError(aluEx.darMensaje());
		}
		catch (RemoteException remEx) {
			ventModAlu.mostrarError(remEx.toString());
		}
			
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
			ventModAlu.mostrarError(aluEx.darMensaje());			
		}
		catch(RemoteException remEx)
		{
			ventModAlu.mostrarError(remEx.toString());
		}
		
	}
	
	private void generarVOAAlumnoDet(long ced) 
	{
		VOAlumnoDetallado alu=new VOAlumnoDetallado();
		
		try
		{
			alu=interfazFachada.listadoAlumnoCedulaComun(ced);
			//lo mando a la ventana
			ventModAlu.mostrarVOAlumDet(alu);
		}
		catch(AlumnoException aluEx)
		{
			ventModAlu.mostrarError(aluEx.darMensaje());			
		}
		catch(RemoteException remEx)
		{
			ventModAlu.mostrarError(remEx.toString());
		}
	}
	
	private void generarVOABecadoDet(long ced) 
	{
		VOBecadoDetallado bec=new VOBecadoDetallado();
		
		try
		{
			bec=interfazFachada.listadoAlumnoCedulaBecado(ced);
			//lo mando a la ventana
			ventModAlu.mostrarVOBecDet(bec);
		}
		catch(AlumnoException aluEx)
		{
			ventModAlu.mostrarError(aluEx.darMensaje());			
		}
		catch(RemoteException remEx)
		{
			ventModAlu.mostrarError(remEx.toString());
		}
	}
}
