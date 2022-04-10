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

public class VentanaAgregarCliente extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldRut;
	private JTextField textFieldEdad;
	private JTextField textFieldTelefono;

	public VentanaAgregarCliente(Empresa empresa, DefaultTableModel model, JLabel lblTotal) {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(53, 32, 65, 21);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(130, 32, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblRUT = new JLabel("RUT:");
		lblRUT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRUT.setBounds(53, 64, 65, 21);
		contentPane.add(lblRUT);
		
		textFieldRut = new JTextField();
		textFieldRut.setColumns(10);
		textFieldRut.setBounds(130, 65, 116, 22);
		contentPane.add(textFieldRut);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nombre = textFieldNombre.getText();
					String rut = textFieldRut.getText();
					int edad = Integer.parseInt(textFieldEdad.getText());
					int telefono = Integer.parseInt(textFieldTelefono.getText());
					
					if(nombre.equals("") || rut.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
					}
					else {
						if(empresa.buscarCliente(rut) == null) {
							Cliente nuevoCliente = new ClienteNormal(nombre, rut, edad, telefono);
							
							empresa.agregarCliente(nuevoCliente);
							
							Tabla.agregarFilaCliente(model, nuevoCliente);
							lblTotal.setText("Total: " + empresa.cantidadDeClientes());
							
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese RUT");
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido");
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
		
		JLabel lblEdad = new JLabel("EDAD:");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdad.setBounds(53, 96, 65, 21);
		contentPane.add(lblEdad);
		
		textFieldEdad = new JTextField();
		textFieldEdad.setColumns(10);
		textFieldEdad.setBounds(130, 97, 116, 22);
		contentPane.add(textFieldEdad);
		
		JLabel lblTelefono = new JLabel("TEL\u00C9FONO:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(36, 128, 82, 21);
		contentPane.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(130, 129, 116, 22);
		contentPane.add(textFieldTelefono);
	}
}
