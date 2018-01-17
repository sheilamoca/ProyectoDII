package Vista;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controlador.Controller;

public class VBuscarDepart extends JPanel {
	
	private static JButton btnBuscar,btnBorrar;
	private static DefaultTableModel modelo;
	private static JTextField jtfNum;
	private static JTable tabla;
	public VBuscarDepart() {
		// Config
		setBounds(600, 250, 650, 500);
		setLayout(null);


		JLabel titulo = new JLabel("Buscar Departamento");
		JLabel lNum = new JLabel("Número del departamento:");

		String[] column = { "Número", "Nombre" };
		modelo = new DefaultTableModel(column, 0);
		tabla = new JTable(modelo);
		JScrollPane jsTabla = new JScrollPane(tabla);
		
		jtfNum = new JTextField();

		btnBuscar = new JButton("Buscar");
		btnBorrar = new JButton("Borrar");

		// label
		add(titulo);
		titulo.setBounds(50, 30, 250, 15);
		titulo.setFont(new Font("Courier New", Font.ITALIC, 20));
		
		add(lNum);
		lNum.setBounds(80, 300, 175, 25);

		// jtf
		add(jtfNum);
		jtfNum.setBounds(250, 300, 250, 30);

		// button
		add(btnBuscar);
		btnBuscar.setBounds(80, 350, 250, 30);
		
		add(btnBorrar);
		btnBorrar.setBounds(330, 350, 250, 30);

		// tabla
		add(jsTabla);
		jsTabla.setBounds(80, 100, 500, 160);

		setVisible(true);
		
		
		
		
	}

	public static JTable getTabla() {
		return tabla;
	}

	public static void setTabla(JTable tabla) {
		VBuscarDepart.tabla = tabla;
	}

	public static JTextField getJtfNum() {
		return jtfNum;
	}

	public static void setJtfNum(JTextField jtfNum) {
		VBuscarDepart.jtfNum = jtfNum;
	}

	public static JButton getBtnBuscar() {
		return btnBuscar;
	}

	public static void setBtnBuscar(JButton btnBuscar) {
		VBuscarDepart.btnBuscar = btnBuscar;
	}

	public static JButton getBtnBorrar() {
		return btnBorrar;
	}

	public static void setBtnBorrar(JButton btnBorrar) {
		VBuscarDepart.btnBorrar = btnBorrar;
	}

	public static DefaultTableModel getModelo() {
		return modelo;
	}

	public static void setModelo(DefaultTableModel modelo) {
		VBuscarDepart.modelo = modelo;
	}

	public static void setContoller(Controller c) {
		btnBorrar.addActionListener(c);
		btnBuscar.addActionListener(c);
		
	}

}
