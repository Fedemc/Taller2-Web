package sistema.logica.valueObjects;

import java.io.Serializable;

public class VOAlumnoDetallado extends VOAlumno implements Serializable{

	private String domicilio;
	private int telefono;
	private String dirCorreo;
	private Float cuotaMensual;
	
	public VOAlumnoDetallado() {
		super();
		this.domicilio = ""; 
		this.telefono = 0;
		this.dirCorreo = "";
		this.cuotaMensual = (float) 0.0;
	}
	
	public VOAlumnoDetallado(Long cedula, String nombre, String apellido, String domicilio, int telefono, String dirCorreo, Float cuotaMensual) {
		super(cedula,nombre,apellido);
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.dirCorreo = dirCorreo;
		this.cuotaMensual = cuotaMensual;
	}

	//Getters
	public String getDomicilio() {
		return domicilio;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getDirCorreo() {
		return dirCorreo;
	}
	
	public Float getCuotaMensual( ) {
		return cuotaMensual;
	}
	
	public void setDomicilio(String dom)
	{
		this.domicilio=dom;
	}
	
	public void setTelefono(int tel)
	{
		this.telefono=tel;
	}
	
	public void setDirCorreo(String dirCo)
	{
		this.dirCorreo=dirCo;
	}
	
	public void setCuotaMensual(Float cuota)
	{
		this.cuotaMensual=cuota;
	}

	@Override
	public String toString() {
		return super.toString() + "\nDomicilio: " + domicilio + "\n Telefono: " + telefono + "\nCorreo electronico:" + dirCorreo;
	}
	
	
	
}
