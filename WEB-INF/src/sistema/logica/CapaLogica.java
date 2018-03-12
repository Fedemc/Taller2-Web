package sistema.logica;
import sistema.logica.alumnos.*;
import sistema.logica.asignaturas.*;
import sistema.logica.inscripciones.*;
import sistema.logica.valueObjects.*;
import sistema.persistencia.Respaldo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import sistema.excepciones.*;
import sistema.grafica.controladores.ContSingleton;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class CapaLogica extends UnicastRemoteObject implements ICapaLogica
{
	
	Alumnos alumnos = null;
	Asignaturas asignaturas = null; 
	Monitor monitor=new Monitor();
	private static final long serialVersionUID = 1L;
	private static CapaLogica instanciaFachada;
	
	/*public CapaLogica() throws RemoteException
	{
		
	}*/
	
	private CapaLogica() throws RemoteException
	{
		
	}
	
	public static CapaLogica getInstancia()
	{
		if(instanciaFachada==null)
		{
			try
			{
				instanciaFachada= new CapaLogica();
			}
			catch(RemoteException remEx)
			{
				remEx.printStackTrace();
			}
		}		

		return instanciaFachada;	
	}	
	
	
	public void crearColeccionesFachada() throws RemoteException
	{
		alumnos= new Alumnos();
		asignaturas= new Asignaturas();
	}

	/*Req. 1: Registrar una asignatura en el sistema. */
	public void registrarAsignatura (Asignatura as) throws AsignaturaException, RemoteException 
	{
		monitor.comienzoEscritura();
		if (asignaturas.getTope() >= 10)
		{
			monitor.terminoEscritura();
			String msj="Error: Ya existen 10 asignaturas registradas en el sistema";
			throw new AsignaturaException(msj);
		}
		else
		{
			if  (asignaturas.memberAsignatura(as.getCodigo()))
			{
				monitor.terminoEscritura();
				String msj="Error: Ya existe una asignatura con ese código ingresada en el sistema";
				throw new AsignaturaException(msj);
			}
			else
			{
				asignaturas.insertAsignatura(as);
				monitor.terminoEscritura();
			}
		}
	}
	
	/*Req. 2: Registro de un alumno en el sistema.*/
	public void registrarAlumno(Alumno al) throws AlumnoException, RemoteException 
	{
		monitor.comienzoEscritura();
		if (alumnos.member(al.getCedula()))
		{
			//System.out.println("Exception alumno ya registrado.");
			monitor.terminoEscritura();
			String msj= "Error: Ya existe un alumno con la cédula en el sistema.";
			throw new AlumnoException(msj);
		}
		else
		{
			alumnos.insert(al);
			monitor.terminoEscritura();
		}
			
	}
	
	public void registrarBecado(Becado bec) throws AlumnoException, RemoteException 
	{
		monitor.comienzoEscritura();
		if (alumnos.member(bec.getCedula()))
		{
			//System.out.println("Exception alumno ya registrado.");
			monitor.terminoEscritura();
			String msj= "Error: Ya existe un alumno con la cédula en el sistema.";
			throw new AlumnoException(msj);
		}
		else
		{
			alumnos.insert(bec);
			monitor.terminoEscritura();
		}
			
	}
	
	/*Req. 3: Modificación de datos de un alumno (Domicilio, teléfono y dirección de correo electrónico.*/
	public void modificarDatosAlumno(Long ced, String dom, int tel, String email) throws AlumnoException, RemoteException 
	{
		monitor.comienzoEscritura();
		if (alumnos.member(ced)) 
		{
			alumnos.find(ced).setDomicilio(dom);
			alumnos.find(ced).setTelefono(tel);
			alumnos.find(ced).setEmail(email);
		}
		else
		{
			monitor.terminoEscritura();
			String msj= "Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
	}
	
	/*Req. 4: Listado de asignaturas*/
	public VOAsignaturas listadoAsignaturas() throws AsignaturaException, RemoteException 
	{
		VOAsignaturas voas = new VOAsignaturas();
		monitor.comienzoLectura();
		if (asignaturas.getTope() == 0)
		{
			monitor.terminoLectura();
			String msj="Error: No hay asignaturas registrados.";
			throw new AsignaturaException(msj);
		}
		else
		{
			voas = asignaturas.listadoAsignaturas();
			monitor.terminoLectura();
		}
			
		return voas;
	}
	
	/*Req. 5: Listado de alumnos cuyo apellido empiece con un substring dado.*/
	public VOAlumnos listadoAlumnoApellido (String s) throws AlumnoException, RemoteException 
	{
		VOAlumnos voas = new VOAlumnos();
		monitor.comienzoLectura();
		if (alumnos.getCantidadElementos() == 0)
		{
			monitor.terminoLectura();
			String msj="Error: No hay alumnos registrados.";
			throw new AlumnoException(msj);
		}
		else
		{
			voas = alumnos.ListadoAlumnosApe(s);
			monitor.terminoLectura();
		}
			
		return voas;
	}
	
	/*Req. 6: Listado detallado de un alumno, dada una cedula. Si es becado, también listar detalles de la beca.*/
	
	public boolean consultaEsBecado(Long ced) throws AlumnoException, RemoteException
	{
		boolean resu=false;
		
		if(alumnos.member(ced))
		{
			if(alumnos.find(ced) instanceof Becado)
			{
				resu=true;
			}
			else
			{
				resu=false;
			}
		}
		else
		{
			String msj="Error: No existe un alumno con esa cedula en el sistema";
			throw new AlumnoException(msj);
		}			
		
		return resu;
	}
	
	public VOAlumnoDetallado listadoAlumnoCedulaComun(Long ced) throws AlumnoException, RemoteException
	{
		VOAlumnoDetallado voad = new VOAlumnoDetallado();
		monitor.comienzoLectura();
		if (alumnos.member(ced))
		{
			voad = alumnos.ListadoAlumnoCedulaCom(ced);
			monitor.terminoLectura();
		}
		else
		{
			monitor.terminoLectura();
			String msj= "Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
		return voad;
	}
	
	public VOBecadoDetallado listadoAlumnoCedulaBecado(Long ced) throws AlumnoException, RemoteException
	{
		VOBecadoDetallado vobd = new VOBecadoDetallado();
		monitor.comienzoLectura();
		if (alumnos.member(ced))
		{
			vobd = alumnos.ListadoAlumnoCedulaBec(ced);
			monitor.terminoLectura();
		}
		else
		{
			monitor.terminoLectura();
			String msj= "Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
		return vobd;
	}
	
	/*Req. 7: Registrar la inscripcion de un alumno.*/
	public void inscripcionAsignatura(Long ced, String cod, int monto) throws AsignaturaException, AlumnoException, InscripcionException, RemoteException
	{
		monitor.comienzoEscritura();
		if (asignaturas.memberAsignatura(cod)) 
		{
			if (alumnos.member(ced)) 
			{
				Alumno alu = alumnos.find(ced);
				boolean retorno=true;
				try
				{
					retorno=alu.esValidaInscripcion(cod);
				}
				catch(InscripcionException inscEx)
				{
					monitor.terminoEscritura();
					throw new InscripcionException(inscEx.darMensaje());
				}
				
				if (retorno) 
				{
					int nroInscripcion= alu.getInscripciones().getListaInscripciones().size() +1;
					Inscripcion i = new Inscripcion(nroInscripcion, monto ,asignaturas.findAsignatura(cod));
					alumnos.find(ced).registrarInscripcion(i);
					monitor.terminoEscritura();
				}
				else
				{
					monitor.terminoEscritura();
					String msj="Error: La inscripcion no es valida. Ya se registró una inscripción para esa materia en el año actual";
					throw new InscripcionException(msj);
				}
			}
			else
			{
				monitor.terminoEscritura();
				String msj= "Error: No existe un alumno con esa cedula en el sistema.";
				throw new AlumnoException(msj);
			}
		}
		else
		{
			monitor.terminoEscritura();
			String msj="Error: No se encontro la asignatura en el sistema.";
			throw new AsignaturaException(msj);
		}
	}
	
	/*Req. 8: Registro de resultado de una asignatura. */
	public void registrarResultadoAsignatura(long ced, int codIns, int nota) throws AlumnoException, InscripcionException, RemoteException
	{
		monitor.comienzoEscritura();
		if(alumnos.member(ced)) 
		{
			if(alumnos.find(ced).getInscripciones().member(codIns)) 
			{
				if(alumnos.find(ced).getInscripciones().find(codIns).getCalificacion()>0) 
				{
					monitor.terminoEscritura();
					String msj="Error: El alumno ya tuvo calificacion para esta inscripcion.";
					throw new InscripcionException(msj);
				}
				else 
				{
					alumnos.find(ced).getInscripciones().find(codIns).setCalificacion(nota);
					if(nota>=6) 
					{
						alumnos.find(ced).setCantAprobaciones(alumnos.find(ced).getCantAprobaciones()+1);
					}
				}
			}
			else 
			{
				monitor.terminoEscritura();
				String msj="Error: El alumno no tiene una inscripcion con el codigo dado.";
				throw new InscripcionException(msj);
			}	
		}
		else 
		{
			monitor.terminoEscritura();
			String msj="Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
		monitor.terminoEscritura();
	}
	
	/*Req. 9: Monto recaudado por inscripcioens. */
	public float montoTotalPorInscripciones(long ced, int anio) throws AlumnoException, RemoteException
	{
		float montoTotal=0;
		
		monitor.comienzoLectura();
		if(alumnos.member(ced))
		{
			Alumno aluTemp=alumnos.find(ced); //Obtengo el alumno por su cedula
			Inscripciones auxIns=aluTemp.getInscripciones();	//Obtengo las inscripciones del alumno
			for(Inscripcion ins: auxIns.getListaInscripciones())	//Recorro las inscripciones
			{
				if(ins.getAnioLectivo() == anio)	//verifico que el anio de la inscripcion en la que estoy parado sea igual al anio lectivo ingresado por parametro
				{
					montoTotal=montoTotal + ins.getMontoBase();
				}
			}
			//Verifico si es becado, si lo es aplico el descuento correspondiente
			if(alumnos.find(ced) instanceof Becado)
			{
				float porcentaje=((Becado)aluTemp).getPorcentaje() / 100;
				montoTotal=montoTotal - (montoTotal * porcentaje);
			}
			monitor.terminoLectura();
		}
		else
		{
			monitor.terminoLectura();
			String msj="Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
		return montoTotal;
	}
	
	/*Req. 10: Respaldo de datos. */
	public void respaldarDatos() throws PersistenciaException, IOException, RemoteException
	{
		Respaldo res= new Respaldo();
		
		try
		{
			Properties p=new Properties();
			String nomArch = "config/config.properties";
			
			//Abro el archivo properties
			p.load(new FileInputStream(nomArch));
			String datosRespaldoAsignaturas= p.getProperty("rutaRespaldoAsignaturas");
			String datosRespaldoAlumnos= p.getProperty("rutaRespaldoAlumnos");

			//Respaldar datos
			try
			{
				res.respaldarAsignaturas(datosRespaldoAsignaturas, asignaturas);
				res.respaldarAlumnos(datosRespaldoAlumnos, alumnos);
			}
			catch(PersistenciaException pExc)
			{
				pExc.darMensaje();
			}		
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	//Restaurar datos
	public void restaurarDatos() throws PersistenciaException, IOException, RemoteException
	{
		Respaldo res= new Respaldo();
		
		try
		{
			Properties p=new Properties();
			String nomArch = "config/config.properties";
			
			//Abro el archivo properties
			p.load(new FileInputStream(nomArch));
			String datosRespaldoAsignaturas= p.getProperty("rutaRespaldoAsignaturas");
			String datosRespaldoAlumnos= p.getProperty("rutaRespaldoAlumnos");

			//Restaurar datos
			try
			{
				asignaturas=res.recuperarAsignaturas(datosRespaldoAsignaturas);
				alumnos=(res.recuperarAlumnos(datosRespaldoAlumnos));
				System.out.println("Datos recuperados.");
			}
			catch(PersistenciaException pExc)
			{
				throw new PersistenciaException(pExc.darMensaje());
			}		
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/*Req. 11: Consulta parcial o completa de escolaridad de un alumno*/
	public VOInscripciones consultaEscolaridadParcial(Long ced) throws InscripcionException, AlumnoException, RemoteException 
	{
		VOInscripciones vois = new VOInscripciones();
		monitor.comienzoLectura();
		if (alumnos.member(ced)) 
		{
			Alumno aluTemp = alumnos.find(ced);
			//Verifico que tenga inscripciones, si no tiene ya lo aviso en una excepcion
			//Esto se hace porque cuando me traigo la consulta de la escolaridad completa, si viene vacio va a ser porque 
			//las asignaturas no tienen calificacion, NO es porque el alumno no tiene inscripciones.
			if(aluTemp.getInscripciones().getListaInscripciones().isEmpty())
			{
				monitor.terminoLectura();
				String msj="No hay inscripciones para este alumno.";
				throw new InscripcionException(msj);
			}
			vois = aluTemp.consultaEscolaridadParcial();
			if (vois.esVacia()) 
			{
				monitor.terminoLectura();
				String msj = "No hay inscripciones para este alumno que tengan calificacion asignada, no se puede mostrar escolaridad parcial";
				throw new InscripcionException(msj);
			}
		}
		else 
		{
			monitor.terminoLectura();
			String msj = "Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
		monitor.terminoLectura();
		return vois;
	}
	
	public VOInscripciones consultaEscolaridadCompleta(Long ced) throws InscripcionException, AlumnoException, RemoteException 
	{
		VOInscripciones vois = new VOInscripciones();
		monitor.comienzoLectura();
		if (alumnos.member(ced)) 
		{
			Alumno aluTemp = alumnos.find(ced);
			
			vois = aluTemp.consultaEscolaridadCompleta();
			if (vois.esVacia()) 
			{
				monitor.terminoLectura();
				String msj = "No hay inscripciones para este alumno.";
				throw new InscripcionException(msj);
			}
		}
		else 
		{
			monitor.terminoLectura();
			String msj = "Error: No existe un alumno con esa cedula en el sistema.";
			throw new AlumnoException(msj);
		}
		monitor.terminoLectura();
		return vois;
	}
	
	/*Req. 12: Listado parcial o completo de alumnos egresados*/
	public VOAlumnos listadoEgresadosParcial() throws AlumnoException, RemoteException 
	{
		VOAlumnos voas = new VOAlumnos();
		monitor.comienzoLectura();
		voas = alumnos.listadoEgresadosParcial();
		
		if (voas.esVacia()) 
		{
			monitor.terminoLectura();
			String msj = "Error: No hay alumnos egresados en el sistema.";
			throw new AlumnoException(msj);
		}
		monitor.terminoLectura();
		return voas;
	}
	
	public VOEgresados listadoEgresadosCompleto() throws AlumnoException, RemoteException 
	{
		VOEgresados voegs = new VOEgresados();
		monitor.comienzoLectura();
		voegs = alumnos.listadoEgresadosCompleto();
		if (voegs.esVacia()) 
		{
			monitor.terminoLectura();
			String msj = "Error: No hay alumnos egresados en el sistema.";
			throw new AlumnoException(msj);
		}
		monitor.terminoLectura();
		return voegs;
	}
	
	//Consulta para verificar si hay un alumno con la ced ingresada por parametro, es para utilizarlo con la consulta web
	
	public boolean existeAlumno(Long ced) throws RemoteException, NullPointerException
	{
		return alumnos.member(ced);
	}
	
	public int cantElemAlumnos() throws RemoteException
	{
		return alumnos.getCantidadElementos();
	}
	
	public Alumnos getAlumnos()
	{
		return alumnos;
	}
}
