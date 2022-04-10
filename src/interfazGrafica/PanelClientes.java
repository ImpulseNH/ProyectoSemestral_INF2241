package interfazGrafica;

import clases.Empresa;
import clases.Cliente;
import clases.ClienteNormal;
import clases.ClienteSuscrito;
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

public class PanelClientes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable table;

	public PanelClientes(Empresa empresa, DefaultTableModel modelClientes) {
		setLayout(null);
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setIcon(null);
		lblClientes.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 16));
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setBounds(10, 11, 630, 15);
		add(lblClientes);
		
		JLabel lblTotal = new JLabel("Total: " + empresa.cantidadDeClientes());
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 37, 531, 544);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(modelClientes);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		llenarTabla(empresa, modelClientes);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 37, 89, 602);
		add(panel_1);
		panel_1.setLayout(new GridLayout(8, 1, 0, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarCliente ventanaAgregarCliente = new VentanaAgregarCliente(empresa, modelClientes, lblTotal);
				ventanaAgregarCliente.setLocationRelativeTo(null);
				ventanaAgregarCliente.setVisible(true);
			}
		});
		panel_1.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeClientes() == 0)
					JOptionPane.showMessageDialog(null, "No hay clientes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente que desea editar");
					else {
						VentanaEditarCliente ventanaEditarCliente = new VentanaEditarCliente(empresa, table, row, modelClientes);
						ventanaEditarCliente.setLocationRelativeTo(null);
						ventanaEditarCliente.setVisible(true);
					}
				}
			}
		});
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empresa.cantidadDeClientes() == 0)
					JOptionPane.showMessageDialog(null, "No hay clientes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente que desea eliminar");
					else {
						empresa.eliminarCliente((String) table.getValueAt(row, 1));
						modelClientes.removeRow(row);
						lblTotal.setText("Total: " + empresa.cantidadDeClientes());
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
				if(empresa.cantidadDeClientes() == 0)
					JOptionPane.showMessageDialog(null, "No hay clientes en el sistema");
				else {
					int row = table.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente al que le desea aplicar una promoción");
					else {
						String rutClienteSeleccionado = (String) table.getValueAt(row, 1);
						Cliente aux = empresa.buscarCliente(rutClienteSeleccionado);
						if(aux instanceof ClienteNormal) {
							JOptionPane.showMessageDialog(null, "El cliente no está suscrito por lo que una promoción no es aplicable");
						}
						else {
							String nuevosPuntos = Integer.toString(((ClienteSuscrito) aux).promocion());
							Tabla.actualizarFila(modelClientes, nuevosPuntos, row, 6);
						}
					}
				}
			}
		});
		panel_1.add(btnPromocion);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 592, 531, 47);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnExportar = new JButton("Exportar Clientes");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empresa.exportarDatos(2);
			}
		});
		panel.add(btnExportar);
	}
	
	private void llenarTabla(Empresa empresa, DefaultTableModel model) {
		for(int i = 0; i < empresa.cantidadDeClientes(); i++) {
			Cliente aux = empresa.obtenerCliente(i);
			
			if(aux instanceof ClienteNormal)
				model.addRow(new Object[] {aux.getNombre(), aux.getRut(), aux.getEdad(), aux.getTelefono(), "NO", ((ClienteNormal) aux).getViajesRealizados(), "N/A"});
			else
				model.addRow(new Object[] {aux.getNombre(), aux.getRut(), aux.getEdad(), aux.getTelefono(), "SI", "N/A", ((ClienteSuscrito) aux).getPuntosAcumulados()});
		}
	}
}
