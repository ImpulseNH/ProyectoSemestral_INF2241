package clases;

public abstract class Cliente extends Pasajero {
	private int edad;
	private int telefono;
	
	// Constructor
	
	public Cliente(String nombre, String rut, int edad, int telefono) {
		super(nombre, rut);
		this.edad = edad;
		this.telefono = telefono;
	}

	// Getters / Setters
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	// Metodos de la logica del negocio
	
	// Descuento que se aplicara de forma especial segun estime la empresa
	public abstract int descuentoEspecial();
}
