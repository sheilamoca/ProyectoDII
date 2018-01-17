package Vista;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controlador.Controller;
import Modelo.Listados;


public class VCrearEmpleado extends JPanel{
	private static JButton boton;
	private static JTextField empNo;
	private static JTextField fechaNac;
	private static  JTextField nombre;
	private static JTextField apellidos;
	private static JTextField fecha;
	private static JTextField depNo;
	private static JTextField cargo;
	private static JTextField encargado;
	private static JTextField salario;
	private static JComboBox combo;
	private static DefaultComboBoxModel<String> departamentos;
	private static ButtonGroup group;
	private static JRadioButton buttonM,buttonF;
	private static String gen;
	



	public VCrearEmpleado() {
		
		JPanel panel= new JPanel();
		JPanel pNombre=new JPanel();
		JPanel pFechaNac=new JPanel();
		JPanel pApellidos=new JPanel();
		JPanel pEmpNo=new JPanel();
		JPanel pGenero=new JPanel();
		JPanel pFechaCont=new JPanel();
		
		setLayout(null);
		panel.setLayout(null);
		pNombre.setLayout(null);
		pFechaNac.setLayout(null);
		pFechaCont.setLayout(null);
		pApellidos.setLayout(null);
		pEmpNo.setLayout(null);
		pGenero.setLayout(null);
		
		ImageIcon icono= new ImageIcon("images/logo.png","Logo Persona");
		JLabel lbEmpNo= new JLabel("Emp.No");
		JLabel lbFechaNac= new JLabel("Fecha de Nac.");
		JLabel lbNombre=new JLabel("Nombre");
		JLabel lbApellidos=new JLabel("Apellidos");
		JLabel lbGenero=new JLabel("Género");
		JLabel lbFechaContrato=new JLabel("Fecha Contratación");
		JLabel lbTitulo= new JLabel("Datos del empleado");
		lbTitulo.setFont(new Font("Courier New", Font.ITALIC, 20));
		JLabel lbDepNo=new JLabel("Dept.No");
		JLabel lbSalario=new JLabel("Salario");
		JLabel lbCargo=new JLabel("Puesto");
		JLabel lbEncargado=new JLabel("Encargado Dep.No");

		lbTitulo.setBounds(110, 0, 300, 70);
		
		JLabel lbicono= new JLabel(icono);
		
		 buttonM= new JRadioButton("M");
		 buttonF= new JRadioButton("F");
		
		buttonM.addActionListener(new ActionListener(){
	         
            @Override
            public void actionPerformed(ActionEvent e){
            	gen="M";
            }
        });
		
		buttonF.addActionListener(new ActionListener(){
	         
            @Override
            public void actionPerformed(ActionEvent e){
            	gen="F";
            }
        });
		
		group = new ButtonGroup();
		group.add(buttonM);
		group.add(buttonF);
		
		departamentos= new DefaultComboBoxModel<String>();
		Listados.listarDepart();
		
		combo= new JComboBox<>(departamentos);
		
		boton= new JButton("Añadir empleado");
		
		
		 empNo=new JTextField(10);
		 fechaNac=new JTextField(10);
		nombre=new JTextField(10);
		 apellidos=new JTextField(10);
		fecha=new JTextField(10);
		 depNo=new JTextField(10);
		 cargo=new JTextField(10);
		 encargado=new JTextField(10);
		 salario=new JTextField(10);
		
		panel.setBounds(0, 20, 640, 400);
		
		lbicono.setBounds(0, 70, 100,100);
		
		lbEmpNo.setBounds(110, 50, 70, 30);
		lbNombre.setBounds(110, 80,70, 30);
		lbApellidos.setBounds(110, 110, 70,30);
		lbDepNo.setBounds(110, 140, 70,30);
		lbCargo.setBounds(110,170,70, 30);
		

		
		empNo.setBounds(170, 60, 130, 17);
		nombre.setBounds(170, 90, 130, 17);
		apellidos.setBounds(170, 120, 130, 17);
		combo.setBounds(170, 150,130, 17);
		cargo.setBounds(170, 180,130, 17);
		
		lbFechaNac.setBounds(330, 50, 110, 30);
		lbGenero.setBounds(330, 80, 110, 30);
		lbFechaContrato.setBounds(330, 110, 110, 30);
		lbSalario.setBounds(330,140 ,110 , 30);
		lbEncargado.setBounds(330,170 ,110 , 30);
		
		fechaNac.setBounds(450, 60, 130, 17);
		buttonM.setBounds(450, 90, 40, 17);
		buttonF.setBounds(510, 90, 40, 17);
		fecha.setBounds(450, 120,130, 17);
		salario.setBounds(450, 150,130, 17);
		encargado.setBounds(450, 180, 130, 17);
		
		boton.setBounds(250, 230, 140, 30);
		
		panel.add(lbicono);
		panel.add(lbEmpNo);
		panel.add(empNo);
		panel.add(lbFechaNac);
		panel.add(fechaNac);
		panel.add(lbNombre);
		panel.add(nombre);
		panel.add(lbApellidos);
		panel.add(apellidos);
		panel.add(lbGenero);
		panel.add(buttonM);
		panel.add(buttonF);
		panel.add(lbFechaContrato);
		panel.add(fecha);
		panel.add(lbDepNo);
		panel.add(combo);
		panel.add(boton);
		panel.add(lbSalario);
		panel.add(lbCargo);
		panel.add(lbEncargado);
		panel.add(salario);
		panel.add(encargado);
		panel.add(cargo);
		
		setVisible(true);
		setSize(650, 500);
	
		
		add(lbTitulo);
		add(panel);
 
		
	}
	

