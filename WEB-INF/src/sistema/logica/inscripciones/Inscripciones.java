package sistema.logica.inscripciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import sistema.logica.valueObjects.VOInscripcion;
import sistema.logica.valueObjects.VOInscripcionDetallada;
import sistema.logica.valueObjects.VOInscripciones;
import java.io.Serializable;
import sistema.excepciones.InscripcionException;

public class Inscripciones implements Serializable{

	private ArrayList<Inscripcion> listaInscripciones;
	
	
	//Constructor simple Inscripciones 
	public Inscripciones()
	{
		listaInscripciones= new ArrayList<Inscripcion>();
	}
	
	//Getter
	public ArrayList<Inscripcion> getListaInscripciones() {
		return listaInscripciones;
	}

	//Inserta una inscripcion en la lista
	public void insert(Inscripcion i) 
	{
		int nroIns = listaInscripciones.size() + 1;
		i.setNroInscripcion(nroIns);
		listaInscripciones.add(i);
	}
	
	public boolean member(int nroIns)
	{
		boolean existe=false;
		int i=0;
		while(!existe && i<listaInscripciones.size())
		{
			if(nroIns==listaInscripciones.get(i).getNroInscripcion())
				existe=true;
			else
				i++;
		}
		return existe;
	}
	
	//Crear Iterador
	public Iterator crearIterador() {
		Iterator it = listaInscripciones.iterator();
		return it;
	}
	
	//Precondicion: Existe la inscripcion con ese nro de inscripcion en la lista
	public Inscripcion find(int nroIns)
	{
		return listaInscripciones.get(nroIns-1);
	}
	
	public boolean esVacia() {
		return listaInscripciones.isEmpty();
	}
	
	//Calculo el monto total del costo de las inscripciones que estan activas (nota == 0)
	public float calcularCuotasAlumno()
	{
		float cuotaTotal=0;
		for(int i=0;i<listaInscripciones.size();i++)
		{
			Inscripcion auxIns=listaInscripciones.get(i);
			if(auxIns.getCalificacion() == 0)
				cuotaTotal=cuotaTotal + (auxIns.getMontoBase()/10);
		}

		return cuotaTotal;
	}
	
	public float calcularPromedioTotal()
	{
		float promedioTotal=0;
		for(int i=0;i<listaInscripciones.size();i++)
		{
			Inscripcion auxIns=listaInscripciones.get(i);
			promedioTotal = promedioTotal + (auxIns.getCalificacion());
		}
		promedioTotal = promedioTotal / listaInscripciones.size();
		return promedioTotal;
	}
	
	public float calcularPromedioAprob()
	{
		float promedioAprob=0;
		for(int i=0;i<listaInscripciones.size();i++)
		{
			Inscripcion auxIns=listaInscripciones.get(i);
			if(auxIns.getCalificacion() != 0)
				promedioAprob = promedioAprob + (auxIns.getCalificacion());
		}
		promedioAprob = promedioAprob / 10;
		return promedioAprob;
	}
	
	public VOInscripciones escolaridadParcial() {
		VOInscripciones vois = new VOInscripciones();
		for(int i=0;i<listaInscripciones.size();i++) {
			Inscripcion insAux = listaInscripciones.get(i);
			if (insAux.getCalificacion() != 0) {
				VOInscripcion voi = new VOInscripcion(insAux.getNroInscripcion(),insAux.getAsignatura().getNombre(),insAux.getAnioLectivo(),insAux.getCalificacion());
				vois.insert(voi);
			}
		}
		return vois;
	}
	
	public VOInscripciones escolaridadCompleta() {
		VOInscripciones vois = new VOInscripciones();
		for(int i=0;i<listaInscripciones.size();i++) {
			Inscripcion insAux = listaInscripciones.get(i);
			VOInscripcionDetallada voidet = new VOInscripcionDetallada(insAux.getNroInscripcion(),insAux.getAsignatura().getNombre(),insAux.getAnioLectivo(),insAux.getCalificacion(),insAux.getMontoBase());
			vois.insert(voidet);
			}
		return vois;
	}
	
	//verificar si hay una inscripcion con el codigo de la asignatura
	public boolean memberAsig(String codAsig) throws InscripcionException
	{
		boolean existe=true;
		int i=0;
		
		//System.out.println("Llegue al while!");
		while(existe && i<listaInscripciones.size())
		{
			if(listaInscripciones.get(i).getAsignatura().getCodigo().equals(codAsig)) 
			{
				//System.out.println("Llegue al asigs iguales");
				//Si existe la misma asignatura me fijo si ya se aprobó
				if(listaInscripciones.get(i).getCalificacion()>5)
				{
					existe = false;
					String msj=("El alumno ya aprobo la materia");
					throw new InscripcionException(msj);
				}
				else	//Si no se aprobó me fijo si no tiene calif asignada y no se esta cursando en el mismo año 
				{
					//System.out.println("Llegue al else");
					if(listaInscripciones.get(i).getAnioLectivo() == Calendar.getInstance().get(Calendar.YEAR)) 
					{
						existe = false;
						String msj=("El alumno ya se inscribio a la materia en el año lectivo");
						throw new InscripcionException(msj);
					}
				}		
			}
			else
				i++;
		}
		return existe;
	}
	
}
