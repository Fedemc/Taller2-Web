package sistema.logica.valueObjects;

import java.util.*;
import sistema.logica.inscripciones.Inscripcion;
import java.io.Serializable;

public class VOAlumnos implements Serializable {
	
	private ArrayList<VOAlumno> voalumnos;

	//Constructor.
	public VOAlumnos()
	{	
		voalumnos = new ArrayList<VOAlumno>(); 
	}
	
	public void insert(VOAlumno a) 
	{
		voalumnos.add(a);
	}
	
	public ArrayList<VOAlumno> getVOAlumnosArray()
	{
		return voalumnos;
	}
	
	public boolean esVacia() {
		return voalumnos.isEmpty();
	}
	
	public void setArrayAlumnos(ArrayList<VOAlumno> voal)
	{
		this.voalumnos=voal;
	}
}
