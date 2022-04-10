package interfazGrafica;

import clases.Empresa;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public Main() {
		setTitle("Proyecto");
		setIconImage(new ImageIcon(getClass().getResource("/multimedia/icono.png")).getImage());
		setResizable(false);
	}

	public static void main(String[] args) {
		Empresa empresa = new Empresa();
		
		if(empresa.importarDatos())
			System.out.println("Datos importados con éxito!");
		else
			System.out.println("Hubo un error al importar los datos");
		
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(empresa);
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setVisible(true);
	}
}
