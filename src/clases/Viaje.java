package clases;

import java.text.Collator;
import java.util.ArrayList;
import colecciones.ColeccionPasajeros;

// Un viaje solo estara disponible para la venta de pasajes si es que tiene un bus asignado
// Para que un bus pueda ser asignado a un viaje, este debe estar disponible (se puede comprobar mediante un atributo booleano de la clase 'Bus')
public class Viaje implements IPromocionable {
	private int id;
	private String origen;
	private String destino;
	private String horaPartida;
	private String horaLlegada;
	private int valor;
	private Bus busAsignado;
	private ColeccionPasajeros pasajeros;
	
	// Constructor
	
	public Viaje(int id, String origen, String destino, String horaPartida, String horaLlegada, int precio) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.horaPartida = horaPartida;
		this.horaLlegada = horaLlegada;
		this.valor = precio;
		busAsignado = null; // Pensado para que el sistema asigne despues un bus a cada viaje,
							// los viajes estaran disponibles para los usuarios solo si estos tienen un bus asignado
		pasajeros = null;
	}
	
	// Getters / Setters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public int getValor() {
		return valor;
	}
	
	public void setValor(int precio) {
		this.valor = precio;
	}

	public Bus getBusAsignado() {
		return busAsignado;
	}

	public void setBusAsignado(Bus busAsignado) {
		this.busAsignado = busAsignado;
	}
	
	// Metodos de la coleccion de pasajeros

	public void agregarPasajero(Pasajero p, int numeroAsiento) {
		pasajeros.agregar(p, numeroAsiento);
	}
	
	public Pasajero buscarPasajero(String rut) {
		return pasajeros.buscar(rut);
	}
	
	public Pasajero obtenerPasajero(int numeroAsiento) {
		return pasajeros.obtener(numeroAsiento);
	}
	
	public void editarNombrePasajero(String rut, String nuevoNombre) {
		pasajeros.editarNombre(rut, nuevoNombre);
	}
	
	public void editarRutPasajero(String rut, String nuevoRut) {
		pasajeros.editarRut(rut, nuevoRut);
	}
	
	public boolean eliminarPasajeroPorAsiento(int numeroAsiento) {
		return pasajeros.eliminar(numeroAsiento);
	}
	
	public boolean eliminarPasajeroPorRut(String rut) {
		return pasajeros.eliminar(rut);
	}
	
	public int cantidadDePasajeros() {
		return pasajeros.cantidadDePasajeros();
	}
	
	// Metodos de la logica del negocio
	
	// Se asigna un bus a un viaje y se crea la coleccion de pasajeros segun la capacidad del bus
	public void asignarBus(Bus bus) {
		busAsignado = bus;
		pasajeros = new ColeccionPasajeros(bus.getCapacidad());
	}
	
	// Se elimina el bus y la coleccion de pasajeros (los pasajeros ya no existen en el viaje, en otras palabras, completaron el viaje)
	public void finalizarViaje() {
		busAsignado = null;
		pasajeros = null;
	}
	
	public int promocion() {
		int nuevoValor = (int) (valor * 0.8);
		this.setValor(nuevoValor);
		return nuevoValor;
	}
	
	// Retorna el pasajero con el rut mas antiguo
	public Pasajero pasajeroRutAntiguo() {
		Pasajero pasajeroActual = null;
		Collator comparador = Collator.getInstance();
		comparador.setStrength(Collator.PRIMARY);
		for(int i = 0; i < pasajeros.tamaño(); i++) {
			if(pasajeros.obtener(i) != null) {
				// Primer pasajero para comenzar a comparar
				if(pasajeroActual == null)
					pasajeroActual = pasajeros.obtener(i);
				// Si el rut del pasajero del segundo parametro es menor al rut del pasajero del primer parametro, se guarda el pasajero del segundo parametro
				if(comparador.compare(pasajeroActual.getRut(), pasajeros.obtener(i).getRut()) > 0)
					pasajeroActual = pasajeros.obtener(i);
			}
		}
		return pasajeroActual;
	}
	
	// Añade al ArrayList ingresado por parametro los pasajeros que esten en asientos impares
	public void pasajerosAsientosImpares(ArrayList<Pasajero> pasajerosFiltrados) {
		for(int i = 0; i < pasajeros.tamaño(); i++) {
			if(i % 2 == 0)
				if(pasajeros.obtener(i) != null)
					pasajerosFiltrados.add(pasajeros.obtener(i));
		}
	}
}
