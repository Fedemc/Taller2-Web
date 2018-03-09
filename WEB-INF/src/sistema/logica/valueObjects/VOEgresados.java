package sistema.logica.valueObjects;

import java.util.ArrayList;
import java.io.Serializable;

public class VOEgresados implements Serializable {
	
	private ArrayList<VOEgresadoPromedioCal> voEgresados;

	//Constructor.
	public VOEgresados()
	{	 
		voEgresados = new ArrayList<VOEgresadoPromedioCal>(); 
	}
	
	public void insert(VOEgresadoPromedioCal e) 
	{
		voEgresados.add(e);
	}
	
	public ArrayList<VOEgresadoPromedioCal> getVOEgresadosArray()
	{
		return voEgresados;
	}
	
	public boolean esVacia() {
		return voEgresados.isEmpty();
	}

}
