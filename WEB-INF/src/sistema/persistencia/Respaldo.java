package sistema.persistencia;

import java.io.*;
import sistema.logica.asignaturas.*;
import sistema.logica.alumnos.*;
import sistema.logica.inscripciones.*;
import sistema.excepciones.PersistenciaException;

public class Respaldo 
{
	public void respaldarAsignaturas(String nomArch, Asignaturas asigns) throws PersistenciaException
	{
		try
		{
			//Buscar archivo
			//Abrirlo
			FileOutputStream f= new FileOutputStream(nomArch);
			ObjectOutputStream o= new ObjectOutputStream(f);
			
			//Guardar coleccion en archivo
			o.writeObject(asigns);
			o.close();
			f.close();
			System.out.println("Asignaturas respaldadas");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new PersistenciaException("Error al respaldar asignaturas");
		}
	}
	
	public Asignaturas recuperarAsignaturas(String nomArch) throws PersistenciaException
	{
		try 
		{
			//Abrir archivo
			FileInputStream f= new FileInputStream(nomArch);
			ObjectInputStream o= new ObjectInputStream(f);
			
			//Leer coleccion de archivo
			Asignaturas asigns= (Asignaturas) o.readObject();
			o.close();
			f.close();
			System.out.println("Asignaturas recuperadas");
			
			//Devolver coleccion
			return asigns;
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new PersistenciaException("Error al restaurar asignaturas");
		}
	}
	
	public void respaldarAlumnos(String nomArch, Alumnos alus) throws PersistenciaException
	{
		try
		{
			//Buscar archivo
			//Abrirlo
			FileOutputStream f= new FileOutputStream(nomArch);
			ObjectOutputStream o= new ObjectOutputStream(f);
			
			//Guardar coleccion en archivo
			o.writeObject(alus);
			o.close();
			f.close();
			System.out.println("Alumnos respaldado");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new PersistenciaException("Error al respaldar alumnos");
		}
	}
	
	public Alumnos recuperarAlumnos(String nomArch) throws PersistenciaException
	{
		try 
		{
			//Abrir archivo
			FileInputStream f= new FileInputStream(nomArch);
			ObjectInputStream o= new ObjectInputStream(f);
			
			//Leer coleccion de archivo
			Alumnos alus= (Alumnos) o.readObject();
			o.close();
			f.close();
			System.out.println("Alumnos recuperados");
			
			//Devolver coleccion
			return alus;
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new PersistenciaException("Error al restaurar alumnos");
		}
	}

}
