package interfazGrafica;

import clases.Empresa;
import clases.Cliente;
import clases.ClienteNormal;
import clases.ClienteSuscrito;
import clases.Pasajero;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDatosCliente extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldEdad;
	private JTextField textFieldTelefono;

	public VentanaDatosCliente(Empresa empresa, Pasajero pasajero, int valorTotal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 46, 56, 14);
		contentPane.add(lblEdad);
		
		JLabel lblTitulo = new JLabel("Ingrese los siguientes datos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 260, 14);
		contentPane.add(lblTitulo);
		
		textFieldEdad = new JTextField();
		textFieldEdad.setBounds(76, 43, 174, 20);
		contentPane.add(textFieldEdad);
		textFieldEdad.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(76, 102, 174, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel lblRUT = new JLabel("Teléfono");
		lblRUT.setBounds(20, 105, 46, 14);
		contentPane.add(lblRUT);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int edad = Integer.parseInt(textFieldEdad.getText());
					int telefono = Integer.parseInt(textFieldTelefono.getText());
					
					Cliente nuevoCliente;
					
					if (JOptionPane.showConfirmDialog(null, "¿Desea suscribirse al plan de clientes?\n"
														+ "Por cada compra estará acumulando puntos que podrá ocupar como descuento en compras futuras",
														"Suscripción",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						nuevoCliente = new ClienteSuscrito(pasajero.getNombre(), pasajero.getRut(), edad, telefono);
						
						empresa.agregarCliente(nuevoCliente);
						
						int puntosCompraActual = (int) (valorTotal * 0.05);
						
						JOptionPane.showMessageDialog(null, "Ya comienzas a acumular puntos!\n" +
															"Puntos acumulados por esta compra: " + puntosCompraActual);
						
						((ClienteSuscrito) nuevoCliente).setPuntosAcumulados(puntosCompraActual);
						
						procesoFinalizado(empresa);
					}
					else {
						nuevoCliente = new ClienteNormal(pasajero.getNombre(), pasajero.getRut(), edad, telefono);
						
						empresa.agregarCliente(nuevoCliente);
						
						procesoFinalizado(empresa);
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido");
			    }
			}
		});
		btnConfirmar.setBounds(92, 144, 89, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(92, 168, 89, 23);
		contentPane.add(btnVolver);
	}
	
	public void procesoFinalizado(Empresa empresa) {
		JOptionPane.showMessageDialog(null, "Su pasaje ha sido comprado con éxito!\nQue tenga buen viaje!");

		dispose();
		VentanaElegirAsiento.cerrarVentanaDatosPasajero();
		VentanaUsuario.cerrarVentanaElegirAsiento();
		VentanaPrincipal.cerrarVentanaUsuario();
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(empresa);
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setVisible(true);
	}
}
