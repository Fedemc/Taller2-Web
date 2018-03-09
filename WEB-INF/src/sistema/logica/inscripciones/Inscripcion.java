package sistema.logica.inscripciones;

import java.util.Calendar;

import sistema.logica.asignaturas.Asignatura;
import java.io.Serializable;

public class Inscripcion implements Serializable {
	
	private int nroInscripcion;
	private int anioLectivo;
	private int montoBase;
	private int calificacion;
	private Asignatura asignatura; 
	
	//Constructor
	
	public Inscripcion(int nroInsc, int montoBase, Asignatura asignatura) {
		this.nroInscripcion = nroInsc;
		this.anioLectivo = Calendar.getInstance().get(Calendar.YEAR);
		this.montoBase = montoBase;
		this.calificacion = 0;
		this.asignatura = asignatura;
	}

	//get y set
	
	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public int getNroInscripcion() {
		return nroInscripcion;
	}
	
	public void setNroInscripcion(int nroIns) {
		this.nroInscripcion = nroIns;
	}
	
	public int getAnioLectivo() {
		return anioLectivo;
	}

	public int getMontoBase() {
		return montoBase;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	@Override
	public String toString() {
		return "Inscripcion [nroInscripcion=" + nroInscripcion + ", anioLectivo=" + anioLectivo + ", montoBase="
				+ montoBase + ", calificacion=" + calificacion + ", asignatura=" + asignatura + "]";
	}
	
	
}
