package colecciones;

import clases.Viaje;
import java.util.ArrayList;

public class ColeccionViajes {
	private ArrayList<Viaje> viajes;

	public ColeccionViajes() {
		viajes = new ArrayList<Viaje>();
	}
	
	public void agregar(Viaje v) {
		viajes.add(v);
	}
	
	public Viaje buscar(int id) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id)
				return viajes.get(i);
		}
		return null;
	}
	
	public Viaje obtener(int posicion) {
		return viajes.get(posicion);
	}
	
	public void editarOrigen(int id, String nuevoOrigen) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id)
				viajes.get(i).setOrigen(nuevoOrigen);
		}
	}
	
	public void editarDestino(int id, String nuevoDestino) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id)
				viajes.get(i).setDestino(nuevoDestino);
		}
	}
	
	public void editarHoraPartida(int id, String nuevaHoraPartida) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id)
				viajes.get(i).setHoraPartida(nuevaHoraPartida);
		}
	}
	
	public void editarHoraLlegada(int id, String nuevaHoraLlegada) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id) 
				viajes.get(i).setHoraLlegada(nuevaHoraLlegada);
		}
	}
	
	public void editarValor(int id, int nuevoPrecio) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id)
				viajes.get(i).setValor(nuevoPrecio);
		}
	}
	
	public boolean eliminar(int id) {
		for(int i = 0; i < viajes.size(); i++) {
			if(viajes.get(i).getId() == id) {
				viajes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int tamaño() {
		return viajes.size();
	}
}
