package sistema.excepciones;

public class PersistenciaException extends Exception
{
	private String mensaje;
	
	public PersistenciaException(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
