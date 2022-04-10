package interfazGrafica;

import clases.Viaje;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarPasajero extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtNuevoNombre;
	private JTextField txtNuevoRut;

	public VentanaEditarPasajero(Viaje viaje, JTable table, int row, DefaultTableModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setText("Nuevo nombre");
		txtNuevoNombre.setEnabled(false);
		txtNuevoNombre.setBounds(22, 37, 198, 20);
		contentPane.add(txtNuevoNombre);
		txtNuevoNombre.setColumns(10);
		
		JRadioButton cbNombre = new JRadioButton("Nombre");
		cbNombre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoNombre.setEnabled(true);
					txtNuevoNombre.setText("");
				}
				else {
					txtNuevoNombre.setEnabled(false);
					txtNuevoNombre.setText("Nuevo nombre");
				}
			}
		});
		cbNombre.setBounds(22, 7, 198, 23);
		contentPane.add(cbNombre);
		
		JRadioButton cbRut = new JRadioButton("RUT");
		cbRut.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoRut.setEnabled(true);
					txtNuevoRut.setText("");
				}
				else {
					txtNuevoRut.setEnabled(false);
					txtNuevoRut.setText("Nuevo RUT");
				}
			}
		});
		cbRut.setBounds(22, 75, 198, 23);
		contentPane.add(cbRut);
		
		txtNuevoRut = new JTextField();
		txtNuevoRut.setText("Nuevo RUT");
		txtNuevoRut.setEnabled(false);
		txtNuevoRut.setColumns(10);
		txtNuevoRut.setBounds(22, 105, 198, 20);
		contentPane.add(txtNuevoRut);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbNombre.isSelected()) {
					String nuevoNombre = txtNuevoNombre.getText();
					if(nuevoNombre.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
					}
					else {
						viaje.editarNombrePasajero((String) table.getValueAt(row, 1), nuevoNombre);
						Tabla.actualizarFila(model, nuevoNombre, row, 0);
					}
				}
				if(cbRut.isSelected()) {
					String nuevoRut = txtNuevoRut.getText();
					if(nuevoRut.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
					}
					else {
						if(viaje.buscarPasajero(txtNuevoRut.getText()) == null) {
							viaje.editarRutPasajero((String) table.getValueAt(row, 1), nuevoRut);
							Tabla.actualizarFila(model, nuevoRut, row, 1);
						}
						else
							JOptionPane.showMessageDialog(null, "Ya existe un pasajero con ese rut");
					}
				}
				dispose();
			}
		});
		btnConfirmar.setBounds(72, 152, 109, 20);
		contentPane.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(72, 172, 109, 23);
		contentPane.add(btnVolver);
	}
}
