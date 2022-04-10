package interfazGrafica;

import clases.Empresa;
import clases.Bus;
import clases.Viaje;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAsignarBus extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public VentanaAsignarBus(Empresa empresa, Viaje viaje, int row, DefaultTableModel modelViajes, DefaultTableModel modelBuses, JLabel lblTotalBuses) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBusesDisponibles = new JLabel("Buses disponibles");
		lblBusesDisponibles.setBounds(10, 29, 313, 14);
		contentPane.add(lblBusesDisponibles);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		llenarComboBox(empresa, comboBox);
		comboBox.setBounds(10, 54, 424, 20);
		contentPane.add(comboBox);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String patenteBusSeleccionado = ((String) comboBox.getSelectedItem()).substring(9,15);
				empresa.asignarBus(viaje, patenteBusSeleccionado);
				
				modelViajes.setValueAt("SI", row, 6);
				modelViajes.setValueAt(viaje.cantidadDePasajeros() + "/" + viaje.getBusAsignado().getCapacidad(), row, 7);
				actualizarPanelBuses(modelBuses, patenteBusSeleccionado);
				
				dispose();
			}
		});
		btnConfirmar.setBounds(168, 105, 119, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(168, 134, 119, 23);
		contentPane.add(btnVolver);
	}
	
	private void llenarComboBox(Empresa empresa, JComboBox<String> comboBox) {
		for(int i = 0; i < empresa.cantidadDeBuses(); i++) {
			Bus aux = empresa.obtenerBus(i);
			if(aux.getDisponibilidad())
				comboBox.addItem("PATENTE: " + aux.getPatente() + " | SERVICIO: " + aux.getServicio() + " | CAPACIDAD: " + aux.getCapacidad());
		}
	}
	
	private void actualizarPanelBuses(DefaultTableModel modelBuses, String patenteBusSeleccionado) {
		for(int i = 0; i < modelBuses.getRowCount(); i++) {
			if(modelBuses.getValueAt(i, 0).equals(patenteBusSeleccionado)) {
				modelBuses.setValueAt("NO", i, 4);
				return;
			}
		}
	}
}
