package colecciones;

import clases.Bus;
import java.util.ArrayList;

public class ColeccionBuses {
	private ArrayList<Bus> buses;
	
	public ColeccionBuses() {
		buses = new ArrayList<Bus>();
	}
	
	public void agregar(Bus b) {
		buses.add(b);
	}
	
	public Bus buscar(String patente) {
		for(int i = 0; i < buses.size(); i++) {
			if(patente.equals(buses.get(i).getPatente()))
				return buses.get(i);
		}
		return null;
	}
	
	public Bus obtener(int posicion) {
		return buses.get(posicion);
	}
	
	// Sobrecarga de metodos: Este metodo edita la patente de un bus
	public void editar(String patente, String nuevaPatente) {
		for(int i = 0; i < buses.size(); i++) {
			if(buses.get(i).getPatente().equals(patente))
				buses.get(i).setPatente(nuevaPatente);
		}
	}
	
	public void editarServicio(String patente, String nuevoServicio) {
		for(int i = 0; i < buses.size(); i++) {
			if(buses.get(i).getPatente().equals(patente))
				buses.get(i).setServicio(nuevoServicio);
		}
	}
	
	// Sobrecarga de metodos: Este metodo edita el valor de un bus
	public void editar(String patente, int nuevoPrecio) {
		for(int i = 0; i < buses.size(); i++) {
			if(buses.get(i).getPatente().equals(patente))
				buses.get(i).setValor(nuevoPrecio);
		}
	}
	
	public boolean eliminar(String patente) {
		for(int i = 0; i < buses.size(); i++) {
			if(patente.equals(buses.get(i).getPatente())) {
				buses.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int tamaño() {
		return buses.size();
	}
}
