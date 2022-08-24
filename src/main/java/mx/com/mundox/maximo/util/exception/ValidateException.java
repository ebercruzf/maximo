package mx.com.mundox.maximo.util.exception;

public class ValidateException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8587126513780337465L;
	
	private int codE;
    private String mensaje;
    private String observacion;

    public ValidateException(int codE, String mensaje, String observacion) {
        this.codE = codE;
        this.mensaje = mensaje;
        this.observacion = observacion;
    }

    public ValidateException(int codE, String mensaje, String observacion, Throwable cause) {
        super(cause);
        this.codE = codE;
        this.mensaje = mensaje;
        this.observacion = observacion;
    }

	public int getCodE() {
		return codE;
	}

	public void setCodE(int codE) {
		this.codE = codE;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public String toString() {
		return "ValidateException [codE=" + codE + ", mensaje=" + mensaje + ", observacion=" + observacion + "]";
	}
    
    

}
