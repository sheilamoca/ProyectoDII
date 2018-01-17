package Modelo;

import java.util.LinkedList;

import Persistencia.FicheDepartamentoEmpleado;
import Persistencia.FicheDepartamentoManager;
import Persistencia.FicheEmpleados;
import Persistencia.FicheSalarios;
import Persistencia.FicheTitles;

public class BorrarEmpleado {

	public static boolean borrarEmple(int emp_no) {
		boolean salario, cargo, emp, manager, empleado;

		salario = borrarSalario(emp_no);
		cargo = borrarCargo(emp_no);
		emp = borrarDept_emp(emp_no);
		manager = borrarDept_manager(emp_no);
		empleado = borrarEmpleado(emp_no);

		return salario && cargo && emp && manager && empleado;
	}

	private static boolean borrarEmpleado(int emp_no) {
		boolean correcto = false;
		FicheEmpleados fiche = new FicheEmpleados();
		LinkedList<Object> list = fiche.listar();
		for (int i = 0; i < list.size(); i++) {
			EmpleadoDTO emp = (EmpleadoDTO) list.get(i);
			if (emp.getEmp_no() == emp_no) {
				fiche.borrar(emp);
				correcto = true;
			}
		}
		fiche.cerrar();
		return correcto;

	}

	private static boolean borrarSalario(int emp_no) {
		boolean correcto = false;
		FicheSalarios fiche = new FicheSalarios();
		LinkedList<Object> list = fiche.listar();
		for (int i = 0; i < list.size(); i++) {
			SalarioDTO emp = (SalarioDTO) list.get(i);
			if (emp.getEmp_no() == emp_no) {
				fiche.borrar(emp);
				correcto = true;
			}
		}
		fiche.cerrar();
		return correcto;
	}

	private static boolean borrarCargo(int emp_no) {
		boolean correcto = false;
		FicheTitles fiche = new FicheTitles();
		LinkedList<Object> list = fiche.listar();
		for (int i = 0; i < list.size(); i++) {
			TitlesDTO emp = (TitlesDTO) list.get(i);
			if (emp.getEmp_no() == emp_no) {
				fiche.borrar(emp);
				correcto = true;
			}
		}
		fiche.cerrar();
		return correcto;
	}

	private static boolean borrarDept_emp(int emp_no) {
		boolean correcto = false;
		FicheDepartamentoEmpleado fiche = new FicheDepartamentoEmpleado();
		LinkedList<Object> list = fiche.listar();
		for (int i = 0; i < list.size(); i++) {
			Dept_empDTO emp = (Dept_empDTO) list.get(i);
			if (emp.getEmp_no() == emp_no) {
				fiche.borrar(emp);
				correcto = true;
			}
		}
		fiche.cerrar();
		return false;
	}

	private static boolean borrarDept_manager(int emp_no) {
		boolean correcto = false;
		FicheDepartamentoManager fiche = new FicheDepartamentoManager();
		LinkedList<Object> list = fiche.listar();
		for (int i = 0; i < list.size(); i++) {
			Dept_ManagerDTO emp = (Dept_ManagerDTO) list.get(i);
			if (emp.getEmp_no() == emp_no) {
				fiche.borrar(emp);
				correcto = true;
			}
		}
		fiche.cerrar();
		return correcto;
	}

}
