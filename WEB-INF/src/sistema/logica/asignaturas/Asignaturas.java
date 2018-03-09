package sistema.logica.asignaturas;

import java.util.*;
import sistema.logica.valueObjects.VOAsignatura;
import sistema.logica.valueObjects.VOAsignaturas;
import java.io.Serializable;

public class Asignaturas implements Serializable{

	int TAM = 10;
	private int tope=0;
	private Asignatura arrAsignaturas[];
	
	public Asignaturas() 
	{
		arrAsignaturas= new Asignatura[TAM];
		tope=0; 
	}


	public int getTope() {
		return tope;
	}

	public void setTope(int tope) {
		this.tope = tope;
	}
	

	//Insertar una asignatura al array
	public void insertAsignatura(Asignatura a) {
		if(!memberAsignatura(a.getCodigo())) {
			arrAsignaturas[tope]=a;
			tope++;
		}
	}
	
	//Verificar si existe la asignatura en el array
	public boolean memberAsignatura(String s) {
		boolean existe =false;
		int largo=0;
		
		while(!existe && largo<getTope()) {
			if(arrAsignaturas[largo].getCodigo().equals(s)) {
				existe = true;
			}
			largo++;
		}
		return existe;
	}
	
	//Obtengo la asignatura solicitada
	public Asignatura findAsignatura(String s) {
		
		boolean encontre =false;
		int largo=0,pos=-1;
		
		while(!encontre) {
			if(arrAsignaturas[largo].getCodigo().equals(s)) {
				encontre = true;
				pos=largo;
			}
			largo++;
		}
		return arrAsignaturas[pos];
		
	}
	
	//Listado de asignaturas.
	public VOAsignaturas listadoAsignaturas () {
		VOAsignaturas vOAsignaturas = new VOAsignaturas();
		for(int i=0; i<getTope();i++) {
			VOAsignatura voas = new VOAsignatura(arrAsignaturas[i].getCodigo(), arrAsignaturas[i].getNombre(), arrAsignaturas[i].getDescripcion());
			vOAsignaturas.insert(voas);
		}
		return vOAsignaturas;
	}
	
	
}
