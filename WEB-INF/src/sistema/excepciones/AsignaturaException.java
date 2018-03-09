package sistema.excepciones;

public class AsignaturaException extends Exception
{
	private String mensaje;
	
	public AsignaturaException(String msj)
	{
		this.mensaje=msj;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
