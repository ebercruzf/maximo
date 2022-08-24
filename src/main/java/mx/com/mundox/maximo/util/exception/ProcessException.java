package mx.com.mundox.maximo.util.exception;

public class ProcessException extends Exception {

	public static final int GENERICO = 0;
	public static final int NO_EXISTE_DATO = 1;
	static final long serialVersionUID = 2L;
	private int tipo;

	public ProcessException() {
		this.tipo = 0;
	}

	public ProcessException(String mensaje) {
		this(mensaje, 0);
	}

	public ProcessException(String mensaje, Throwable e) {
		this(mensaje, 0, e);
	}

	public ProcessException(String mensaje, int tipo) {
		super(mensaje);
		this.tipo = tipo;
	}

	public ProcessException(String mensaje, int tipo, Throwable e) {
		super(mensaje, e);
		this.tipo = tipo;
	}

	public int getTipo() {
		return this.tipo;
	}
}
