package clases;

// Cliente que solamente es parte del sistema (esta registrado)
// Podra optar a descuentos en viajes dependiendo de la cantidad de viajes que haya realizado
public class ClienteNormal extends Cliente {
	private int viajesRealizados;
	
	// Constructor
	
	public ClienteNormal(String nombre, String rut, int edad, int telefono) {
		super(nombre, rut, edad, telefono);
		viajesRealizados = 0;
	}

	// Getters / Setters

	public int getViajesRealizados() {
		return viajesRealizados;
	}
	
	public void setViajesRealizados(int viajesRealizados) {
		this.viajesRealizados = viajesRealizados;
	}
	
	// Metodos de la logica del negocio
	
	public int descuento() {
		if(viajesRealizados < 10)
			return 0;
		else if(viajesRealizados < 25)
			return viajesRealizados * 5;
		else if(viajesRealizados < 50)
			return viajesRealizados * 10;
		else
			// Cuando los viajes realizados sean mas de 50
			return viajesRealizados * 15;
	}
	
	public int descuentoEspecial() {
		return viajesRealizados * 100;
	}
}