	public static String getGen() {
		return gen;
	}



	public static void setGen(String gen) {
		VCrearEmpleado.gen = gen;
	}


	public static void setDepartamentos(DefaultComboBoxModel<String> departamentos) {
		VCrearEmpleado.departamentos = departamentos;
	}


	public static DefaultComboBoxModel<String> getDepartamentos() {
		return departamentos;
	}


	public static void setDepartamentos(String depart) {
		departamentos.addElement(depart);
	}


	public static JComboBox getCombo() {
		return combo;
	}


	public static void setCombo(JComboBox combo) {
		VCrearEmpleado.combo = combo;
	}


	public static JButton getBoton() {
		return boton;
	}
	public static void setBoton(JButton boton) {
		VCrearEmpleado.boton = boton;
	}
	public static JTextField getEmpNo() {
		return empNo;
	}
	public static void setEmpNo(JTextField empNo) {
		VCrearEmpleado.empNo = empNo;
	}
	public static JTextField getFechaNac() {
		return fechaNac;
	}
	public static void setFechaNac(JTextField fechaNac) {
		VCrearEmpleado.fechaNac = fechaNac;
	}
	public static JTextField getNombre() {
		return nombre;
	}
	public static void setNombre(JTextField nombre) {
		VCrearEmpleado.nombre = nombre;
	}
	public static JTextField getApellidos() {
		return apellidos;
	}
	public static void setApellidos(JTextField apellidos) {
		VCrearEmpleado.apellidos = apellidos;
	}
	public static JTextField getFecha() {
		return fecha;
	}
	public static void setFecha(JTextField fecha) {
		VCrearEmpleado.fecha = fecha;
	}
	public static JTextField getDepNo() {
		return depNo;
	}
	public static void setDepNo(JTextField depNo) {
		VCrearEmpleado.depNo = depNo;
	}
	public static JTextField getCargo() {
		return cargo;
	}
	public static void setCargo(JTextField cargo) {
		VCrearEmpleado.cargo = cargo;
	}
	public static JTextField getEncargado() {
		return encargado;
	}
	public static void setEncargado(JTextField encargado) {
		VCrearEmpleado.encargado = encargado;
	}
	public static JTextField getSalario() {
		return salario;
	}
	public static void setSalario(JTextField salario) {
		VCrearEmpleado.salario = salario;
	}
	

}
