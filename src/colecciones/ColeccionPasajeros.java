package colecciones;

import clases.Pasajero;

public class ColeccionPasajeros {
	private Pasajero[] pasajeros;
	
	public ColeccionPasajeros(int capacidad) {
		pasajeros = new Pasajero[capacidad];
	}
	
	public void agregar(Pasajero p, int numeroAsiento) {
		pasajeros[numeroAsiento] = p;
	}
	
	public Pasajero buscar(String rut) {
		for(int i = 0; i < pasajeros.length; i++) {
			if(pasajeros[i] != null && pasajeros[i].getRut().equals(rut))
				return pasajeros[i];
		}
		return null;
	}
	
	public Pasajero obtener(int numeroAsiento) {
		return pasajeros[numeroAsiento];
	}
	
	public void editarNombre(String rut, String nuevoNombre) {
		for(int i = 0; i < pasajeros.length; i++) {
			if(pasajeros[i] != null && pasajeros[i].getRut().equals(rut))
				pasajeros[i].setNombre(nuevoNombre);
		}
	}
	
	public void editarRut(String rut, String nuevoRut) {
		for(int i = 0; i < pasajeros.length; i++) {
			if(pasajeros[i] != null && pasajeros[i].getRut().equals(rut))
				pasajeros[i].setRut(nuevoRut);
		}
	}
	
	// Sobrecarga de metodos: Este metodo elimina un pasajero de un numero de asiento
	public boolean eliminar(int numeroAsiento) {
		if(pasajeros[numeroAsiento] != null) {
			pasajeros[numeroAsiento] = null;
			return true;
		}
		else return false;
	}
	
	// Sobrecarga de metodos: Este metodo elimina un pasajero a partir de un rut
	public boolean eliminar(String rut) {
		for(int i = 0; i < pasajeros.length; i++) {
			if(pasajeros[i].getRut().equals(rut)) {
				pasajeros[i] = null;
				return true;
			}
		}
		return false;
	}
	
	public int cantidadDePasajeros() {
		int cont = 0;
		for(int i = 0; i < pasajeros.length; i++) {
			if(pasajeros[i] != null)
				cont++;
		}
		return cont;
	}
	
	public int tamaño() {
		return pasajeros.length;
	}
}
