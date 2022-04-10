package clases;

public class Bus implements IPromocionable {
	private String patente;
	private String servicio;
	private int valor;
	private int capacidad;
	private boolean disponibilidad;
	
	// Constructor
	
	public Bus(String patente, String servicio) {
		this.patente = patente;
		this.servicio = servicio;
		// Cada servicio tiene una capacidad y un valor distinto
		if(servicio.equals("CLASICO")) {
			valor = 1000;
			capacidad = 40;
		}
		else if(servicio.equals("PREMIUM")) {
			valor = 3000;
			capacidad = 20;
		}
		disponibilidad = true;
	}
	
	// Getters / Setters

	public String getPatente() {
		return patente;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int precio) {
		this.valor = precio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public boolean getDisponibilidad() {
		return disponibilidad;
	}
	
	// Metodos de la logica del negocio
	
	// Cambia la disponibilidad del bus a 'true', lo que significa que el bus esta disponible para realizar un viaje (que pueda ser asignado)
	public void setDisponibilidadTrue() {
		disponibilidad = true;
	}
	
	// Cambia la disponibilidad del bus a 'false'
	public void setDisponibilidadFalse() {
		disponibilidad = false;
	}
	
	public int promocion() {
		int nuevoValor = (int) (valor * 0.9);
		this.setValor(nuevoValor);
		return nuevoValor;
	}
}
