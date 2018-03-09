package sistema;

import java.util.*;
import java.io.*;
import java.rmi.RemoteException;

import sistema.excepciones.*;
import sistema.logica.alumnos.Alumno;
import sistema.logica.alumnos.Alumnos;
import sistema.logica.alumnos.Becado;
import sistema.logica.asignaturas.Asignatura;
import sistema.logica.asignaturas.Asignaturas;
import sistema.logica.inscripciones.Inscripcion;
import sistema.logica.inscripciones.Inscripciones;
import sistema.logica.*;
import sistema.persistencia.*;
import sistema.logica.valueObjects.*;


public class principal { 

	public static void main(String[] args) throws AsignaturaException, AlumnoException, InscripcionException {
		/*
		CapaLogica fachada=null;
		try
		{
			fachada= new CapaLogica();
		}
		catch(RemoteException rEx)
		{
			rEx.printStackTrace();
		}
		
		
		try
		{
			System.out.println("Intento restaurar datos");
			fachada.restaurarDatos();
		}
		catch(PersistenciaException pExc)
		{
			System.out.println(pExc.darMensaje());
			System.out.println("No hay archivos, creo de cero");
			//Si no pudo abrir el respaldo, crear las colecciones vacias
			fachada.crearColeccionesFachada();	
		}
		catch(IOException iExc)
		{
			iExc.printStackTrace();
		}
		
		
		long ced = 2;
		String ape = "zu";
		
		//Listado asignaturas
		VOAsignaturas voAsigns=new VOAsignaturas();
		try
		{
			voAsigns=fachada.listadoAsignaturas();
			
			Iterator it = voAsigns.getVOAsignaturasArray().iterator();
			
			while(it.hasNext()) {
				System.out.println(it.next());
				
			}
			
		}
		catch(AsignaturaException asigEx)
		{
			asigEx.darMensaje();
		}
		
		//Prueba registrar asignatura
		Asignatura asig1 = new Asignatura("asignatura 1",  "Taller", "Taller de programacion en JAVA");
		Asignatura asig2 = new Asignatura("asignatura 2",  "S.O.", "Sistemas Operativos");
		Asignatura asig3 = new Asignatura("asignatura 3",  "BD", "Base de Datos");
		Asignatura asig4 = new Asignatura("asignatura 4",  "Redes", "Redes de Computadores");
		
		try
		{
			fachada.registrarAsignatura(asig1);
			fachada.registrarAsignatura(asig2);
			fachada.registrarAsignatura(asig3);
			fachada.registrarAsignatura(asig4);
		}
		catch (AsignaturaException asigEx)
		{
			System.out.println(asigEx.darMensaje());
		}
		
		
		//Listado alumnos
		VOAlumnos voAlus=new VOAlumnos();
		try
		{
			System.out.println("ENTRO");
			voAlus=fachada.listadoAlumnoApellido(ape);
			Iterator it = voAlus.getVOAlumnosArray().iterator();
			
			while(it.hasNext()) {
				System.out.println(it.next());
				
			}
		}
		catch(AlumnoException aluEx)
		{
			System.out.println(aluEx.darMensaje());
			System.out.println("SALIO");
		}
		
		
		//Prueba registrar alumno
		Alumno a1 =  new Alumno(1,"andres","zubbb","br",99,"sss@");
		Alumno a2 =  new Alumno(5,"car","zulll","sr",88,"hooo@");
		Becado b1 =  new Becado(2,"fed","zur","pp",66,"ppp@",null, 20,"alumno estudioso");
		Alumno a3 =  new Alumno(66,"sak","chaa","qwe",123,"acll@");
		
		//Alumno a4 =  new Alumno(66,"sak","chaa","qwe",123,"acll@");
		
		
		try
		{
			fachada.registrarAlumno(a1);
			fachada.registrarAlumno(a2);
			fachada.registrarAlumno(a3);
			fachada.registrarAlumno(b1);
		}
		catch (AlumnoException alEx)
		{
			System.out.println(alEx.darMensaje());
		}
		
		
			
		
		
		//Prueba inscripcion
		
		Calendar fecha = Calendar.getInstance();
	    int anioLec = fecha.get(Calendar.YEAR);
		int nroIns1 = 1;
		int nroIns2 = 2;
		
		try {
		//agrego inscripcion
		 fachada.inscripcionAsignatura(a1.getCedula(), asig1.getCodigo());
		 fachada.inscripcionAsignatura(a1.getCedula(), asig2.getCodigo());
		 
		}catch (AlumnoException a) {
			System.out.println(a.darMensaje());
		}
		catch (InscripcionException s) {
			System.out.println(s.darMensaje());
		}
		catch (AsignaturaException e) {
			System.out.println(e.darMensaje());
		}
		 
		 //ingreso nota para cada inscripcion (se ingresa ci, cod de inscripcion y nota)
		try
		{
			fachada.registrarResultadoAsignatura(a1.getCedula(), 1, 8);
			fachada.registrarResultadoAsignatura(a1.getCedula(), 2, 3);//asignatura no aprobada
		}
		catch(AlumnoException alEx)
		{
			System.out.println(alEx.darMensaje());
		}
		catch(InscripcionException inEx)
		{
			System.out.println(inEx.darMensaje());
		}
		 
		 
		 //inscribo al alumno nuevamente en la asignatura no aprobada
		 fachada.inscripcionAsignatura(a1.getCedula(), asig2.getCodigo());
		 //ingreso nota de aprobacion para la asignatura reprobada anteriormente
		 fachada.registrarResultadoAsignatura(a1.getCedula(), 3, 6);
		 
		 ArrayList<Inscripcion> lisIns = new ArrayList<>();
		 lisIns = a1.getInscripciones().getListaInscripciones();
		 Iterator<Inscripcion> it = lisIns.iterator();
		 while(it.hasNext()) {
			 System.out.println(it.next().toString());
		 }
		 
		 System.out.println(a1.toString());
		 
		
		try
		{
			fachada.respaldarDatos();
		}
		catch(PersistenciaException pExc)
		{
			System.out.println(pExc.darMensaje());
		}
		catch(IOException iExc)
		{
			iExc.printStackTrace();
		}
		 
		 
		 
		/*
		//Listado alumnos
		VOAlumnos voAlus=new VOAlumnos();
		try
		{
			voAlus=fachada.listadoAlumnoApellido(ape);
		}
		catch(AlumnoException aluEx)
		{
			aluEx.darMensaje();
		}
		
		
		System.out.println("\nListado alumnos");
		for(VOAlumno alu: voAlus.getVOAlumnosArray())
		{
			System.out.println(alu.toString());
		}
		
		*/
		//Respaldo
		
		
		
		/*
		List<VOAsignatura> vOAsignaturas = new ArrayList<>();
		vOAsignaturas = arrayAsignaturas.listadoAsignaturas();
		Iterator it = vOAsignaturas.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
			
		}
		//verifico cuanto vale el tope del array
		 System.out.println(arrayAsignaturas.getTope());
		*/
		
		
		/*
		//prueba datos del becado
		VOBecadoDetallado vob = new VOBecadoDetallado(null, null, null, null, 0, null, null, null, null);
		vob = a.ListadoAlumnoCedulaBec(ced);
		System.out.println(vob);
		*/
		
		/*
		//prueba de listado por apellido
		List<VOAlumno> vOAlumnos = new ArrayList<>();
		vOAlumnos = a.ListadoAlumnosApe(ape);
		Iterator it = vOAlumnos.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
			
		}
		*/
		
		/*
		//prueba busqueda por ci
		VOAlumno voa = new VOAlumno(a.ListadoAlumnoCedulaCom((long) ced).getCedula(), a.ListadoAlumnoCedulaCom((long) ced).getNombre(), a.ListadoAlumnoCedulaCom((long) ced).getApellido());
		System.out.println(voa);
		*/
		
		/*
		//Prueba treemap con VO
		TreeMap<Long,Alumno> a = new TreeMap<Long,Alumno>();
		a.put(a1.getCedula(), a1);
		a.put(a2.getCedula(), a2);
		a.put(a3.getCedula(), a3);
		
		Iterator iter = a.keySet().iterator();
		while(iter.hasNext()) {
			long ced = (long) iter.next();
			VOAlumno voa = new VOAlumno(a.get(ced).getCedula(), a.get(ced).getNombre(), a.get(ced).getApellido());
			System.out.println(voa.toString());
		}
		*/
		
	}

}
