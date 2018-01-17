package Vista;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import Modelo.Listados;


public class VListadoDepartamentos extends JPanel {
	
	private static DefaultTableModel modelo;
	private static JTable tabla;
	public VListadoDepartamentos() {
		
		JPanel panel= new JPanel();
		 tabla= new JTable();
		JScrollPane scroll= new JScrollPane(tabla);
		
		String [] Columnas= {"Dep No","Nombre del departamento"};
		modelo= new DefaultTableModel(Columnas, 0);
		
		Listados.listadoDepart();

		
		setLayout(null);
		tabla.setLayout(null);
		tabla.setModel(modelo);
		scroll.setBounds(20,100 ,600 ,300 );

		
		JLabel lbTitulo= new JLabel("Listado de departamentos");
		lbTitulo.setFont(new Font("Courier New", Font.ITALIC, 20));
		lbTitulo.setBounds(110, 0, 300, 70);
		

		add(lbTitulo);
		add(scroll);
		
		setVisible(true);
		setSize(650, 500);

		
	}

	public static JTable getTabla() {
		return tabla;
	}

	public static void setTabla(JTable tabla) {
		VListadoDepartamentos.tabla = tabla;
	}

	public static DefaultTableModel getModelo() {
		return modelo;
	}

	public static void setModelo(DefaultTableModel modelo) {
		VListadoDepartamentos.modelo = modelo;
	}

	public static void main(String[] args) {
		
		VListadoDepartamentos listado= new VListadoDepartamentos();

	}

}
