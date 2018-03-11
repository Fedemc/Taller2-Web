package sistema.web.servlets;

import sistema.grafica.controladores.ContSingleton;
import sistema.logica.CapaLogica;
import sistema.logica.ICapaLogica;
import sistema.servidor.MainServidor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;


public class PrincipalServlet extends HttpServlet
{
	private CapaLogica fachada;
	private ICapaLogica iFachada;
	private static final long serialVersionUID = 1L;

	
	public void init ()
	{
		fachada=MainServidor.fachada;
		iFachada=ContSingleton.getInstancia().getInterfazFachada();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//Obtengo los datos de la cedula desde el campo del jsp
		String cedTexto=req.getParameter("cedula");	//Lo guardamos como String primero para verificar que no sea nulo, despues lo parsearemos a Long
		String opcion=req.getParameter("consulta");
		long ced=Long.valueOf(cedTexto);
		
		boolean error=false;
		String msjError=new String();
		
		if((cedTexto == null) || (cedTexto.trim().equals("")))	//Verifico que hayan ingresado algo en el campo de la cedula
		{
			error=true;
			msjError="Debe ingresar una cedula.";
		}
		else
		{
			//Parseo la cedula a Long y verifico si existe en el sistema
			//ced=Long.parseLong(cedTexto);

			if(fachada == null)
			{
				error=true;
				msjError="No hay fachada.";
			}
			else
			{
				if(fachada.existeAlumno(ced) == false)
				{
					error=true;
					msjError="No existe un alumno con esa cedula en el sistema.";
				}
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
