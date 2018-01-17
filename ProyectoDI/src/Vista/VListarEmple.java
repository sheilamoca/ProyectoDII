package Vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controller;
import Modelo.Listados;

public class VListarEmple extends JPanel{
	
	private JPanel panelTitulo;
	private JPanel panelTabla;
	private JLabel labelTitulo;
	private static JTable tabla;
	JScrollPane scrollPane;
	private Dimension dim;
	private static DefaultTableModel modelTable;

	public static Object[][] empleadosLista= {};
	
	

	private String[] nombreColumnas = { "Nº Emple", "Nombre", "Apellidos", "Genero", "Fecha Nac.","Fecha Cont." };

		public VListarEmple() {
			setLayout(null);
			dim = new Dimension(650, 500);
			panelTabla = new JPanel();
			panelTitulo = new JPanel();
			labelTitulo = new JLabel("LISTADO EMPLEADOS");
			modelTable=new DefaultTableModel(empleadosLista,nombreColumnas);
			tabla = new JTable(modelTable);
			scrollPane = new JScrollPane(tabla);
			tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
			tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
			tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
			tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabla.setPreferredScrollableViewportSize(dim);
			
			
			Listados.listarEmpleados();
			modelTable.fireTableDataChanged();
			add(panelTitulo);
				panelTitulo.setLayout(null);
				panelTitulo.setBounds(0,0,650,50);
				
				panelTitulo.add(labelTitulo);
					labelTitulo.setFont(new Font("Courier New",Font.ITALIC,20));
					labelTitulo.setBounds(200,0,600,50);

				
			
			add(panelTabla);
				panelTabla.setLayout(null);
				panelTabla.setBounds(0,50,650,450);
				panelTabla.add(scrollPane);
					scrollPane.setBounds(0, 0, 650, 450);		
					
					
				
					
			setSize(dim);
			setVisible(true);
		}

		public static JTable getTabla() {
			return tabla;
		}

		public static void setTabla(JTable tabla) {
			VListarEmple.tabla = tabla;
		}

		public static DefaultTableModel getModelTable() {
			return modelTable;
		}

		public static void setModelTable(DefaultTableModel modelTable) {
			VListarEmple.modelTable = modelTable;
		}
		public static Object[][] getEmpleadosLista() {
			return empleadosLista;
		}

		public static void setEmpleadosLista(Object[][] empleadosLista) {
			VListarEmple.empleadosLista = empleadosLista;
		}

}
