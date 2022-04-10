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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarBus extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtNuevaPatente;
	private JTextField txtNuevoServicio;

	public VentanaEditarBus(Empresa empresa, JTable table, int row, DefaultTableModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNuevaPatente = new JTextField();
		txtNuevaPatente.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if(txtNuevaPatente.getText().length() == 6)
					arg0.consume();
			}
		});
		txtNuevaPatente.setText("Nueva patente");
		txtNuevaPatente.setEnabled(false);
		txtNuevaPatente.setBounds(22, 37, 198, 20);
		contentPane.add(txtNuevaPatente);
		txtNuevaPatente.setColumns(10);
		
		JRadioButton cbPatente = new JRadioButton("Patente");
		cbPatente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevaPatente.setEnabled(true);
					txtNuevaPatente.setText("");
				}
				else {
					txtNuevaPatente.setEnabled(false);
					txtNuevaPatente.setText("Nueva patente");
				}
			}
		});
		cbPatente.setBounds(22, 7, 198, 23);
		contentPane.add(cbPatente);
		
		JRadioButton cbServicio = new JRadioButton("Servicio");
		cbServicio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoServicio.setEnabled(true);
					txtNuevoServicio.setText("");
				}
				else {
					txtNuevoServicio.setEnabled(false);
					txtNuevoServicio.setText("Nuevo servicio");
				}
			}
		});
		cbServicio.setBounds(22, 75, 198, 23);
		contentPane.add(cbServicio);
		
		txtNuevoServicio = new JTextField();
		txtNuevoServicio.setText("Nuevo servicio");
		txtNuevoServicio.setEnabled(false);
		txtNuevoServicio.setColumns(10);
		txtNuevoServicio.setBounds(22, 105, 198, 20);
		contentPane.add(txtNuevoServicio);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbPatente.isSelected()) {
					if(txtNuevaPatente.getText().length() < 6)
						JOptionPane.showMessageDialog(null, "La patente debe tener 6 caracteres");
					else {
						if(empresa.buscarBus(txtNuevaPatente.getText()) == null) {
							empresa.editarPatenteBus((String) table.getValueAt(row, 0), txtNuevaPatente.getText());
							Tabla.actualizarFila(model, txtNuevaPatente.getText(), row, 0);
						}
						else
							JOptionPane.showMessageDialog(null, "Ya existe una bus con esa patente");
					}
				}
				if(cbServicio.isSelected()) {
					String nuevoServicio = txtNuevoServicio.getText();
					nuevoServicio = nuevoServicio.toUpperCase();
					
					if(nuevoServicio.equals("PREMIUM") || nuevoServicio.equals("CLASICO")) {
						if(nuevoServicio.equals(table.getValueAt(row, 1)))
							JOptionPane.showMessageDialog(null, "El serivicio actual ya corresponde al que intenta agregar");
						else {
							empresa.editarServicioBus((String) table.getValueAt(row, 0), nuevoServicio);
							Tabla.actualizarFila(model, nuevoServicio, row, 1);
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Debe ingresar un servicio valido (PREMIUM o CLASICO)");
				}
				dispose();
			}
		});
		btnConfirmar.setBounds(65, 161, 109, 20);
		contentPane.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(65, 181, 109, 23);
		contentPane.add(btnVolver);
	}
}
