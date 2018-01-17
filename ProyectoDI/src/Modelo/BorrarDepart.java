package Modelo;

import java.util.LinkedList;

import Persistencia.FicheDepartamentoEmpleado;
import Persistencia.FicheDepartamentos;
import Persistencia.FicheEmpleados;

public class BorrarDepart {
	
	private static LinkedList<Integer> buscarEmplesDepart(String depart) {
		LinkedList<Integer> lista = new LinkedList<>();
		boolean correcto=false;
		
		FicheDepartamentoEmpleado fiche= new FicheDepartamentoEmpleado();
		LinkedList<Object> list=fiche.listar();
		for(int i=0;i<list.size();i++) {
			Dept_empDTO emp=(Dept_empDTO)list.get(i);
			if(emp.getDept_no().equals(depart)) {
				lista.add(emp.getEmp_no());
				correcto=true;
			}
		}
		fiche.cerrar();
		return lista;
	}
	
	private static boolean borrarEmpleados(LinkedList<Integer> empleados) {
		boolean correcto=false;
		if(!empleados.isEmpty()) {
		for(int i=0; i<empleados.size();i++) {
			BorrarEmpleado bEmple= new BorrarEmpleado();
			bEmple.borrarEmple(empleados.get(i));
			correcto=true;
		}
		}
		return correcto;
	}
	
	public static boolean borrarDepart(String depart) {
		borrarEmpleados(buscarEmplesDepart(depart));
		FicheDepartamentos f= new FicheDepartamentos();
		boolean correcto=f.borrar(f.buscar(depart));
		f.cerrar();
		return correcto;
		
	}

}
