package interfazGrafica;

import clases.Empresa;
import clases.Viaje;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class PanelViajes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable table;

	public PanelViajes(Empresa empresa, DefaultTableModel modelViajes, DefaultTableModel modelBuses, JLabel lblTotalBuses) {
		setLayout(null);
		JLabel lblViajes = new JLabel("Viajes");
		lblViajes.setIcon(null);
		lblViajes.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 16));
		lblViajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblViajes.setBounds(10, 11, 630, 15);
		add(lblViajes);
		
		JLabel lblTotal = new JLabel("Total: " + empresa.cantidadDeViajes());
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 37, 531, 544);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(modelViajes);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);
		llenarTabla(empresa, modelViajes);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 37, 89, 602);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAgregarViaje ventanaAgregarViaje = new VentanaAgregarViaje(empresa, empresa.obtenerIdActualViaje(), modelViajes, lblTotal);
				ventanaAgregarViaje.setLocationRelativeTo(null);
				ventanaAgregarViaje.setVisible(true);
			}
		});
		panel_1.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeViajes() == 0)
					JOptionPane.showMessageDialog(null, "No hay viajes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el viaje que desea editar");
					else {
						VentanaEditarViaje ventanaEditarViaje = new VentanaEditarViaje(empresa, table, row, modelViajes);
						ventanaEditarViaje.setLocationRelativeTo(null);
						ventanaEditarViaje.setVisible(true);
					}
				}
			}
		});
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(empresa.cantidadDeViajes() == 0)
					JOptionPane.showMessageDialog(null, "No hay viajes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el viaje que desea eliminar");
					else {
						if(empresa.buscarViaje((Integer) table.getValueAt(row, 0)).getBusAsignado() != null) {
							Viaje viajePorFinalizar = empresa.buscarViaje((Integer) table.getValueAt(row, 0));
							String patenteBusDisponible = viajePorFinalizar.getBusAsignado().getPatente();
							empresa.finalizarViaje(viajePorFinalizar);
							actualizarPanelBuses(modelBuses, patenteBusDisponible);
						}
						empresa.eliminarViaje((Integer) table.getValueAt(row, 0));
						modelViajes.removeRow(row);
						lblTotal.setText("Total: " + empresa.cantidadDeViajes());
					}
				}
			}
		});
		panel_1.add(btnEliminar);
		
		// Se agrega el lbl total a la cuarta fila del grid layout
		panel_1.add(lblTotal);
		
		JButton btnAsignar = new JButton();
		btnAsignar.setText("<html><center>Asignar Bus</html>");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeViajes() == 0)
					JOptionPane.showMessageDialog(null, "No hay viajes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el viaje al que le desea asignar un bus");
					else {
						if(empresa.buscarViaje((Integer) table.getValueAt(row, 0)).getBusAsignado() != null)
							JOptionPane.showMessageDialog(null, "El viaje ya tiene un bus asignado");
						else {
							if(empresa.cantidadBusesDisponibles() == 0)
								JOptionPane.showMessageDialog(null, "No hay buses disponibles");
							else {
								VentanaAsignarBus ventanaAsignarBus = new VentanaAsignarBus(empresa, empresa.buscarViaje((Integer) table.getValueAt(row, 0)), row, modelViajes, modelBuses, lblTotalBuses);
								ventanaAsignarBus.setLocationRelativeTo(null);
								ventanaAsignarBus.setVisible(true);
							}
						}
					}
				}
			}
		});
		
		JButton btnPromocion = new JButton();
		btnPromocion.setText("<html><center>Aplicar Promoción</html>");
		btnPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeViajes() == 0)
					JOptionPane.showMessageDialog(null, "No hay viajes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el viaje al que le desea aplicar una promoción");
					else {
						int idViajeSeleccionado = (Integer) table.getValueAt(row, 0);
						String nuevoValor = "$" + Integer.toString(empresa.buscarViaje(idViajeSeleccionado).promocion());
						Tabla.actualizarFila(modelViajes, nuevoValor, row, 5);
					}
				}
			}
		});
		panel_1.add(btnPromocion);
		btnAsignar.setToolTipText("");
		panel_1.add(btnAsignar);
		
		JButton btnMostrar = new JButton();
		btnMostrar.setText("<html><center>Administrar pasajeros</html>");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeViajes() == 0)
					JOptionPane.showMessageDialog(null, "No hay viajes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar un viaje para poder administrar sus pasajeros");
					else {
						if(empresa.buscarViaje((Integer) table.getValueAt(row, 0)).getBusAsignado() == null)
							JOptionPane.showMessageDialog(null, "Aún no se ha asignado un bus a este viaje");
						else {
							VentanaPasajeros ventanaPasajeros = new VentanaPasajeros(empresa.buscarViaje((Integer) table.getValueAt(row, 0)), row, modelViajes);
							ventanaPasajeros.setLocationRelativeTo(null);
							ventanaPasajeros.setVisible(true);
						}
					}
				}
			}
		});
		
		panel_1.add(btnMostrar);
		
		JButton btnFinalizar = new JButton();
		btnFinalizar.setText("<html><center>Finalizar viaje</html>");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(empresa.cantidadDeViajes() == 0)
					JOptionPane.showMessageDialog(null, "No hay viajes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el viaje que desea finalizar");
					else {
						if(empresa.buscarViaje((Integer) table.getValueAt(row, 0)).getBusAsignado() == null) {
							JOptionPane.showMessageDialog(null, "Aún no se ha asignado un bus a este viaje");
						}
						else {
							Viaje viajePorFinalizar = empresa.buscarViaje((Integer) table.getValueAt(row, 0));
							String patenteBusDisponible = viajePorFinalizar.getBusAsignado().getPatente();
							empresa.finalizarViaje(viajePorFinalizar);
							Tabla.actualizarFila(modelViajes, "NO", row, 6);
							Tabla.actualizarFila(modelViajes, "0/0", row, 7);
							actualizarPanelBuses(modelBuses, patenteBusDisponible);
						}
					}
				}
			}
		});
		panel_1.add(btnFinalizar);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 592, 531, 47);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnExportar = new JButton("Exportar Viajes");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empresa.exportarDatos(3);
			}
		});
		panel.add(btnExportar);
	}
	
	public void llenarTabla(Empresa empresa, DefaultTableModel model) {
		for(int i = 0; i < empresa.cantidadDeViajes(); i++) {
			Viaje aux = empresa.obtenerViaje(i);
			
			if(aux.getBusAsignado() == null)
				model.addRow(new Object[] {aux.getId(), aux.getOrigen(), aux.getDestino(), aux.getHoraPartida(), aux.getHoraLlegada(), "$" + aux.getValor(), "NO", "0/0"});
			else
				model.addRow(new Object[] {aux.getId(), aux.getOrigen(), aux.getDestino(), aux.getHoraPartida(), aux.getHoraLlegada(), "$" + aux.getValor(), "SI", aux.cantidadDePasajeros() + "/" + aux.getBusAsignado().getCapacidad()});
		}
	}
	
	private void actualizarPanelBuses(DefaultTableModel modelBuses, String patenteBusDisponible) {
		for(int i = 0; i < modelBuses.getRowCount(); i++) {
			if(modelBuses.getValueAt(i, 0).equals(patenteBusDisponible)) {
				modelBuses.setValueAt("SI", i, 4);
				return;
			}
		}
	}
}
