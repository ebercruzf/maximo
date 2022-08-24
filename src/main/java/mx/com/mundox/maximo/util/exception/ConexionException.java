package mx.com.mundox.maximo.util.exception;

public class ConexionException extends Exception{


	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7556215179402897879L;
	
	private final Object[] params;

	    public ConexionException(String mensaje) {
	        super(mensaje);
	        params = new Object[0];
	    }

	    public ConexionException(String mensaje, Throwable t) {
	        super(mensaje, t);
	        params = new Object[0];
	    }

	    public ConexionException(String mensaje, Throwable t, Object... params) {
	        super(mensaje, t);
	        this.params = params;
	    }

	    public Object[] getParams() {
	        return params;
	    }
}
