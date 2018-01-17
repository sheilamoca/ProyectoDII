package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controlador.Controller;

public class VBorrarEmple extends JPanel {


	private JPanel panelBotones;
	private Dimension dim;

	private static JButton buttonBorrar;
	private static JTextField fieldID;
	private JLabel labelID;
	private JLabel lbEmpNo;
	private JLabel lbFechaNac;
	private JLabel lbNombre;
	private JLabel lbApellidos;
	private JLabel lbGenero;
	private JLabel lbFechaContrato;
	private JLabel lbTitulo;


	private static  JTextField empNo;
	private static  JTextField fechaNac;
	private static JTextField nombre;
	private static JTextField apellidos;
	private static JTextField genero;
	private static JTextField fecha;



	public static JButton getButtonBorrar() {
		return buttonBorrar;
	}
	public static void setButtonBorrar(JButton buttonBorrar) {
		VBorrarEmple.buttonBorrar = buttonBorrar;
	}
	public static JTextField getFieldID() {
		return fieldID;
	}
	public static void setFieldID(JTextField fieldID) {
		VBorrarEmple.fieldID = fieldID;
	}
	public static JTextField getEmpNo() {
		return empNo;
	}
	public static void setEmpNo(JTextField empNo) {
		VBorrarEmple.empNo = empNo;
	}
	public static JTextField getFechaNac() {
		return fechaNac;
	}
	public static void setFechaNac(JTextField fechaNac) {
		VBorrarEmple.fechaNac = fechaNac;
	}
	public static JTextField getNombre() {
		return nombre;
	}
	public static void setNombre(JTextField nombre) {
		VBorrarEmple.nombre = nombre;
	}
	public static JTextField getApellidos() {
		return apellidos;
	}
	public static void setApellidos(JTextField apellidos) {
		VBorrarEmple.apellidos = apellidos;
	}
	public static JTextField getGenero() {
		return genero;
	}
	public static void setGenero(JTextField genero) {
		VBorrarEmple.genero = genero;
	}
	public static JTextField getFecha() {
		return fecha;
	}
	public static void setFecha(JTextField fecha) {
		VBorrarEmple.fecha = fecha;
	}
	public VBorrarEmple() {

		dim = new Dimension(650, 500);
		panelBotones = new JPanel();

		labelID = new JLabel("Nº de empleado");
		fieldID = new JTextField("");
		buttonBorrar = new JButton("Borrar Empleado");

		lbEmpNo = new JLabel("Emp.No");
		lbFechaNac = new JLabel("Fecha de Nac.");
		lbNombre = new JLabel("Nombre");
		lbApellidos = new JLabel("Apellidos");
		lbGenero = new JLabel("Género");
		lbFechaContrato = new JLabel("Fecha Contratación");
		lbTitulo = new JLabel("Datos del empleado");
		

		empNo = new JTextField(10);
		fechaNac = new JTextField(10);
		nombre = new JTextField(10);
		apellidos = new JTextField(10);
		genero = new JTextField(10);
		fecha = new JTextField(10);
		

		add(panelBotones);
		panelBotones.setLayout(null);
		panelBotones.setBounds(0, 0, 650, 250);

		panelBotones.add(labelID);
		panelBotones.add(fieldID);
		panelBotones.add(buttonBorrar);
		panelBotones.add(lbEmpNo);
		panelBotones.add(empNo);
		panelBotones.add(lbFechaNac);
		panelBotones.add(fechaNac);
		panelBotones.add(lbNombre);
		panelBotones.add(nombre);
		panelBotones.add(lbApellidos);
		panelBotones.add(apellidos);
		panelBotones.add(lbGenero);
		panelBotones.add(genero);
		panelBotones.add(lbFechaContrato);
		panelBotones.add(fecha);
	
		
		labelID			.setBounds(100, 25,  150, 30);
		fieldID			.setBounds(200, 25,  100, 30);
		buttonBorrar	.setBounds(320, 25,  180, 30);
		lbEmpNo			.setBounds(100, 100, 70,  30);
		lbNombre		.setBounds(100, 140, 70,  30);
		lbApellidos		.setBounds(100, 180, 70,  30);
		empNo			.setBounds(160, 100, 130, 30);
		nombre			.setBounds(160, 140, 130, 30);
		apellidos		.setBounds(160, 180, 130, 30);
		lbFechaNac		.setBounds(320, 100, 110, 30);
		lbGenero		.setBounds(320, 140, 110, 30);
		lbFechaContrato	.setBounds(320, 180, 110, 30);
		fechaNac		.setBounds(440, 100, 130, 30);
		genero			.setBounds(440, 140, 40,  30);
		fecha			.setBounds(440, 180, 130, 30);
		

		setSize(dim);
		setLayout(null);
		setVisible(true);

	}
	public static void setContoller(Controller c) {
		buttonBorrar.addActionListener(c);
		
	}
}
