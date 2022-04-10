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

public class VentanaEditarViaje extends Main {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtNuevoOrigen;
	private JTextField txtNuevoDestino;
	private JTextField txtNuevaHoraPartida;
	private JTextField txtNuevaHoraLlegada;
	private JTextField txtNuevoValor;

	public VentanaEditarViaje(Empresa empresa, JTable table, int row, DefaultTableModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNuevoOrigen = new JTextField();
		txtNuevoOrigen.setText("Nuevo origen");
		txtNuevoOrigen.setEnabled(false);
		txtNuevoOrigen.setBounds(22, 37, 198, 20);
		contentPane.add(txtNuevoOrigen);
		txtNuevoOrigen.setColumns(10);
		
		JRadioButton cbOrigen = new JRadioButton("Origen");
		cbOrigen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoOrigen.setEnabled(true);
					txtNuevoOrigen.setText("");
				}
				else {
					txtNuevoOrigen.setEnabled(false);
					txtNuevoOrigen.setText("Nuevo origen");
				}
			}
		});
		cbOrigen.setBounds(22, 7, 198, 23);
		contentPane.add(cbOrigen);
		
		JRadioButton cbDestino = new JRadioButton("Destino");
		cbDestino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoDestino.setEnabled(true);
					txtNuevoDestino.setText("");
				}
				else {
					txtNuevoDestino.setEnabled(false);
					txtNuevoDestino.setText("Nuevo destino");
				}
			}
		});
		cbDestino.setBounds(22, 75, 198, 23);
		contentPane.add(cbDestino);
		
		txtNuevoDestino = new JTextField();
		txtNuevoDestino.setText("Nuevo destino");
		txtNuevoDestino.setEnabled(false);
		txtNuevoDestino.setColumns(10);
		txtNuevoDestino.setBounds(22, 105, 198, 20);
		contentPane.add(txtNuevoDestino);
		
		JRadioButton cbHoraPartida = new JRadioButton("Hora de partida");
		cbHoraPartida.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevaHoraPartida.setEnabled(true);
					txtNuevaHoraPartida.setText("");
				}
				else {
					txtNuevaHoraPartida.setEnabled(false);
					txtNuevaHoraPartida.setText("Nueva hora de partida");
				}
			}
		});
		cbHoraPartida.setBounds(22, 148, 198, 23);
		contentPane.add(cbHoraPartida);
		
		txtNuevaHoraPartida = new JTextField();
		txtNuevaHoraPartida.setText("Nueva hora de partida");
		txtNuevaHoraPartida.setEnabled(false);
		txtNuevaHoraPartida.setColumns(10);
		txtNuevaHoraPartida.setBounds(22, 178, 198, 20);
		contentPane.add(txtNuevaHoraPartida);
		
		JRadioButton cbHoraLlegada = new JRadioButton("Hora de llegada");
		cbHoraLlegada.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevaHoraLlegada.setEnabled(true);
					txtNuevaHoraLlegada.setText("");
				}
				else {
					txtNuevaHoraLlegada.setEnabled(false);
					txtNuevaHoraLlegada.setText("Nueva hora de llegada");
				}
			}
		});
		cbHoraLlegada.setBounds(22, 219, 198, 23);
		contentPane.add(cbHoraLlegada);
		
		txtNuevaHoraLlegada = new JTextField();
		txtNuevaHoraLlegada.setText("Nueva hora de llegada");
		txtNuevaHoraLlegada.setEnabled(false);
		txtNuevaHoraLlegada.setColumns(10);
		txtNuevaHoraLlegada.setBounds(22, 249, 198, 20);
		contentPane.add(txtNuevaHoraLlegada);
		
		JRadioButton cbValor = new JRadioButton("Valor");
		cbValor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					txtNuevoValor.setEnabled(true);
					txtNuevoValor.setText("");
				}
				else {
					txtNuevoValor.setEnabled(false);
					txtNuevoValor.setText("Nuevo valor");
				}
			}
		});
		cbValor.setBounds(22, 289, 198, 23);
		contentPane.add(cbValor);
		
		txtNuevoValor = new JTextField();
		txtNuevoValor.setText("Nuevo valor");
		txtNuevoValor.setEnabled(false);
		txtNuevoValor.setColumns(10);
		txtNuevoValor.setBounds(22, 319, 198, 20);
		contentPane.add(txtNuevoValor);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cbOrigen.isSelected()) {
						String nuevoOrigen = txtNuevoOrigen.getText();
						if(nuevoOrigen.equals("")) {
							JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
						}
						else {
							empresa.editarOrigenViaje((Integer) table.getValueAt(row, 0), nuevoOrigen);
							Tabla.actualizarFila(model, nuevoOrigen, row, 1);
						}
					}
					if(cbDestino.isSelected()) {
						String nuevoDestino = txtNuevoDestino.getText();
						if(nuevoDestino.equals("")) {
							JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
						}
						else {
							empresa.editarDestinoViaje((Integer) table.getValueAt(row, 0), nuevoDestino);
							Tabla.actualizarFila(model, nuevoDestino, row, 2);
						}
					}
					if(cbHoraPartida.isSelected()) {
						String nuevaHoraPartida = txtNuevaHoraPartida.getText();
						if(nuevaHoraPartida.equals("")) {
							JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
						}
						else {
							empresa.editarHoraPartidaViaje((Integer) table.getValueAt(row, 0), nuevaHoraPartida);
							Tabla.actualizarFila(model, nuevaHoraPartida, row, 3);
						}
					}
					if(cbHoraLlegada.isSelected()) {
						String nuevaHoraLlegada = txtNuevaHoraLlegada.getText();
						if(nuevaHoraLlegada.equals("")) {
							JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos");
						}
						else {
							empresa.editarHoraLlegadaViaje((Integer) table.getValueAt(row, 0), nuevaHoraLlegada);
							Tabla.actualizarFila(model, nuevaHoraLlegada, row, 4);
						}
					}
					if(cbValor.isSelected()) {
						empresa.editarValorViaje((Integer) table.getValueAt(row, 0), Integer.parseInt(txtNuevoValor.getText()));
						Tabla.actualizarFila(model, "$" + txtNuevoValor.getText(), row, 5);
					}
					dispose();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido");
			    }
			}
		});
		btnConfirmar.setBounds(67, 363, 109, 20);
		contentPane.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(67, 383, 109, 23);
		contentPane.add(btnVolver);
	}
}
