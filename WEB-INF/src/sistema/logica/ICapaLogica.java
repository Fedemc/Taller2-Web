package sistema.logica;

import sistema.logica.asignaturas.*;
import sistema.logica.alumnos.*;
import sistema.logica.inscripciones.*;
import sistema.logica.valueObjects.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import sistema.excepciones.*;
import java.io.IOException;


public interface ICapaLogica extends Remote
{
	void crearColeccionesFachada() throws RemoteException;

	/*Req. 1: Registrar una asignatura en el sistema. */
	void registrarAsignatura(Asignatura as) throws AsignaturaException, RemoteException;

	/*Req. 2: Registro de un alumno en el sistema.*/
	void registrarAlumno(Alumno al) throws AlumnoException, RemoteException;
	void registrarBecado(Becado bec) throws AlumnoException, RemoteException;

	/*Req. 3: Modificación de datos de un alumno (Domicilio, teléfono y dirección de correo electrónico.*/
	void modificarDatosAlumno(Long ced, String dom, int tel, String email) throws AlumnoException, RemoteException;

	/*Req. 4: Listado de asignaturas*/
	VOAsignaturas listadoAsignaturas() throws AsignaturaException, RemoteException;

	/*Req. 5: Listado de alumnos cuyo apellido empiece con un substring dado.*/
	VOAlumnos listadoAlumnoApellido(String s) throws AlumnoException, RemoteException;

	/*Req. 6: Listado detallado de un alumno, dada una cedula. Si es becado, también listar detalles de la beca.*/
	boolean consultaEsBecado(Long ced) throws AlumnoException, RemoteException;
	VOAlumnoDetallado listadoAlumnoCedulaComun(Long ced) throws AlumnoException, RemoteException;

	VOBecadoDetallado listadoAlumnoCedulaBecado(Long ced) throws AlumnoException, RemoteException;

	/*Req. 7: Registrar la inscripcion de un alumno.*/
	void inscripcionAsignatura(Long ced, String cod, int monto)
			throws AsignaturaException, AlumnoException, InscripcionException, RemoteException;

	/*Req. 8: Registro de resultado de una asignatura. */
	void registrarResultadoAsignatura(long ced, int codIns, int nota)
			throws AlumnoException, InscripcionException, RemoteException;

	/*Req. 9: Monto recaudado por inscripcioens. */
	float montoTotalPorInscripciones(long ced, int anio) throws AlumnoException, RemoteException;

	/*Req. 10: Respaldo de datos. */
	void respaldarDatos() throws PersistenciaException, IOException, RemoteException;

	//Restaurar datos
	void restaurarDatos() throws PersistenciaException, IOException, RemoteException;

	/*Req. 11: Consulta parcial o completa de escolaridad de un alumno*/
	VOInscripciones consultaEscolaridadParcial(Long ced) throws InscripcionException, AlumnoException, RemoteException;

	VOInscripciones consultaEscolaridadCompleta(Long ced) throws InscripcionException, AlumnoException, RemoteException;

	/*Req. 12: Listado parcial o completo de alumnos egresados*/
	VOAlumnos listadoEgresadosParcial() throws AlumnoException, RemoteException;

	VOEgresados listadoEgresadosCompleto() throws AlumnoException, RemoteException;
	
}
