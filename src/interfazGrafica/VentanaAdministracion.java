package interfazGrafica;

import clases.Empresa;
import clases.Pasajero;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaAdministracion extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public VentanaAdministracion(Empresa empresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 750);
		
		JMenu menu = new JMenu("Menu");
		JMenuItem menuItemViajes = new JMenuItem("Viajes");
		JMenuItem menuItemBuses = new JMenuItem("Buses");
		JMenuItem menuItemClientes = new JMenuItem("Clientes");
		menu.add(menuItemViajes);
		menu.add(menuItemBuses);
		menu.add(menuItemClientes);
		JMenu menu2 = new JMenu("Funciones propias");
		JMenuItem menuItemF1 = new JMenuItem("Pasajero con rut más antiguo");
		JMenuItem menuItemF2 = new JMenuItem("Pasajeros con asientos impares");
		menu2.add(menuItemF1);
		menu2.add(menuItemF2);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.add(menu2);
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		DefaultTableModel modelViajes = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Origen", "Destino", "Hora de partida", "Hora de llegada", "Valor", "Bus", "Asientos"
				}
			) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
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
		DefaultTableModel modelBuses = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Patente", "Servicio", "Valor", "Capacidad", "Disponible"
				}
			) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
				};
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@SuppressWarnings("unused")
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};;
		DefaultTableModel modelClientes = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nombre", "RUT", "Edad", "Teléfono", "Suscrito", "Viajes realizados", "Puntos acumulados"
				}
			) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@SuppressWarnings("unused")
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
		JLabel lblTotalBuses = new JLabel("Total: " + empresa.cantidadDeBuses());
		lblTotalBuses.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalBuses.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 15));
		
		PanelViajes panelViajes = new PanelViajes(empresa, modelViajes, modelBuses, lblTotalBuses);
		PanelBuses panelBuses = new PanelBuses(empresa, modelBuses, lblTotalBuses);
		PanelClientes panelClientes = new PanelClientes(empresa, modelClientes);
		CardLayout cl = new CardLayout();
		panel.setLayout(cl);
		panel.add(panelViajes, "Viajes");
		panel.add(panelBuses, "Buses");
		panel.add(panelClientes, "Clientes");
		
		menuItemViajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "Viajes");
			}
		});
		
		menuItemBuses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "Buses");
			}
		});
		
		menuItemClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "Clientes");
			}
		});
		
		menuItemF1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pasajero pasajeroRutAntiguo = empresa.pasajeroConRutAntiguo();
				if(pasajeroRutAntiguo == null) {
					JOptionPane.showMessageDialog(null, "No hay pasajeros en el sistema");
				}
				else {
					JOptionPane.showMessageDialog(null, "Los datos del pasajero con el RUT más antiguo son: \n"
														+ "Nombre: " + pasajeroRutAntiguo.getNombre() + "\n"
														+ "RUT: " + pasajeroRutAntiguo.getRut());
				}
			}
		});
		
		menuItemF2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Pasajero> pasajerosFiltrados = empresa.pasajerosAsientosImpares();
				if(pasajerosFiltrados.size() == 0) {
					JOptionPane.showMessageDialog(null, "No hay pasajeros que cumplan ese criterio o no hay pasajeros en el sistema");
				}
				else {
					String output = "";
					for(int i = 0; i < pasajerosFiltrados.size(); i++) {
						output += pasajerosFiltrados.get(i).getNombre() + " | " + pasajerosFiltrados.get(i).getRut() + "\n";
					}
					JOptionPane.showMessageDialog(null, "Los pasajeros con asientos impares de todo el sistema son: \n" + output);
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(empresa);
				dispose();
				ventanaPrincipal.setLocationRelativeTo(null);
				ventanaPrincipal.setVisible(true);
			}
		});
		panel_1.add(btnVolver);
	}
}
