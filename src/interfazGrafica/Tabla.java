package interfazGrafica;

import clases.*;
import javax.swing.table.DefaultTableModel;

public class Tabla {
	
	// Tabla buses
	
	public static void agregarFilaBus(DefaultTableModel model, Bus nuevoBus) {
		model.addRow(new Object[] {nuevoBus.getPatente(), nuevoBus.getServicio(), "$" + nuevoBus.getValor(), nuevoBus.getCapacidad(), "SI"});
	}
	
	// Tabla clientes
	
	public static void agregarFilaCliente(DefaultTableModel model, Cliente nuevoCliente) {
		model.addRow(new Object[] {nuevoCliente.getNombre(), nuevoCliente.getRut(), nuevoCliente.getEdad(), nuevoCliente.getTelefono(), "NO", "0", "N/A"});
	}
	
	// Tabla pasajeros
	
	public static void agregarFilaPasajero(DefaultTableModel model, Pasajero nuevoPasajero, int numeroAsiento) {
		model.addRow(new Object[] {nuevoPasajero.getNombre(), nuevoPasajero.getRut(), numeroAsiento});
	}
	
	// Tabla viajes
	
	public static void agregarFilaViaje(DefaultTableModel model, Viaje nuevoViaje) {
		model.addRow(new Object[] {nuevoViaje.getId(), nuevoViaje.getOrigen(), nuevoViaje.getDestino(), 
									nuevoViaje.getHoraPartida(), nuevoViaje.getHoraLlegada(), 
									"$" + nuevoViaje.getValor(), "NO", "0/0"});
	}
	
	// Para actualizar una fila de una tabla
	
	public static void actualizarFila(DefaultTableModel model, String nuevoDato, int row, int column) {
		model.setValueAt(nuevoDato, row, column);
	}
}
