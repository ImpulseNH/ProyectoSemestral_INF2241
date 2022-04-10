package interfazGrafica;

import clases.Empresa;
import clases.Viaje;
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

public class VentanaDatosPasajero extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldRut;

	public VentanaDatosPasajero(Empresa empresa, Viaje viajeSeleccionado, int asientoSeleccionado, int valorTotal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 46, 56, 14);
		contentPane.add(lblNombre);
		
		JLabel lblTitulo = new JLabel("Ingrese sus datos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 260, 14);
		contentPane.add(lblTitulo);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(76, 43, 174, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(76, 102, 174, 20);
		contentPane.add(textFieldRut);
		textFieldRut.setColumns(10);
		
		JLabel lblRUT = new JLabel("RUT:");
		lblRUT.setBounds(20, 105, 46, 14);
		contentPane.add(lblRUT);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String rut = textFieldRut.getText();
				
				if(nombre.equals("") || rut.equals("")) {
					JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
				}
				else {
					if(viajeSeleccionado.buscarPasajero(rut) != null) {
						JOptionPane.showMessageDialog(null, "Ya tiene un asiento comprado en este bus\n"
															+ "Verifique su RUT e inténtelo nuevamente");
					}
					else {
						Pasajero nuevoPasajero = new Pasajero(nombre, rut);
						
						viajeSeleccionado.agregarPasajero(nuevoPasajero, asientoSeleccionado);
						
						if(empresa.buscarCliente(rut) == null) {
							if (JOptionPane.showConfirmDialog(null, "¿Desea registrarse en el sistema?\nPodrá optar a beneficios como descuentos",
																	"Registro",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								VentanaDatosCliente ventanaDatosCliente = new VentanaDatosCliente(empresa, nuevoPasajero, valorTotal);
								ventanaDatosCliente.setLocationRelativeTo(null);
								ventanaDatosCliente.setVisible(true);
							}
							else {
								procesoFinalizado(empresa);
							}
						}
						else {
							if(!(empresa.buscarCliente(rut) instanceof ClienteSuscrito)) {
								if (JOptionPane.showConfirmDialog(null, "Nuestro sistema indica que usted ya es cliente pero no se ha suscrito al plan de clientes\n"
																		+ "¿Desea hacerlo ahora?\n"
																		+ "Por cada compra estará acumulando puntos que podrá ocupar como descuento en compras futuras",
																		"Suscripción",
								        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
									Cliente aux = empresa.buscarCliente(rut);
									
									Cliente clienteSuscribiendose = new ClienteSuscrito(aux.getNombre(), aux.getRut(), aux.getEdad(), aux.getTelefono());
									
									empresa.eliminarCliente(rut);
									empresa.agregarCliente(clienteSuscribiendose);
									
									int puntosCompraActual = (int) (valorTotal * 0.05);
									
									JOptionPane.showMessageDialog(null, "Ya comienzas a acumular puntos!\n" +
																		"Puntos acumulados por esta compra: " + puntosCompraActual);
									
									((ClienteSuscrito) clienteSuscribiendose).setPuntosAcumulados(puntosCompraActual);
									
									procesoFinalizado(empresa);
								}
								else {
									ClienteNormal aux = (ClienteNormal) empresa.buscarCliente(rut);
									if(aux.descuento() != 0) {
										if (JOptionPane.showConfirmDialog(null, "Un descuento es aplicable para la compra de este pasaje\n"
																				+ "¿Desea aplicarlo?", 
																				"Descuento",
												JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
											JOptionPane.showMessageDialog(null, "Descuento aplicado: " + aux.descuento() + "\n" +
																				"Valor final: $" + (valorTotal - aux.descuento()));
										}
									}
									aux.setViajesRealizados(aux.getViajesRealizados() + 1);
									
									procesoFinalizado(empresa);
								}
							}
							else {
								ClienteSuscrito aux = (ClienteSuscrito) empresa.buscarCliente(rut);
								int puntosCompraActual = (int) (valorTotal * 0.05);
								// Si los puntos acumulados son mas de 500
								if(aux.getPuntosAcumulados() >= 500) {
									if (JOptionPane.showConfirmDialog(null, "Dispone de " + aux.getPuntosAcumulados() + " puntos\n"
																			+ "¿Desea usarlos en la compra de este pasaje?", 
																			"Descuento",
											JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
										JOptionPane.showMessageDialog(null, "Puntos usados: " + aux.getPuntosAcumulados() + "\n" +
																			"Valor final: $" + Math.abs(valorTotal - aux.descuento()) + "\n" +
																			"Puntos acumulados por esta compra: " + puntosCompraActual);
									}
									else {
										JOptionPane.showMessageDialog(null, "Puntos acumulados por esta compra: " + puntosCompraActual);
										
										aux.setPuntosAcumulados(aux.getPuntosAcumulados() + puntosCompraActual);
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Puntos acumulados por esta compra: " + puntosCompraActual);
									
									aux.setPuntosAcumulados(aux.getPuntosAcumulados() + puntosCompraActual);
								}
								procesoFinalizado(empresa);
							}
						}
					}
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
		VentanaUsuario.cerrarVentanaElegirAsiento();
		VentanaPrincipal.cerrarVentanaUsuario();
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(empresa);
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setVisible(true);
	}
}
