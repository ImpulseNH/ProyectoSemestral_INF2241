package clases;

// Cliente que ademas de estar registrado en el sistema esta suscrito al plan
// A diferencia del Cliente Normal, este podra acumular puntos y estos seran usados como descuento
public class ClienteSuscrito extends Cliente implements IPromocionable {
	private int puntosAcumulados;
	
	// Constructor
	
	public ClienteSuscrito(String nombre, String rut, int edad, int telefono) {
		super(nombre, rut, edad, telefono);
		puntosAcumulados = 0;
	}
	
	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}
	
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}
	
	// Metodos de la logica del negocio
	
	public int descuento() {
		int descuento = puntosAcumulados;
		// Todos los puntos se usaran como dinero al momento de aplicar el descuento, por lo que al usarlos volveran a 0
		puntosAcumulados = 0;
		return descuento;
	}
	
	public int descuentoEspecial() {
		return puntosAcumulados * 100;
	}
	
	public int promocion() {
		int nuevosPuntosAcumulados;
		if(puntosAcumulados == 0) {
			puntosAcumulados = 100;
			nuevosPuntosAcumulados = puntosAcumulados;
			return nuevosPuntosAcumulados;
		}
		else {
			nuevosPuntosAcumulados = (int) (puntosAcumulados * 1.5);
			this.setPuntosAcumulados(nuevosPuntosAcumulados);
			return nuevosPuntosAcumulados;
		}
	}
}
