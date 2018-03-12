package sistema.web.servlets;

import sistema.logica.CapaLogica;
import sistema.logica.alumnos.Alumnos;
import sistema.logica.ICapaLogica;
import sistema.servidor.MainServidor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;


public class PrincipalServlet extends HttpServlet
{
	private CapaLogica fachada;
	private ICapaLogica iFachada;
	private static final long serialVersionUID = 1L;

	
	public void init()
	{
		
		try
		{
			//Intento conectarme
			
			//String ip=super.getInitParameter("ipServidor");
			String ip="127.0.0.1";
			//String puerto=super.getInitParameter("puertoServidor");
			String puerto="1099";
			String ruta="//"+ip+":"+puerto+"/fachada";
			
			//Voy a buscar el objeto remoto
			iFachada = (ICapaLogica) Naming.lookup(ruta);
		}
		catch(MalformedURLException mEx)
		{
			mEx.printStackTrace();			
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();			
		}
		catch(NotBoundException nobEx)
		{
			nobEx.printStackTrace();
		}
		
		//fachada=CapaLogica.getInstancia();
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		//Obtengo los datos de la cedula desde el campo del jsp
		String cedTexto=req.getParameter("cedula");	//Lo guardamos como String primero para verificar que no sea nulo, despues lo parsearemos a Long
		String opcion=req.getParameter("consulta");
		long ced=Long.valueOf(cedTexto);
		
		boolean error=false;
		String msjError=new String();
		
		//Alumnos diccAls=iFachada.getAlumnos() falta agregar el metodo en la Interfaz
		
		ServletContext contexto=super.getServletContext();
		synchronized(contexto)
		{
			//contexto.setAttribute("diccAlumnos", diccAls);
		}

		if((cedTexto == null) || (cedTexto.trim().equals("")))	//Verifico que hayan ingresado algo en el campo de la cedula
		{
			error=true;
			msjError="Debe ingresar una cedula.";
		}
		else
		{
			//Parseo la cedula a Long y verifico si existe en el sistema
			//ced=Long.parseLong(cedTexto);
			
			/*
			if(fachada == null)
			{
				error=true;
				msjError="Hay: " + Integer.toString(fachada.cantElemAlumnos()) + " alumnos.";
			}
			else
			{
				error=true;
				msjError="Hay fachada";
				boolean existe=false;
				try
				{
					existe=fachada.existeAlumno(ced);
					if(!existe)
					{
						error=true;
						msjError="No existe un alumno con esa cedula en el sistema.";
					}
				}
				catch(NullPointerException nullEx)
				{
					msjError=nullEx.toString();
				}
			}
			*/
			if(!iFachada.existeAlumno(ced))
			{
				error=true;
				msjError="No existe un alumno con esa cedula en el sistema.";
			}
		}
		
		if(!error)
		{
			//Guardo la ced en la sesion
			HttpSession session=req.getSession();
			synchronized (session)
			{
				session.setAttribute("cedula", ced);
				session.setAttribute("consulta", opcion);
			}
		}
			
		
		//Voy a la pagina que corresponda
		req.setAttribute("mensajeError", msjError);	//Seteo el mensaje de error para pasarselo al jsp de Error
		RequestDispatcher rd;
		if(!error)
		{
			if(opcion == "Escolaridad")
			{
				rd=req.getRequestDispatcher("Escolaridad.jsp");
			}
			else
			{
				rd=req.getRequestDispatcher("ListadoEgresado.jsp");
			}
		}
		else
		{
			rd=req.getRequestDispatcher("Error.jsp");
		}
		
		rd.forward(req, resp);
	}
	
}
