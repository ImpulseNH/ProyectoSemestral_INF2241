package clases;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.text.Collator;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import colecciones.ColeccionBuses;
import colecciones.ColeccionViajes;
import colecciones.ColeccionClientes;

public class Empresa {
	private ColeccionViajes viajes;
	private int idActualViaje;
	private ColeccionBuses buses;
	private ColeccionClientes clientes;
	
	// Constructor
	
	public Empresa() {
		buses = new ColeccionBuses();
		viajes = new ColeccionViajes();
		idActualViaje = 0;
		clientes = new ColeccionClientes();
	}
	
	// Metodos de la coleccion de viajes
	
	public void agregarViaje(Viaje v) {
		viajes.agregar(v);
		idActualViaje++;
	}
	
	public Viaje buscarViaje(int id) {
		return viajes.buscar(id);
	}
	
	public Viaje obtenerViaje(int posicion) {
		return viajes.obtener(posicion);
	}
	
	public void editarOrigenViaje(int id, String nuevoOrigen) {
		viajes.editarOrigen(id, nuevoOrigen);
	}
	
	public void editarDestinoViaje(int id, String nuevoDestino) {
		viajes.editarDestino(id, nuevoDestino);
	}
	
	public void editarHoraPartidaViaje(int id, String nuevaHoraPartida) {
		viajes.editarHoraPartida(id, nuevaHoraPartida);
	}
	
	public void editarHoraLlegadaViaje(int id, String nuevaHoraLlegada) {
		viajes.editarHoraLlegada(id, nuevaHoraLlegada);
	}
	
	public void editarValorViaje(int id, int nuevoPrecio) {
		viajes.editarValor(id, nuevoPrecio);
	}
	
	public boolean eliminarViaje(int id) {
		return viajes.eliminar(id);
	}
	
	public int cantidadDeViajes() {
		return viajes.tamaño();
	}
	
	// Metodos de la coleccion de buses
	
	public void agregarBus(Bus b) {
		buses.agregar(b);
	}
	
	public Bus buscarBus(String patente) {
		return buses.buscar(patente);
	}
	
	public Bus obtenerBus(int posicion) {
		return buses.obtener(posicion);
	}
	
	public void editarPatenteBus(String patente, String nuevaPatente) {
		buses.editar(patente, nuevaPatente);
	}
	
	public void editarServicioBus(String patente, String nuevoServicio) {
		buses.editarServicio(patente, nuevoServicio);
	}
	
	public void editarValorBus(String patente, int nuevoPrecio) {
		buses.editar(patente, nuevoPrecio);
	}
	
	public boolean eliminarBus(String patente) {
		return buses.eliminar(patente);
	}
	
	public int cantidadDeBuses() {
		return buses.tamaño();
	}
	
	// Metodos de la coleccion de clientes
	
	public void agregarCliente(Cliente c) {
		clientes.agregar(c);
	}
	
	public Cliente buscarCliente(String rut) {
		return clientes.buscar(rut);
	}
	
	public Cliente obtenerCliente(int posicion) {
		return clientes.obtener(posicion);
	}
	
	public void editarNombreCliente(String rut, String nuevoNombre) {
		clientes.editarNombre(rut, nuevoNombre);
	}
	
	public void editarRutCliente(String rut, String nuevoRut) {
		clientes.editarRut(rut, nuevoRut);
	}
	
	public void editarEdadCliente(String rut, int nuevaEdad) {
		clientes.editarEdad(rut, nuevaEdad);
	}
	
	public void editarTelefonoCliente(String rut, int nuevoTelefono) {
		clientes.editarTelefono(rut, nuevoTelefono);
	}
	
	public boolean eliminarCliente(String rut) {
		return clientes.eliminar(rut);
	}
	
	public int cantidadDeClientes() {
		return clientes.tamaño();
	}
	
	// Metodos de la logica del negocio
	
	public int obtenerIdActualViaje() {
		return idActualViaje;
	}
	
	// Busca el bus correspondiente a la patente recibida por parametro y lo asigna al viaje recibido
	public void asignarBus(Viaje viaje, String patenteBusSeleccionado) {
		Bus busSeleccionado = buses.buscar(patenteBusSeleccionado);
		// Se cambia la disponibilidad del bus, indicando que se encuentra realizando un viaje
		busSeleccionado.setDisponibilidadFalse();
		viaje.asignarBus(busSeleccionado);
	}
	
	// Finaliza el viaje cambiando la disponibilidad del bus correspondiente y llama al metodo de la clase 'Viaje'
	public void finalizarViaje(Viaje viaje) {
		buses.buscar(viaje.getBusAsignado().getPatente()).setDisponibilidadTrue();
		viaje.finalizarViaje();
	}
	
	public int buscarViajeSegunBus(String patente) {
		for(int i = 0; i < viajes.tamaño(); i++) {
			if(viajes.obtener(i).getBusAsignado().getPatente().equals(patente))
				return viajes.obtener(i).getId();
		}
		return 0;
	}
	
	public int cantidadBusesDisponibles() {
		int cont = 0;
		for(int i = 0; i < buses.tamaño(); i++) {
			if(buses.obtener(i).getDisponibilidad())
				cont++;
		}
		return cont;
	}
	
