package sistema.logica.valueObjects;

import java.io.Serializable;

public class VOBecadoDetallado extends VOAlumnoDetallado implements Serializable {

	private Float porcentaje;
	private String descripcion;
	
	public VOBecadoDetallado() {
		super();
		this.porcentaje = (float) 0.0; 
		this.descripcion = "";
	}
	
	public VOBecadoDetallado(Long cedula, String nombre, String apellido, String domicilio, int telefono, String dirCorreo, Float cuotaMensual,
			Float porcentaje, String descripcion) {
		super(cedula,nombre,apellido,domicilio,telefono,dirCorreo,cuotaMensual);
		this.porcentaje = porcentaje;
		this.descripcion = descripcion;
	}
	
	public Float getPorcentaje() {
		return porcentaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return super.toString() + "\nPorcentaje: " + porcentaje + "\nDescripcion: " + descripcion;
	}
		
}
