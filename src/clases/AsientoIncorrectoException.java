package clases;

public class AsientoIncorrectoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AsientoIncorrectoException(int maximo) {
		super("Debe ingresar un asiento entre 1 y " + maximo);
	}
}
