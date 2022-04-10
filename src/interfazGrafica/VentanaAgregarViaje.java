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

public class VentanaAgregarViaje extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldOrigen;
	private JTextField textFieldDestino;
	private JLabel lblHoraPartida;
	private JLabel lblHoraLlegada;
	private JTextField textFieldHoraPartida;
	private JTextField textFieldHoraLlegada;
	private JLabel lblValor;
	private JTextField textFieldValor;

	public VentanaAgregarViaje(Empresa empresa, int id, DefaultTableModel model, JLabel lblTotal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldOrigen = new JTextField();
		textFieldOrigen.setBounds(187, 12, 116, 22);
		contentPane.add(textFieldOrigen);
		textFieldOrigen.setColumns(10);
		
		JLabel lblOrigen = new JLabel("ORIGEN:");
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrigen.setBounds(53, 13, 65, 21);
		contentPane.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("DESTINO:");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestino.setBounds(53, 47, 65, 21);
		contentPane.add(lblDestino);
		
		textFieldDestino = new JTextField();
		textFieldDestino.setColumns(10);
		textFieldDestino.setBounds(187, 47, 116, 22);
		contentPane.add(textFieldDestino);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String origen = textFieldOrigen.getText();
					String destino = textFieldDestino.getText();
					String horaPartida = textFieldHoraPartida.getText();
					String horaLlegada = textFieldHoraLlegada.getText();
					int valor = Integer.parseInt(textFieldValor.getText());
					
					if(origen.equals("") || destino.equals("") || horaPartida.equals("") || horaLlegada.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
					}
					else {
						Viaje nuevoViaje = new Viaje(id, origen, destino, horaPartida, horaLlegada, valor);
						
						empresa.agregarViaje(nuevoViaje);
						
						Tabla.agregarFilaViaje(model, nuevoViaje);
						lblTotal.setText("Total: " + empresa.cantidadDeViajes());
						
						dispose();
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido");
			    }
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAgregar.setBounds(157, 200, 97, 25);
		contentPane.add(btnAgregar);
		
		lblHoraPartida = new JLabel("HORA DE PARTIDA:");
		lblHoraPartida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHoraPartida.setBounds(53, 81, 128, 16);
		contentPane.add(lblHoraPartida);
		
		lblHoraLlegada = new JLabel("HORA DE LLEGADA:");
		lblHoraLlegada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHoraLlegada.setBounds(53, 118, 128, 16);
		contentPane.add(lblHoraLlegada);
		
		textFieldHoraPartida = new JTextField();
		textFieldHoraPartida.setColumns(10);
		textFieldHoraPartida.setBounds(187, 82, 116, 22);
		contentPane.add(textFieldHoraPartida);
		
		textFieldHoraLlegada = new JTextField();
		textFieldHoraLlegada.setColumns(10);
		textFieldHoraLlegada.setBounds(187, 115, 116, 22);
		contentPane.add(textFieldHoraLlegada);
		
		lblValor = new JLabel("VALOR:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValor.setBounds(53, 150, 128, 16);
		contentPane.add(lblValor);
		
		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(187, 147, 116, 22);
		contentPane.add(textFieldValor);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(157, 226, 97, 23);
		contentPane.add(btnVolver);
	}
}
