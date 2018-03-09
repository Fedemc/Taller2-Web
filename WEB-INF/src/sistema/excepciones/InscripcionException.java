package sistema.excepciones;

public class InscripcionException extends Exception
{
	private String mensaje;
	
	public InscripcionException(String msj)
	{
		this.mensaje=msj;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}

}
