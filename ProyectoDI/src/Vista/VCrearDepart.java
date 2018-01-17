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

import Controlador.Controller;

public class VCrearDepart extends JPanel {
	
	 private static JButton btnCrear;
	 private static JTextField nomDepart ;
	 private static 	JTextField nDepart ;

	public VCrearDepart() {
		// Config
		setLayout(null);
		setBounds(600, 250, 650, 500);
		
		JLabel titulo = new JLabel("Crear Departamento");
		JLabel lNum = new JLabel("Número del departamento:");
		JLabel lNombre = new JLabel("Nombre del departamento:");

		nDepart = new JTextField();
		nomDepart = new JTextField();

		 btnCrear = new JButton("Crear Departamento");

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
	
	public static JButton getBtnCrear() {
		return btnCrear;
	}

	public static void setBtnCrear(JButton btnCrear) {
		VCrearDepart.btnCrear = btnCrear;
	}

	public static JTextField getNomDepart() {
		return nomDepart;
	}

	public static void setNomDepart(JTextField nomDepart) {
		VCrearDepart.nomDepart = nomDepart;
	}

	public static JTextField getnDepart() {
		return nDepart;
	}

	public static void setnDepart(JTextField nDepart) {
		VCrearDepart.nDepart = nDepart;
	}

	public static void setContoller(Controller c) {
		btnCrear.addActionListener(c);
		
	}
	

	public static void main(String[] args) {
		new VCrearDepart();
	}
}
