package mx.com.mundox.maximo.util.exception;

public class GACCESWSException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5152258309855794287L;

	private int codigoE;
    private String mensaje;
    
    
    public GACCESWSException( int codigoE, String mensajeGACCESWS)
    {
        super(mensajeGACCESWS);
        this.codigoE = codigoE;
        this.mensaje = mensajeGACCESWS;
    }
    
	public int getCodigoE() {
		return codigoE;
	}
	public void setCodigoE(int codigoE) {
		this.codigoE = codigoE;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
    
    

}
