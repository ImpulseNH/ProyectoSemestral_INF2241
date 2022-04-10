package clases;

public class Pasajero {
	private String nombre;
	private String rut;
	
	// Constructor
	
	public Pasajero(String nombre, String rut) {
		this.nombre = nombre;
		this.rut = rut;
	}

	// Getters / Setters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	// Metodos de la logica del negocio
	
	public int descuento() {
		return 0;
	}
}
