package sistema.logica.valueObjects;

import java.util.ArrayList;
import java.io.Serializable;

public class VOAsignaturas implements Serializable {

	private ArrayList<VOAsignatura> voasignaturas;

	//Constructor.
	public VOAsignaturas()
	{	 
		voasignaturas = new ArrayList<VOAsignatura>(); 
	}
	
	public void insert(VOAsignatura a) 
	{
		voasignaturas.add(a);
	}
	
	public ArrayList<VOAsignatura> getVOAsignaturasArray()
	{
		return voasignaturas;
	}
	
	public int getSize() {
		return voasignaturas.size();
	}
	
}
