package sistema.grafica.controladores;

import sistema.logica.ICapaLogica;
import java.rmi.RemoteException;
import sistema.grafica.ventanas.VentanaRegistroAlumno;
import sistema.logica.alumnos.Alumno;
import sistema.logica.alumnos.Becado;
import sistema.excepciones.AlumnoException;

public class ContVentanaRegistroAlumno
{
	private VentanaRegistroAlumno ventRegAlu;
	private ICapaLogica interfazFachada;
	

	public ContVentanaRegistroAlumno(VentanaRegistroAlumno vent)
	{
		ventRegAlu=vent;
		interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void crearAlumno(long ced, String nom, String ape, String dom, int tel, String mail) 
	{
		Alumno alu=new Alumno(ced,nom,ape,dom,tel,mail);
		//try y catch de registrarAlumno
		try
		{
			interfazFachada.registrarAlumno(alu);
			ventRegAlu.mostrarResultado("Alumno ingresado correctamente!");
		}
		catch(AlumnoException alEx)
		{
			ventRegAlu.mostrarError(alEx.darMensaje());
		}
		catch(RemoteException remEx)
		{
			ventRegAlu.mostrarError(remEx.toString());
		}
	}
	
	public void crearBecado(long ced, String nom, String ape, String dom, int tel, String mail, int descuento, String descripcion)
	{
		Becado bec=new Becado(ced,nom,ape,dom,tel,mail,descuento,descripcion);
		//try y catch
		try
		{
			//interfazFachada.registrarAlumno(bec, true);
			interfazFachada.registrarBecado(bec);
			ventRegAlu.mostrarResultado("Becado ingresado correctamente!");
		}
		catch(AlumnoException alEx)
		{
			ventRegAlu.mostrarError(alEx.darMensaje());
		}
		catch(RemoteException remEx)
		{
			ventRegAlu.mostrarError(remEx.toString());
		}		
	}
}
