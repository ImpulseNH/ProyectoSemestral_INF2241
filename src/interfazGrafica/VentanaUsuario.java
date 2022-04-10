package interfazGrafica;

import clases.Empresa;
import clases.Viaje;
import colecciones.ColeccionViajes;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class VentanaUsuario extends Main {
	private static final long serialVersionUID = 1L;
	
	private static JFrame ventanaElegirAsiento;
	private JPanel contentPane;
	private JTable table;

	public VentanaUsuario(Empresa empresa, ColeccionViajes viajesFiltrados) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 774, 478);
		contentPane.add(scrollPane);
		
		DefaultTableModel modelViajes = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Origen", "Destino", "Hora de partida", "Hora de llegada", "Servicio", "Valor", "Asientos"
				}
			) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, String.class
				};
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@SuppressWarnings("unused")
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};;
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(modelViajes);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		llenarTabla(viajesFiltrados, modelViajes);
		scrollPane.setViewportView(table);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1)
					JOptionPane.showMessageDialog(null, "Debe seleccionar el pasaje que desea comprar");
				else {
					Viaje viajeSeleccionado = viajesFiltrados.buscar((Integer) table.getValueAt(row, 0));
					ventanaElegirAsiento = new VentanaElegirAsiento(empresa, viajeSeleccionado, viajeSeleccionado.getValor() + viajeSeleccionado.getBusAsignado().getValor());
					ventanaElegirAsiento.setLocationRelativeTo(null);
					ventanaElegirAsiento.setVisible(true);
				}
			}
		});
		btnSeleccionar.setBounds(5, 485, 152, 23);
		contentPane.add(btnSeleccionar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(empresa);
				dispose();
				ventanaPrincipal.setLocationRelativeTo(null);
				ventanaPrincipal.setVisible(true);
			}
		});
		btnVolver.setBounds(627, 485, 152, 23);
		contentPane.add(btnVolver);
	}
	
	public static void cerrarVentanaElegirAsiento() {
		ventanaElegirAsiento.dispose();
	}
	
	public void llenarTabla(ColeccionViajes viajesFiltrados, DefaultTableModel model) {
		for(int i = 0; i < viajesFiltrados.tamaño(); i++) {
			Viaje aux = viajesFiltrados.obtener(i);
			
			model.addRow(new Object[] {
					aux.getId(),
					aux.getOrigen(), 
					aux.getDestino(), 
					aux.getHoraPartida(), 
					aux.getHoraLlegada(), 
					aux.getBusAsignado().getServicio(),
					"$" + (aux.getValor() + aux.getBusAsignado().getValor()),
					aux.cantidadDePasajeros() + "/" + aux.getBusAsignado().getCapacidad()});
		}
	}
}
