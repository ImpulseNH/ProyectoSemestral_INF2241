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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaAgregarBus extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldPatente;
	private JTextField textFieldServicio;

	public VentanaAgregarBus(Empresa empresa, DefaultTableModel model, JLabel lblTotal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatente = new JLabel("PATENTE:");
		lblPatente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatente.setBounds(53, 32, 65, 21);
		contentPane.add(lblPatente);
		
		textFieldPatente = new JTextField();
		textFieldPatente.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if(textFieldPatente.getText().length() == 6)
					arg0.consume();
			}
		});
		textFieldPatente.setBounds(130, 32, 116, 22);
		contentPane.add(textFieldPatente);
		textFieldPatente.setColumns(10);
		
		JLabel lblServicio = new JLabel("SERVICIO:");
		lblServicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblServicio.setBounds(53, 94, 65, 21);
		contentPane.add(lblServicio);
		
		textFieldServicio = new JTextField();
		textFieldServicio.setColumns(10);
		textFieldServicio.setBounds(130, 94, 116, 22);
		contentPane.add(textFieldServicio);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String patente = textFieldPatente.getText();
				String servicio = textFieldServicio.getText();
				servicio = servicio.toUpperCase();
				
				if(patente.length() < 6) {
					throw new CantidadIncorrectaException();
				}
				else {
					if(empresa.buscarBus(patente) == null) {
						if(servicio.equals("PREMIUM") || servicio.equals("CLASICO")) {
							Bus nuevoBus = new Bus(patente, servicio);
							
							empresa.agregarBus(nuevoBus);
							
							Tabla.agregarFilaBus(model, nuevoBus);
							lblTotal.setText("Total: " + empresa.cantidadDeBuses());
							
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe ingresar un servicio valido (PREMIUM o CLASICO)");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Ya existe un bus con esa patente");
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
	}
}
