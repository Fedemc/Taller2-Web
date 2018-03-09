package sistema.logica.valueObjects;

import java.io.Serializable;

public class VOEgresadoPromedioCal extends VOAlumno implements Serializable{

	private Float promedioTotal;
	private Float promedioAprob;
	
	public VOEgresadoPromedioCal(Long cedula, String nombre, String apellido, Float pt, Float pa) {
		super(cedula,nombre,apellido); 
		this.promedioTotal = pt;
		this.promedioAprob = pa;
	}

	//Getters
	public Float getPromedioTotal() {
		return promedioTotal;
	}
	
	public Float getPromedioAprob() {
		return promedioAprob;
	}

	@Override
	public String toString() {
		return "VOEgresadoPromedioCal [promedioTotal=" + promedioTotal + ", promedioAprobadas=" + promedioAprob + 
				" , toString()=" + super.toString() + "]";
	}
	
	
}
