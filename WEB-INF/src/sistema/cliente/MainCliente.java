package sistema.cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import sistema.logica.ICapaLogica;
import sistema.logica.valueObjects.*;
import sistema.excepciones.*;

import  sistema.grafica.ventanas.*;
import sistema.grafica.controladores.ContSingleton;

public class MainCliente
{
	private ICapaLogica interfazFachada;
	private ContSingleton singleton;
	
	public static void main(String[] args)
	{
				
			//Llamar ventana
			VentanaPrincipal vPrinc = new VentanaPrincipal();
			vPrinc.setVisible(true);
					
	}
}
