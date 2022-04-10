package interfazGrafica;

import clases.Viaje;
import clases.Bus;
import clases.Pasajero;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class VentanaPasajeros extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;

	public VentanaPasajeros(Viaje viaje, int rowViaje, DefaultTableModel modelViajes) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPasajeros = new JLabel("Pasajeros del viaje " + viaje.getId());
		lblPasajeros.setIcon(null);
		lblPasajeros.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 16));
		lblPasajeros.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasajeros.setBounds(10, 11, 630, 15);
		getContentPane().add(lblPasajeros);
		
		JLabel lblTotal = new JLabel("Total: " + viaje.cantidadDePasajeros());
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 37, 531, 544);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nombre", "RUT", "Asiento"
				}
			) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class
				};
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@SuppressWarnings("unused")
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		llenarTabla(viaje, model);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 37, 89, 602);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(7, 1, 0, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(viaje.cantidadDePasajeros() == 40) {
					JOptionPane.showMessageDialog(null, "El bus ya esta lleno");
				}
				else {
					VentanaAgregarPasajero ventanaAgregarPasajero = new VentanaAgregarPasajero(viaje, model, lblTotal, rowViaje, modelViajes);
					ventanaAgregarPasajero.setLocationRelativeTo(null);
					ventanaAgregarPasajero.setVisible(true);
				}
			}
		});
		panel_1.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(viaje.cantidadDePasajeros() == 0)
					JOptionPane.showMessageDialog(null, "No hay pasajeros en el bus");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente que desea editar");
					else {
						VentanaEditarPasajero ventanaEditarPasajero = new VentanaEditarPasajero(viaje, table, row, model);
						ventanaEditarPasajero.setLocationRelativeTo(null);
						ventanaEditarPasajero.setVisible(true);
					}
				}
			}
		});
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(viaje.cantidadDePasajeros() == 0)
					JOptionPane.showMessageDialog(null, "No hay pasajeros en el bus");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el pasajero que desea eliminar");
					else {
						viaje.eliminarPasajeroPorAsiento((Integer) table.getValueAt(row, 2) - 1);
						model.removeRow(row);
						lblTotal.setText("Total: " + viaje.cantidadDePasajeros());
						Tabla.actualizarFila(modelViajes, viaje.cantidadDePasajeros() + "/" + viaje.getBusAsignado().getCapacidad(), rowViaje, 7);
					}
				}
			}
		});
		panel_1.add(btnEliminar);
		
		// Se agrega el lbl total a la cuarta fila del grid layout
		panel_1.add(lblTotal);
		
		JButton btnInfo = new JButton();
		btnInfo.setText("<html><center>Información del bus<html>");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bus busAsignado = viaje.getBusAsignado();
				JOptionPane.showMessageDialog(null, "Patente: " + busAsignado.getPatente() + "\n" +
													"Servicio: " + busAsignado.getServicio() + "\n" +
													"Valor: " + busAsignado.getValor() + "\n" +
													"Capacidad: " + busAsignado.getCapacidad());
			}
		});
		panel_1.add(btnInfo);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 592, 531, 47);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnImportar = new JButton("Importar Pasajeros");
		btnImportar.setEnabled(false);
		panel.add(btnImportar);
		
		JButton btnExportar = new JButton("Exportar Pasajeros");
		btnExportar.setEnabled(false);
		panel.add(btnExportar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(10, 650, 630, 23);
		contentPane.add(btnVolver);
	}
	
	private void llenarTabla(Viaje viaje, DefaultTableModel model) {
		for(int i = 0; i < viaje.getBusAsignado().getCapacidad(); i++) {
			Pasajero aux = viaje.obtenerPasajero(i);
			
			if(aux != null) {
				model.addRow(new Object[] {aux.getNombre(), aux.getRut(), i + 1});
			}
		}
	}
}
