package sistema.logica.valueObjects;

import java.io.Serializable;

public class VOInscripcionDetallada extends VOInscripcion implements Serializable{

	private int montoBase;
	
	public VOInscripcionDetallada(int numero, String nombreAsignatura, int anioLectivo, int calificacion, int montoBase) {
		super(numero,nombreAsignatura,anioLectivo,calificacion);
		this.montoBase = montoBase;
	}

	//Getters
	public int getMontoBase() { 
		return montoBase;
	}

	@Override
	public String toString() {
		return "VOInscripcionDetallada [montoBase=" + montoBase + ", toString()=" + super.toString() + "]";
	}
	
	
}
