package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VBorrarDepart extends JPanel {
	private static JTextField nDepart,nomDepart;
	private static JButton btnCrear;

	public static JTextField getnDepart() {
		return nDepart;
	}

	public static void setnDepart(JTextField nDepart) {
		VBorrarDepart.nDepart = nDepart;
	}

	public static JTextField getNomDepart() {
		return nomDepart;
	}

	public static void setNomDepart(JTextField nomDepart) {
		VBorrarDepart.nomDepart = nomDepart;
	}

	public static JButton getBtnCrear() {
		return btnCrear;
	}

	public static void setBtnCrear(JButton btnCrear) {
		VBorrarDepart.btnCrear = btnCrear;
	}

	public VBorrarDepart() {
		// Config
		setLayout(null);
		setBounds(600, 250, 650, 500);

		JLabel titulo = new JLabel("Borrar Departamento");
		JLabel lNum = new JLabel("Número de departamento");
		JLabel lNombre = new JLabel("Nombre del departamento:");
		

		 nDepart = new JTextField();
		nomDepart = new JTextField();

		 btnCrear = new JButton("Borrar Departamento");



		// labels
		add(titulo);
		titulo.setBounds(50, 30, 250, 15);
		titulo.setFont(new Font("Courier New", Font.ITALIC, 20));
		
		add(lNum);
		lNum.setBounds(50, 150, 175, 25);

		add(lNombre);
		lNombre.setBounds(50, 200, 175, 25);

		// jtextfield
		add(nDepart);
		nDepart.setBounds(250, 150, 250, 30);

		add(nomDepart);
		nomDepart.setBounds(250, 200, 250, 30);

		// button
		add(btnCrear);
		btnCrear.setBounds(250, 275, 150, 30);
		

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VBorrarDepart();
	}
}
