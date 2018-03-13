package sistema.logica.valueObjects;

import java.io.Serializable;

public class VOInscripcion implements Serializable {

	private int numero;
	private String nombreAsignatura;
	private int anioLectivo;
	private int calificacion;
	
	public VOInscripcion()
	{
		
	}
	
	public VOInscripcion(int numero, String nombreAsignatura, int anioLectivo, int calificacion) {
		this.numero = numero;
		this.nombreAsignatura = nombreAsignatura; 
		this.anioLectivo = anioLectivo;
		this.calificacion = calificacion;
	}

	//Getters
	public int getNumero() {
		return numero;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public int getAnioLectivo() {
		return anioLectivo;
	}

	public int getCalificacion() {
		return calificacion;
	}
	
	public void setNumero(int num)
	{
		this.numero=num;
	}
	
	public void setNombreAsignatura(String nomAsig)
	{
		this.nombreAsignatura=nomAsig;
	}
	
	public void setAnioLectiv(int anio)
	{
		this.anioLectivo=anio;
	}
	
	public void setCalificacion(int calif)
	{
		this.calificacion=calif;
	}

	@Override
	public String toString() {
		return "VOInscripcion [numero=" + numero + ", nombreAsignatura=" + nombreAsignatura + ", anioLectivo="
				+ anioLectivo + ", calificacion=" + calificacion + ", toString()=" + super.toString() + "]";
	}
	
		
	
}
