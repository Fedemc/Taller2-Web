package sistema.logica.alumnos;

import java.util.*;

import sistema.logica.alumnos.Alumno;
import sistema.logica.valueObjects.*;
import java.io.Serializable;

public class Alumnos implements Serializable{
	
	private TreeMap<Long,Alumno> arbol;
	
	//Constructor
	public Alumnos() {
		arbol = new TreeMap<Long,Alumno>(); 
	}
		
	public boolean member(Long clave) {
		return arbol.containsKey(clave);
	}
	
	public void insert(Alumno a) {
		arbol.put(a.getCedula(),a);
	}
	
	public Alumno find(Long clave) {
		return arbol.get(clave);
	}
	
	//Devolver cantidad de elementos en el árbol.
	public int getCantidadElementos() {
		return arbol.size();
	}
	
	//Crear Iterador
	public Iterator crearIterador() {
		Iterator it = arbol.keySet().iterator();
		return it;
	}
	
	public VOAlumnos ListadoAlumnosApe(String ape) {
		Iterator it = arbol.keySet().iterator();	//Me genero un iterador con el set de cedulas del treemap
		VOAlumnos vOAlumnos = new VOAlumnos();
		while (it.hasNext()) {
			Long clave = (Long) it.next();	//El iterador solo tiene cedulas, casteo como long para traerme las cedulas
			Alumno tempAlu = arbol.get(clave);	//me genero un alumno temporal para almacenar los datos del que estoy leyendo con el iterador
			if (tempAlu.getApellido().startsWith(ape)) {	//comparo si el apellido del alumno empieza con el string que se ingresó por parametro
				//Convertir a VOAlumno y almacenar
				VOAlumno voa = new VOAlumno(tempAlu.getCedula(),tempAlu.getNombre(),tempAlu.getApellido());
				//Evaluar que tipo de alumno es para desplegar si es becado o alumno comun
				if(arbol.get(clave) instanceof Becado)
				{
					voa.setTipoAlumno("Becado");
				}
				else
				{
					voa.setTipoAlumno("Comun");
				}
				vOAlumnos.insert(voa);
			}
		}
		return vOAlumnos;
	}
	
	public VOAlumnoDetallado ListadoAlumnoCedulaCom(Long clave) {
		Alumno tempAlu = (Alumno) arbol.get(clave);
		VOAlumnoDetallado voad = new VOAlumnoDetallado(tempAlu.getCedula(),tempAlu.getNombre(),tempAlu.getApellido(),tempAlu.getDomicilio(),
					tempAlu.getTelefono(),tempAlu.getEmail(),tempAlu.calcularCuotaAlumno());
		voad.setTipoAlumno("Alumno");
		return voad;
	}
	
	public VOBecadoDetallado ListadoAlumnoCedulaBec(Long clave) {
		Becado tempBec = (Becado) arbol.get(clave);
		VOBecadoDetallado vobd = new VOBecadoDetallado(tempBec.getCedula(),tempBec.getNombre(),tempBec.getApellido(),tempBec.getDomicilio(),
					tempBec.getTelefono(),tempBec.getEmail(),tempBec.calcularCuotaAlumno(),tempBec.getPorcentaje(), tempBec.getDescripcionBeca());
		vobd.setTipoAlumno("Becado");
		return vobd;
	}
	
	public VOAlumnos listadoEgresadosParcial() {
		VOAlumnos voas = new VOAlumnos();
		Iterator it = arbol.keySet().iterator();
		while (it.hasNext()) {
			Long clave = (Long) it.next();
			Alumno tempAlu = arbol.get(clave);
			if (tempAlu.getCantAprobaciones() == 10) {
				VOAlumno voa = new VOAlumno(clave,tempAlu.getNombre(),tempAlu.getApellido());
				voas.insert(voa);
			}
		}
		return voas;
	}
	
	public VOEgresados listadoEgresadosCompleto() {
		VOEgresados voegs = new VOEgresados();
		Iterator it = arbol.keySet().iterator();
		while (it.hasNext()) {
			Long clave = (Long) it.next();
			Alumno tempAlu = arbol.get(clave);
			if (tempAlu.getCantAprobaciones() == 10) {
				float pt = tempAlu.devolverPromedioTotalAlumno();
				float pa = tempAlu.devolverPromedioAprobAlumno();
				VOEgresadoPromedioCal voeg = new VOEgresadoPromedioCal(clave,tempAlu.getNombre(),tempAlu.getApellido(),pt,pa);
				voegs.insert(voeg);
			}
		}
		return voegs;
	}
	
}
