package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Modelo.BorrarDepart;
import Modelo.DepartamentoDTO;
import Modelo.DepartamentosDAO;
import Modelo.Dept_ManagerDAO;
import Modelo.Dept_ManagerDTO;
import Modelo.Dept_empDAO;
import Modelo.Dept_empDTO;
import Modelo.EmpleadoDTO;
import Modelo.EmpleadosDAO;
import Modelo.Gender;
import Modelo.Listados;
import Modelo.SalarioDAO;
import Modelo.SalarioDTO;
import Persistencia.FicheEmpleados;
import Vista.VBorrarDepart;
import Vista.VBorrarEmple;
import Vista.VBuscarDepart;
import Vista.VBuscarEmple;
import Vista.VCrearDepart;
import Vista.VCrearEmpleado;
import Vista.VListadoDepartamentos;
import Vista.VListarEmple;
import Vista.VProyecto;
import Vista.VistaDepart;

public class Controller implements ActionListener, MouseListener {

	private int filaTabla;

	private static void mensajes(String msg) {
		JOptionPane mensaje = new JOptionPane();
		mensaje.showMessageDialog(new JFrame(), msg, "Información", JOptionPane.INFORMATION_MESSAGE);

	}

	private static String stringBuffer(String str, int tamaño) {
		StringBuffer bf = new StringBuffer(str);
		bf.setLength(tamaño);
		return bf.toString();
	}

