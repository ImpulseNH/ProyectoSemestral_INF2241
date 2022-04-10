package interfazGrafica;

import clases.Empresa;
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

public class VentanaEditarCliente extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtNuevoNombre;
	private JTextField txtNuevoRut;
	private JTextField txtNuevaEdad;
	private JTextField txtNuevoTelefono;

	public VentanaEditarCliente(Empresa empresa, JTable table, int row, DefaultTableModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 385);
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
		
		txtNuevoRut = new JTextField();
		txtNuevoRut.setText("Nuevo RUT");
		txtNuevoRut.setEnabled(false);
		txtNuevoRut.setColumns(10);
		txtNuevoRut.setBounds(22, 105, 198, 20);
		contentPane.add(txtNuevoRut);
		
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
		
		txtNuevaEdad = new JTextField();
		txtNuevaEdad.setText("Nueva edad");
		txtNuevaEdad.setEnabled(false);
		txtNuevaEdad.setColumns(10);
		txtNuevaEdad.setBounds(22, 177, 198, 20);
		contentPane.add(txtNuevaEdad);
		
		JRadioButton cbEdad = new JRadioButton("Edad");
		cbEdad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevaEdad.setEnabled(true);
					txtNuevaEdad.setText("");
				}
				else {
					txtNuevaEdad.setEnabled(false);
					txtNuevaEdad.setText("Nueva edad");
				}
			}
		});
		cbEdad.setBounds(22, 147, 198, 23);
		contentPane.add(cbEdad);
		
		txtNuevoTelefono = new JTextField();
		txtNuevoTelefono.setText("Nuevo tel\u00E9fono");
		txtNuevoTelefono.setEnabled(false);
		txtNuevoTelefono.setColumns(10);
		txtNuevoTelefono.setBounds(22, 247, 198, 20);
		contentPane.add(txtNuevoTelefono);
		
		JRadioButton cbTelefono = new JRadioButton("Tel\u00E9fono");
		cbTelefono.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoTelefono.setEnabled(true);
					txtNuevoTelefono.setText("");
				}
				else {
					txtNuevoTelefono.setEnabled(false);
					txtNuevoTelefono.setText("Nuevo teléfono");
				}
			}
		});
		cbTelefono.setBounds(22, 217, 198, 23);
		contentPane.add(cbTelefono);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cbNombre.isSelected()) {
						String nuevoNombre = txtNuevoNombre.getText();
						if(nuevoNombre.equals("")) {
							JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
						}
						else {
							empresa.editarNombreCliente((String) table.getValueAt(row, 1), nuevoNombre);
							Tabla.actualizarFila(model, nuevoNombre, row, 0);
						}
					}
					if(cbRut.isSelected()) {
						String nuevoRut = txtNuevoRut.getText();
						if(nuevoRut.equals("")) {
							JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
						}
						else {
							if(empresa.buscarCliente(txtNuevoRut.getText()) == null) {
								empresa.editarRutCliente((String) table.getValueAt(row, 1), nuevoRut);
								Tabla.actualizarFila(model, nuevoRut, row, 1);
							}
							else
								JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese rut");
						}
					}
					if(cbEdad.isSelected()) {
						empresa.editarEdadCliente((String) table.getValueAt(row, 1), Integer.parseInt(txtNuevaEdad.getText()));
						Tabla.actualizarFila(model, txtNuevaEdad.getText(), row, 2);
					}
					if(cbTelefono.isSelected()) {
						empresa.editarTelefonoCliente((String) table.getValueAt(row, 1), Integer.parseInt(txtNuevoTelefono.getText()));
						Tabla.actualizarFila(model, txtNuevoTelefono.getText(), row, 3);
					}
					dispose();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido");
			    }
			}
		});
		btnConfirmar.setBounds(72, 297, 109, 20);
		contentPane.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(72, 322, 109, 23);
		contentPane.add(btnVolver);
	}
}
