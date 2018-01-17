package Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class VistaDepart extends JPanel {

	private static  VBuscarDepart vBuscarDepart;
	private static VCrearDepart vCrearDepart;
	private static VListadoDepartamentos vListaDepart;
	public static VListadoDepartamentos getvListaDepart() {
		return vListaDepart;
	}

	public static void setvListaDepart(VListadoDepartamentos vListaDepart) {
		VistaDepart.vListaDepart = vListaDepart;
	}

	private VBorrarDepart vBorrarDepart;

	public VistaDepart() {
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
		vBuscarDepart = new VBuscarDepart();
		vCrearDepart = new VCrearDepart();
		vListaDepart = new VListadoDepartamentos();
		vBorrarDepart=new VBorrarDepart();

		JTabbedPane tabPanel = new JTabbedPane();
		tabPanel.addTab("Buscar", buscarIcon, vBuscarDepart);
		tabPanel.addTab("Crear", crearIcon, vCrearDepart);
		tabPanel.addTab("Listar", listarIcon, vListaDepart);
		tabPanel.addTab("Borrar", borrarIcon, vBorrarDepart);
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
