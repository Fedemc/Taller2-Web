package sistema.web.servlets;

import sistema.logica.ICapaLogica;
import sistema.grafica.controladores.ContSingleton;
import javax.servlet.http.HttpServlet;

public class IngresoWebServlet extends HttpServlet
{
	private ICapaLogica interfazFachada=ContSingleton.getInstancia().getInterfazFachada();
	
}
