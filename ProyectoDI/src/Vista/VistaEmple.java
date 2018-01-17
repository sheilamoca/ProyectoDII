package Vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class VistaEmple extends JPanel {

	private JPanel vBuscarEmple;
	private JPanel vListarEmple;
	private JPanel vCrearEmpleado;
	private JPanel vBorrarEmple;

	public VistaEmple() {
		// Config
		setBounds(30, 0, 830, 580);
		setLayout(null);

		// Images
		ImageIcon buscarIcon = new ImageIcon("images/lupa.png");
		ImageIcon crearIcon = new ImageIcon("images/depart.png");
		ImageIcon listarIcon = new ImageIcon("images/lista.png");
		ImageIcon borrarIcon = new ImageIcon("images/borrar.png");

		// Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 830, 550);
		mainPanel.setLayout(null);
		add(mainPanel);

		// tabPanel
		vBuscarEmple = new VBuscarEmple();
		vListarEmple = new VListarEmple();
		vCrearEmpleado = new VCrearEmpleado();
		vBorrarEmple=new VBorrarEmple();

		JTabbedPane tabPanel = new JTabbedPane();
		tabPanel.addTab("Buscar", buscarIcon, vBuscarEmple);
		tabPanel.addTab("Crear", crearIcon, vCrearEmpleado);
		tabPanel.addTab("Listar", listarIcon, vListarEmple);
		tabPanel.addTab("Borrar", borrarIcon, vBorrarEmple);
		tabPanel.setBounds(0, 0,830,510);


		tabPanel.setTabPlacement(JTabbedPane.LEFT);
		mainPanel.add("Center",tabPanel);

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			
		}

		
		setVisible(true);
	}
}