	// Filtra los viajes de la empresa a partir de un origen-destino y siempre y cuando ese viaje ya tenga un bus asignado
	public ColeccionViajes filtrarViajesPorTrayecto(String origen, String destino) {
		ColeccionViajes viajesFiltrados = new ColeccionViajes();
		for(int i = 0; i < viajes.tamaño(); i++) {
			Viaje aux = viajes.obtener(i);
			
			if((aux.getOrigen().equals(origen) && aux.getDestino().equals(destino)) && aux.getBusAsignado() != null)
				viajesFiltrados.agregar(aux);
		}
		return viajesFiltrados;
	}
	
	// Retorna el pasajero que este en algun viaje activo (con bus asignado) con el rut mas antiguo de todo el sistema
	public Pasajero pasajeroConRutAntiguo() {
		Pasajero pasajeroRutAntiguo = null;
		Pasajero pasajeroActual = null;
		Collator comparador = Collator.getInstance();
		comparador.setStrength(Collator.PRIMARY);
		for(int i = 0; i < viajes.tamaño(); i++) {
			// Si el viaje actual tiene un bus asignado (en  otras palabras, si es posible que tenga pasajeros)
			if(viajes.obtener(i).getBusAsignado() != null) {
				if(pasajeroRutAntiguo == null)
					pasajeroRutAntiguo = viajes.obtener(i).pasajeroRutAntiguo();
				pasajeroActual = viajes.obtener(i).pasajeroRutAntiguo();
				if(pasajeroRutAntiguo != null && pasajeroActual != null)
					if(comparador.compare(pasajeroRutAntiguo.getRut(), pasajeroActual.getRut()) > 0)
						pasajeroRutAntiguo = pasajeroActual;
			}
		}
		return pasajeroRutAntiguo;
	}
	
	// Filtra los pasajeros de todos los viajes activos del sistema (con bus asignado) que esten en asientos impares
	public ArrayList<Pasajero> pasajerosAsientosImpares() {
		ArrayList<Pasajero> pasajerosFiltrados = new ArrayList<Pasajero>();
		for(int i = 0; i < viajes.tamaño(); i++) {
			if(viajes.obtener(i).getBusAsignado() != null)
				viajes.obtener(i).pasajerosAsientosImpares(pasajerosFiltrados);
		}
		return pasajerosFiltrados;
	}
	
	// Importacion de datos iniciales mediante archivos .txt
	public boolean importarDatos() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		String line;
		String[] input;
		
		try {
			// Importacion de buses
			archivo = new File("src\\datos\\buses.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			Bus auxB;
			
			while((line = br.readLine()) != null) {
				input = line.split(",");
				
				auxB = new Bus(input[0], input[1]);
				
				this.agregarBus(auxB);
			}
			
			// Se cierra el archivo actual antes de pasar a leer el siguiente
			try {
				if(fr != null)
					fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// Importacion de clientes
			archivo = new File("src\\datos\\clientes.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
						
			Cliente auxC;
						
			while((line = br.readLine()) != null) {
				input = line.split(",");
				
				if(input[4].equals("NO")) {
					auxC = new ClienteNormal(input[0], input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
				}
				else {
					auxC = new ClienteSuscrito(input[0], input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
				}
							
				this.agregarCliente(auxC);
			}
			
			// Se cierra el archivo actual antes de pasar a leer el siguiente
			try {
				if(fr != null)
					fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// Importacion de viajes
			archivo = new File("src\\datos\\viajes.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
						
			Viaje auxV;
						
			while((line = br.readLine()) != null) {
				input = line.split(",");
				
				auxV = new Viaje(idActualViaje, input[0], input[1], input[2], input[3], Integer.parseInt(input[4]));
						
				this.agregarViaje(auxV);
			}
			
			// Se cierra el archivo actual
			try {
				if(fr != null)
					fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Exportacion de datos a archivo .txt
	public void exportarDatos(int seleccion) {
		StringBuilder archivo = new StringBuilder();
		// Dependiendo desde donde se haya llamado el metodo (panel buses, clientes o viajes) se llenará mediante un StringBuilder la información
		switch(seleccion) {
			case 1:
				archivo.append("------ Buses ------\n\n");
				for(int i = 0; i < buses.tamaño(); i++) {
					archivo.append("Bus nº: " + i +
							" | Patente: " + buses.obtener(i).getPatente() +
							" | Servicio: " + buses.obtener(i).getServicio() +
							"\n");
				}
				break;
			case 2:
				archivo.append("------ Clientes ------\n\n");
				for(int i = 0; i < clientes.tamaño(); i++) {
					archivo.append("Cliente nº: " + i +
							" | Nombre: " + clientes.obtener(i).getNombre() +
							" | RUT: " + clientes.obtener(i).getRut() +
							" | Edad: " + clientes.obtener(i).getEdad() +
							" | Teléfono: " + clientes.obtener(i).getTelefono() +
							"\n");
				}
				break;
			case 3:
				archivo.append("------ Viajes ------\n\n");
				for(int i = 0; i < viajes.tamaño(); i++) {
					archivo.append("Viaje nº: " + i + 
							" | Origen: " + viajes.obtener(i).getOrigen() + 
							" | Destino: " + viajes.obtener(i).getDestino() +
							" | Valor: " + viajes.obtener(i).getValor() +
							"\n");
				}
				break;
		}
		
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Elija el lugar de destino");
		
		int userSelection = fileChooser.showSaveDialog(null);
		
		// Si se presiona el boton guardar, se procede a guardar el archivo
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    try {
				FileWriter fileWriter = new FileWriter(fileToSave.getAbsolutePath() + ".txt");
				String archivoFinal = archivo.toString();
				fileWriter.write(archivoFinal);
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
