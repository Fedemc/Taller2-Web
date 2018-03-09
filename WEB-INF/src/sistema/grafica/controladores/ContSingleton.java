package sistema.grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Properties;

import javax.swing.JOptionPane;

import sistema.logica.ICapaLogica;

public class ContSingleton
{
	private static ContSingleton instancia;
	private ICapaLogica interfazFachada;
	
	private ContSingleton() 
	{
		try
		{
			//Intento conectarme
			Properties p=new Properties();
			String nomArch="config/config.properties";
			p.load(new FileInputStream(nomArch));
			String ip=p.getProperty("ipServidor");
			String puerto=p.getProperty("puertoServidor");
			String ruta="//"+ip+":"+puerto+"/fachada";
			
			//Voy a buscar el objeto remoto
			interfazFachada = (ICapaLogica) Naming.lookup(ruta);
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
	}
	
	public static ContSingleton getInstancia()
	{
		if(instancia==null)
			instancia= new ContSingleton();

		return instancia;	
	}	
	
	public ICapaLogica getInterfazFachada()
	{
		return interfazFachada;
	}
	
}
