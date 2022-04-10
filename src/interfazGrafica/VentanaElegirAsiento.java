package interfazGrafica;

import clases.Empresa;
import clases.Viaje;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaElegirAsiento extends Main {
	private static final long serialVersionUID = 1L;
	
	private static JFrame ventanaDatosPasajero;
	private JPanel contentPane;

	public VentanaElegirAsiento(Empresa empresa, Viaje viajeSeleccionado, int valorTotal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Elija su asiento");
		lblTitulo.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 636, 19);
		contentPane.add(lblTitulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 636, 427);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel asientosIzquierda = new JPanel();
		asientosIzquierda.setBounds(10, 11, 298, 405);
		panel_1.add(asientosIzquierda);
		asientosIzquierda.setLayout(new GridLayout(0, 2, 0, 0));
		
		llenarAsientosIzquierda(empresa, asientosIzquierda, viajeSeleccionado, valorTotal);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel asientosDerecha = new JPanel();
		asientosDerecha.setBounds(10, 11, 298, 405);
		panel_2.add(asientosDerecha);
		asientosDerecha.setLayout(new GridLayout(0, 2, 0, 0));
		
		llenarAsientosDerecha(empresa, asientosDerecha, viajeSeleccionado, valorTotal);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(10, 479, 636, 23);
		contentPane.add(btnVolver);
	}
	
	public static void cerrarVentanaDatosPasajero() {
		ventanaDatosPasajero.dispose();
	}
	
	public void llenarAsientosIzquierda(Empresa empresa, JPanel asientosIzquierda, Viaje viajeSeleccionado, int valorTotal) {
		// Desde 0 hasta la mitad de la capacidad
		for(int i = 0; i < viajeSeleccionado.getBusAsignado().getCapacidad() / 2; i++) {
			JButton asiento = new JButton(Integer.toString(i + 1));
			if(viajeSeleccionado.obtenerPasajero(i) != null)
				asiento.setEnabled(false);
			else {
				asiento.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	ventanaDatosPasajero = new VentanaDatosPasajero(empresa, viajeSeleccionado, Integer.parseInt(asiento.getText()) - 1, valorTotal);
			        	ventanaDatosPasajero.setLocationRelativeTo(null);
			        	ventanaDatosPasajero.setVisible(true);
			        }
			    });
			}
			asientosIzquierda.add(asiento);
		}
	}
	
	public void llenarAsientosDerecha(Empresa empresa, JPanel asientosDerecha, Viaje viajeSeleccionado, int valorTotal) {
		// Desde la mitad de la capacidad hasta la capacidad maxima
		for(int i = viajeSeleccionado.getBusAsignado().getCapacidad() / 2; i < viajeSeleccionado.getBusAsignado().getCapacidad(); i++) {
			JButton asiento = new JButton(Integer.toString(i + 1));
			if(viajeSeleccionado.obtenerPasajero(i) != null)
				asiento.setEnabled(false);
			else {
				asiento.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	ventanaDatosPasajero = new VentanaDatosPasajero(empresa, viajeSeleccionado, Integer.parseInt(asiento.getText()) - 1, valorTotal);
			        	ventanaDatosPasajero.setLocationRelativeTo(null);
			        	ventanaDatosPasajero.setVisible(true);
			        }
			    });
			}
			asientosDerecha.add(asiento);
		}
	}
}
