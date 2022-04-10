package interfazGrafica;

import clases.Empresa;
import clases.Bus;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelBuses extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable table;

	public PanelBuses(Empresa empresa, DefaultTableModel modelBuses, JLabel lblTotal) {
		setLayout(null);
		JLabel lblBuses = new JLabel("Buses");
		lblBuses.setIcon(null);
		lblBuses.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 16));
		lblBuses.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuses.setBounds(10, 11, 630, 15);
		add(lblBuses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 37, 531, 544);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(modelBuses);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		llenarTabla(empresa, modelBuses);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 37, 89, 602);
		add(panel_1);
		panel_1.setLayout(new GridLayout(8, 1, 0, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAgregarBus ventanaAgregarBus = new VentanaAgregarBus(empresa, modelBuses, lblTotal);
				ventanaAgregarBus.setLocationRelativeTo(null);
				ventanaAgregarBus.setVisible(true);
			}
		});
		panel_1.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeBuses() == 0)
					JOptionPane.showMessageDialog(null, "No hay buses en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el bus que desea editar");
					else {
						VentanaEditarBus ventanaEditarBus = new VentanaEditarBus(empresa, table, row, modelBuses);
						ventanaEditarBus.setLocationRelativeTo(null);
						ventanaEditarBus.setVisible(true);
					}
				}
			}
		});
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeBuses() == 0)
					JOptionPane.showMessageDialog(null, "No hay buses en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el bus que desea eliminar");
					else {
						String patenteBusSeleccionado = (String) table.getValueAt(row, 0);
						if(!empresa.buscarBus((String) table.getValueAt(row, 0)).getDisponibilidad())
							JOptionPane.showMessageDialog(null, "Actualmente el bus está realizando el viaje numero " + 
																empresa.buscarViajeSegunBus(patenteBusSeleccionado) + 
																", debe finalizar el viaje para poder eliminar al bus del sistema");
						else {
							empresa.eliminarBus(patenteBusSeleccionado);
							modelBuses.removeRow(row);
							lblTotal.setText("Total: " + empresa.cantidadDeBuses());
						}
					}
				}
			}
		});
		panel_1.add(btnEliminar);
		
		// Se agrega el lbl total a la cuarta fila del grid layout
		panel_1.add(lblTotal);
		
		JButton btnPromocion = new JButton();
		btnPromocion.setText("<html><center>Aplicar Promoción</html>");
		btnPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeBuses() == 0)
					JOptionPane.showMessageDialog(null, "No hay buses en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el bus al que le desea aplicar una promoción");
					else {
						String patenteBusSeleccionado = (String) table.getValueAt(row, 0);
						String nuevoValor = "$" + Integer.toString(empresa.buscarBus(patenteBusSeleccionado).promocion());
						Tabla.actualizarFila(modelBuses, nuevoValor, row, 2);
					}
				}
			}
		});
		panel_1.add(btnPromocion);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 592, 531, 47);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnExportar = new JButton("Exportar Buses");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empresa.exportarDatos(1);
			}
		});
		panel.add(btnExportar);
	}
	
	private void llenarTabla(Empresa empresa, DefaultTableModel model) {
		for(int i = 0; i < empresa.cantidadDeBuses(); i++) {
			Bus aux = empresa.obtenerBus(i);
			
			if(aux.getDisponibilidad())
				model.addRow(new Object[] {aux.getPatente(), aux.getServicio(), "$" + aux.getValor(), aux.getCapacidad(), "SI"});
			else
				model.addRow(new Object[] {aux.getPatente(), aux.getServicio(), "$" + aux.getValor(), aux.getCapacidad(), "NO"});
		}
	}
}
