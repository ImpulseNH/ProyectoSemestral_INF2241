package interfazGrafica;

import clases.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarPasajero extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldRut;
	private JTextField textFieldAsiento;

	public VentanaAgregarPasajero(Viaje viaje, DefaultTableModel model, JLabel lblTotal, int rowViaje, DefaultTableModel modelViajes) {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(53, 11, 65, 21);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(128, 12, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblRUT = new JLabel("RUT:");
		lblRUT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRUT.setBounds(53, 54, 65, 21);
		contentPane.add(lblRUT);
		
		textFieldRut = new JTextField();
		textFieldRut.setColumns(10);
		textFieldRut.setBounds(128, 55, 116, 22);
		contentPane.add(textFieldRut);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nombre = textFieldNombre.getText();
					String rut = textFieldRut.getText();
					int numeroAsiento = Integer.parseInt(textFieldAsiento.getText());
					
					if(nombre.equals("") || rut.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
					}
					else {
						if(viaje.buscarPasajero(rut) == null) {
							if(numeroAsiento >= 1 && numeroAsiento <= viaje.getBusAsignado().getCapacidad()) {
								if(viaje.obtenerPasajero(numeroAsiento - 1) == null) {
									Pasajero nuevoPasajero = new Pasajero(nombre, rut);
									
									viaje.agregarPasajero(nuevoPasajero, numeroAsiento - 1);
									
									Tabla.agregarFilaPasajero(model, nuevoPasajero, numeroAsiento);
									lblTotal.setText("Total: " + viaje.cantidadDePasajeros());
									// Se actualizan los asientos del panel de viajes
									Tabla.actualizarFila(modelViajes, viaje.cantidadDePasajeros() + "/" + viaje.getBusAsignado().getCapacidad(), rowViaje, 7);
									
									dispose();
								}
								else
									JOptionPane.showMessageDialog(null, "Ya hay un pasajero en ese asiento, elija otro");
							}
							else
								throw new AsientoIncorrectoException(viaje.getBusAsignado().getCapacidad());
						}
						else
							JOptionPane.showMessageDialog(null, "Ya existe un pasajero con ese RUT");
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un asiento valido");
			    }
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAgregar.setBounds(154, 179, 97, 25);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(154, 215, 97, 23);
		contentPane.add(btnVolver);
		
		textFieldAsiento = new JTextField();
		textFieldAsiento.setColumns(10);
		textFieldAsiento.setBounds(128, 103, 116, 22);
		contentPane.add(textFieldAsiento);
		
		JLabel lblAsiento = new JLabel("N\u00BA DE ASIENTO");
		lblAsiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAsiento.setBounds(10, 103, 108, 21);
		contentPane.add(lblAsiento);
	}
}
