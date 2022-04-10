package clases;

public class CantidadIncorrectaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CantidadIncorrectaException() {
		super("La patente debe tener 6 carácteres");
	}
}
