package sistema.logica.valueObjects;

import java.io.Serializable;

public class VOAsignatura implements Serializable {

	private String codigo;
	private String nombre;
	private String descripcion;
	
	public VOAsignatura(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion; 
	}

	//Getters
	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "VOAsignatura: codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion;
	}
}
