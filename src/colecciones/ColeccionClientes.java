package colecciones;

import clases.Cliente;
import java.util.ArrayList;
import java.util.HashMap;

public class ColeccionClientes {
	private HashMap<String, Cliente> clientes;
	private ArrayList<String> rutClientes;

	public ColeccionClientes() {
		clientes = new HashMap<String, Cliente>();
		rutClientes = new ArrayList<String>();
	}
	
	public void agregar(Cliente c) {
		clientes.put(c.getRut(), c);
		rutClientes.add(c.getRut());
	}

	public Cliente buscar(String rut) {
		return clientes.get(rut);
	}
	
	public Cliente obtener(int posicion) {
		return clientes.get(rutClientes.get(posicion));
	}
	
	public void editarNombre(String rut, String nuevoNombre) {
		clientes.get(rut).setNombre(nuevoNombre);
	}
	
	public void editarRut(String rut, String nuevoRut) {
		clientes.get(rut).setRut(nuevoRut);
		clientes.put(nuevoRut, clientes.remove(rut));
		for(int i = 0; i < rutClientes.size(); i++) {
			if(rutClientes.get(i).equals(rut))
				rutClientes.set(i, nuevoRut);
		}
	}
	
	public void editarEdad(String rut, int nuevaEdad) {
		clientes.get(rut).setEdad(nuevaEdad);
	}
	
	public void editarTelefono(String rut, int nuevoTelefono) {
		clientes.get(rut).setTelefono(nuevoTelefono);
	}
	
	public boolean eliminar(String rut) {
		if(clientes.remove(rut) != null) {
			rutClientes.remove(rut);
			return true;
		}
		return false;
	}
	
	public int tamaño() {
		return clientes.size();
	}
}
