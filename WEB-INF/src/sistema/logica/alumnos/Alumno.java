package sistema.logica.alumnos;

import java.util.Calendar;
import java.util.Iterator;
import java.util.ArrayList;
import sistema.logica.asignaturas.Asignatura;
import sistema.logica.inscripciones.Inscripcion;
import sistema.logica.inscripciones.Inscripciones;
import sistema.excepciones.InscripcionException;
import sistema.logica.valueObjects.VOInscripciones;
import java.io.Serializable;

public class Alumno implements Serializable {

	private long cedula;
	private String nombre;
	private String apellido;
	private String domicilio;
	private int telefono;
	private String email;
	private Inscripciones inscripciones;
	private int cantAprobaciones; 
	
	
	//Constructor
	public Alumno(long cedula, String nombre, String apellido, String domicilio, int telefono, String email) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.inscripciones = new Inscripciones();
		this.cantAprobaciones = 0;
	}

	//get y set
	
	public long getCedula() {
		return cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getCantAprobaciones() {
		return cantAprobaciones;
	}
	
	public void setCantAprobaciones(int cantAprobaciones) {
		this.cantAprobaciones = cantAprobaciones;
	}
	
	public Inscripciones getInscripciones()
	{
		return this.inscripciones;
	}
	
	public void setInscripciones(Inscripciones insc)
	{
		this.inscripciones=insc;
	}


	//Calcular cuota total del Alumno
	public float calcularCuotaAlumno()
	{
		float cuotaTotal=0;
		cuotaTotal=this.inscripciones.calcularCuotasAlumno();
		
		return cuotaTotal;
	}
	
	public float devolverPromedioTotalAlumno()
	{
		float promedioTotal = 0;
		promedioTotal = promedioTotal + this.inscripciones.calcularPromedioTotal();
		return promedioTotal;
	}

	public float devolverPromedioAprobAlumno()
	{
		float promedioAprob = 0;
		promedioAprob = promedioAprob + this.inscripciones.calcularPromedioAprob();
		return promedioAprob;
	}
	
	//Dado un codigo de Asignatura se verifica si es valida para inscribir
	//Se recorren las inscripciones del alumno para verificar que no la tenga aprobada ni la esté cursando en el mismo año
	public boolean esValidaInscripcion(String codAsig) throws InscripcionException
	{
		boolean inscripcionValida=false;
		try
		{
			inscripcionValida = inscripciones.memberAsig(codAsig);
		}
		catch(InscripcionException inscrEx)
		{
			throw new InscripcionException(inscrEx.darMensaje());
		}
		
		return inscripcionValida;
	}
	
	public void registrarInscripcion(Inscripcion i) {
		inscripciones.insert(i);
	}
	
	/*Consulta parcial de escolaridad*/
	public VOInscripciones consultaEscolaridadParcial() {
		VOInscripciones vois = new VOInscripciones();
		if (!(inscripciones.esVacia())) {
			vois = inscripciones.escolaridadParcial();
		}
		return vois;
	}
	
	/*Consulta completa de escolaridad*/
	public VOInscripciones consultaEscolaridadCompleta() {
		VOInscripciones vois = new VOInscripciones();
		if (!(inscripciones.esVacia())) {
			vois = inscripciones.escolaridadCompleta();
		}
		return vois;
	}

}
