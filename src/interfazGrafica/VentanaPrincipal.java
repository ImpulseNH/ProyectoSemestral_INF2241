package interfazGrafica;

import clases.Empresa;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends Main {
	private static final long serialVersionUID = 1L;
	
	private static JFrame ventanaUsuario;
	private JPanel contentPane;

	public VentanaPrincipal(Empresa empresa) {
		setTitle("Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdministracion ventanaAdministracion = new VentanaAdministracion(empresa);
				dispose();
				ventanaAdministracion.setLocationRelativeTo(null);
				ventanaAdministracion.setVisible(true);
			}
		});
		btnAdministrar.setBounds(80, 352, 182, 23);
		panel.add(btnAdministrar);
		
		JLabel lblBienvenida = new JLabel("Bienvenido!");
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 24));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(10, 11, 322, 44);
		panel.add(lblBienvenida);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon wallpaper = new ImageIcon(new ImageIcon(VentanaPrincipal.class.getResource("/multimedia/bus.jpg")).getImage().getScaledInstance(600, 421, Image.SCALE_DEFAULT));
		lblNewLabel_1.setIcon(wallpaper);
		lblNewLabel_1.setBounds(0, 0, 347, 421);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHeader = new JLabel("\u00BFD\u00F3nde quieres viajar?");
		lblHeader.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 18));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(10, 11, 322, 44);
		panel_1.add(lblHeader);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrigen.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 15));
		lblOrigen.setBounds(10, 87, 322, 30);
		panel_1.add(lblOrigen);
		
		JComboBox<String> comboBoxOrigen = new JComboBox<String>();
		comboBoxOrigen.setBounds(80, 118, 182, 20);
		llenarComboBoxOrigen(empresa, comboBoxOrigen);
		panel_1.add(comboBoxOrigen);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestino.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 15));
		lblDestino.setBounds(10, 169, 322, 30);
		panel_1.add(lblDestino);
		
		JComboBox<String> comboBoxDestino = new JComboBox<String>();
		// Accion que hace que se filtre el combo box del destino segun el origen que se haya seleccionado
		comboBoxOrigen.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   llenarComboBoxDestino(empresa, comboBoxDestino, comboBoxOrigen);
			   }
			});
		comboBoxDestino.setBounds(80, 200, 182, 20);
		panel_1.add(comboBoxDestino);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(80, 352, 182, 23);
		panel_1.add(btnSalir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setToolTipText("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				if(comboBoxDestino.getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(null, "Debe seleccionar un origen y un destino");
				else {
					String origen = (String) comboBoxOrigen.getSelectedItem();
					String destino = (String) comboBoxDestino.getSelectedItem();
					
					ventanaUsuario = new VentanaUsuario(empresa, empresa.filtrarViajesPorTrayecto(origen, destino));
					dispose();
					ventanaUsuario.setLocationRelativeTo(null);
					ventanaUsuario.setVisible(true);
				}
			}
		});
		btnBuscar.setBounds(131, 245, 87, 23);
		panel_1.add(btnBuscar);
	}
	
	public static void cerrarVentanaUsuario() {
		ventanaUsuario.dispose();
	}
	
	// Se llena el combo box del origen sin repetir origenes
	private void llenarComboBoxOrigen(Empresa empresa, JComboBox<String> comboBoxOrigen) {
		for(int i = 0; i < empresa.cantidadDeViajes(); i++) {
			if(((DefaultComboBoxModel<String>)comboBoxOrigen.getModel()).getIndexOf(empresa.obtenerViaje(i).getOrigen()) == -1)
				comboBoxOrigen.addItem(empresa.obtenerViaje(i).getOrigen());
		}
	}
	
	// Se llena el combo box del destino y se filtra segun el origen seleccionado, esta accion ocurre cuando se cambia de origen
	private void llenarComboBoxDestino(Empresa empresa, JComboBox<String> comboBoxDestino, JComboBox<String> comboBoxOrigen) {
		   comboBoxDestino.removeAllItems();
		   for(int i = 0; i < empresa.cantidadDeViajes(); i++) {
			   if(empresa.obtenerViaje(i).getOrigen().equals(comboBoxOrigen.getSelectedItem()))
				   if(((DefaultComboBoxModel<String>)comboBoxDestino.getModel()).getIndexOf(empresa.obtenerViaje(i).getDestino()) == -1)
					   comboBoxDestino.addItem(empresa.obtenerViaje(i).getDestino());
		   }
	}
}