	private static String sacarFecha(GregorianCalendar fecha) {
		String f;
		f = fecha.get(Calendar.DAY_OF_MONTH) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR);
		if (f.equals("31/12/2")) {
			f = "dd/mm/aaaa";
		}
		return f;
	}

	private static GregorianCalendar convertirFecha(String fecha) {
		GregorianCalendar f;
		String[] datos = fecha.split("/");
		if (fecha.equals("")) {
			f = new GregorianCalendar(0, 0, 0);
		} else {
			f = new GregorianCalendar(Integer.parseInt(datos[2]), Integer.parseInt(datos[1]) - 1,
					Integer.parseInt(datos[0]));
		}
		return f;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Buscar Empleado")) {
			try {
				int nEmple = Integer.parseInt(VBuscarEmple.getFieldID().getText());
				EmpleadosDAO empledao = new EmpleadosDAO();
				EmpleadoDTO emple = (EmpleadoDTO) empledao.buscar(nEmple);
				VBuscarEmple.getEmpNo().setText(String.valueOf(emple.getEmp_no()));
				VBuscarEmple.getNombre().setText(emple.getFirst_name());
				VBuscarEmple.getApellidos().setText(emple.getLast_name());
				VBuscarEmple.getFechaNac().setText(sacarFecha(emple.getBirth_date()));
				VBuscarEmple.getGenero().setText(emple.getGenero().toString());
				VBuscarEmple.getFecha().setText(sacarFecha(emple.getHire_date()));

			} catch (NumberFormatException nu) {
				mensajes("Valor erroneo. Introduzca un número");
			}

		} else if (e.getActionCommand().equals("Añadir empleado")) {
			EmpleadosDAO empledao = new EmpleadosDAO();
			EmpleadoDTO emple = new EmpleadoDTO();

			emple.setEmp_no(Integer.parseInt(VCrearEmpleado.getEmpNo().getText()));
			String nombre = VCrearEmpleado.getNombre().getText();
			String ape = VCrearEmpleado.getApellidos().getText();
			emple.setFirst_name(stringBuffer(nombre, 14));
			emple.setLast_name(stringBuffer(ape, 16));
			emple.setBirth_date(convertirFecha(VCrearEmpleado.getFechaNac().getText()));
			if (VCrearEmpleado.getGen().equals("F")) {
				emple.setGenero(Gender.F);
			} else {
				emple.setGenero(Gender.M);
			}

			emple.setHire_date(convertirFecha(VCrearEmpleado.getFecha().getText()));
			if (empledao.guardar(emple)) {
				mensajes("Se ha guardado correctamente");
			} else {
				mensajes("No se ha guardado correctamente");
			}

			SalarioDTO salario = new SalarioDTO();
			SalarioDAO salariodao = new SalarioDAO();
			salario.setEmp_no(emple.getEmp_no());
			salario.setSalary(Integer.parseInt(VCrearEmpleado.getSalario().getText()));
			salario.setFrom_date(emple.getHire_date());
			salario.setTo_date(convertirFecha(""));
			salariodao.guardar(salario);

			Dept_empDAO dept = new Dept_empDAO();
			Dept_empDTO d_em = new Dept_empDTO();

			d_em.setDept_no(VCrearEmpleado.getCombo().getSelectedItem().toString());
			d_em.setEmp_no(emple.getEmp_no());
			d_em.setFrom_date(emple.getHire_date());
			d_em.setTo_date(convertirFecha(""));
			dept.guardar(d_em);

			Dept_ManagerDAO depart = new Dept_ManagerDAO();
			Dept_ManagerDTO d_man = new Dept_ManagerDTO();

			d_man.setDept_no(VCrearEmpleado.getCombo().getSelectedItem().toString());
			d_man.setEmp_no(emple.getEmp_no());
			d_man.setFrom_date(emple.getHire_date());
			d_man.setTo_date(convertirFecha(""));
			depart.guardar(d_man);
			Listados.borrarTablaEmple();
			Listados.listarEmpleados();

		} else if (e.getActionCommand().equals("Borrar Empleado")) {
			try {
				int nEmple = Integer.parseInt(VBorrarEmple.getFieldID().getText());
				EmpleadosDAO empledao = new EmpleadosDAO();
				EmpleadoDTO emple = (EmpleadoDTO) empledao.buscar(nEmple);
				VBorrarEmple.getEmpNo().setText(String.valueOf(emple.getEmp_no()));
				VBorrarEmple.getNombre().setText(emple.getFirst_name());
				VBorrarEmple.getApellidos().setText(emple.getLast_name());
				VBorrarEmple.getFechaNac().setText(sacarFecha(emple.getBirth_date()));
				VBorrarEmple.getGenero().setText(emple.getGenero().toString());
				VBorrarEmple.getFecha().setText(sacarFecha(emple.getHire_date()));

				if (empledao.borrar(emple)) {
					mensajes("El empleado ha sido borrado");
				} else {
					mensajes("El empleado no ha sido borrado");
				}
			} catch (NumberFormatException nu) {
				mensajes("Valor erroneo. Introduzca un número");
			}
			Listados.borrarTablaEmple();
			Listados.listarEmpleados();

		} else if (e.getActionCommand().equals("Buscar")) {

			String depno = VBuscarDepart.getJtfNum().getText();

			DepartamentosDAO depart = new DepartamentosDAO();
			String clave = stringBuffer(depno, 4);
			DepartamentoDTO dep = (DepartamentoDTO) depart.buscar(clave);
			System.out.println(dep.getDept_name());
			String[] dato = { dep.getDept_no(), dep.getDept_name() };
			VBuscarDepart.getModelo().addRow(dato);

		} else if (e.getActionCommand().equals("Borrar")) {

			String dept_no = VBuscarDepart.getTabla().getValueAt(filaTabla, 0).toString();
			String dept_name = VBuscarDepart.getTabla().getValueAt(filaTabla, 1).toString();
			DepartamentoDTO dep = new DepartamentoDTO(dept_no, dept_name);
			DepartamentosDAO depart = new DepartamentosDAO();
			depart.borrar(dep);
			mensajes("Departamento " + dep.getDept_name() + " Borrado");
			Listados.borrarTablaDepart();
			Listados.listadoDepart();

		} else if (e.getActionCommand().equals("Crear Departamento")) {
			DepartamentosDAO departamentosDAO = new DepartamentosDAO();
			DepartamentoDTO departamentoDTO = new DepartamentoDTO();

			String numDept = VCrearDepart.getnDepart().getText();
			String nombreDept = VCrearDepart.getNomDepart().getText();

			departamentoDTO.setDept_no(stringBuffer(numDept, 4));
			departamentoDTO.setDept_name(stringBuffer(nombreDept, 40));

			if(departamentosDAO.guardar(departamentoDTO)){
				mensajes("Departamento " + departamentoDTO.getDept_name() + " Creado");
			}else {
				mensajes("Departamento " + departamentoDTO.getDept_name() + " no ha sido creado");
			}
			
			Listados.borrarTablaDepart();
			Listados.listadoDepart();

		} else if (e.getActionCommand().equals("Borrar Departamento")) {
			DepartamentosDAO departamentosDAO = new DepartamentosDAO();
			DepartamentoDTO departamentoDTO = new DepartamentoDTO();

			String numDept = VBorrarDepart.getnDepart().getText();
			String nombreDept = VBorrarDepart.getNomDepart().getText();

			departamentoDTO.setDept_no(stringBuffer(numDept, 4));
			departamentoDTO.setDept_name(stringBuffer(nombreDept, 40));
			BorrarDepart.borrarDepart(numDept);
			departamentosDAO.borrar(departamentoDTO);
			mensajes("Departamento " + departamentoDTO.getDept_name() + " Borrado");
			Listados.borrarTablaDepart();
			Listados.listadoDepart();

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		filaTabla = VBuscarDepart.getTabla().getSelectedRow();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		
		VProyecto proyecto=new VProyecto();
		Controller c= new Controller();
		proyecto.setContoller(c);

	}

	
}
