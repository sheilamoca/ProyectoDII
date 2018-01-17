package Modelo;

import java.io.File;
import java.util.LinkedList;

import Persistencia.FicheEmpleados;

public class EmpleadosDAO implements Actualizable {


	
	public EmpleadosDAO() {
		
	}

	@Override
	public boolean guardar(Object obj) {
		FicheEmpleados fiche= new FicheEmpleados();
		boolean correcto=fiche.guardar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		FicheEmpleados fiche= new FicheEmpleados();
		boolean correcto=  fiche.modificar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		FicheEmpleados fiche= new FicheEmpleados();
		LinkedList<Object> list=fiche.listar();
		fiche.cerrar();
		return list;
	}

	@Override
	public boolean borrar(Object obj) {
		EmpleadoDTO emple= (EmpleadoDTO)obj;
		boolean correcto=BorrarEmpleado.borrarEmple(emple.getEmp_no());
		return correcto;
	}
	
	public Object buscar(int clave) {
		FicheEmpleados fiche = new FicheEmpleados();
		Object obj= fiche.buscar(clave);
		fiche.cerrar();
		return obj;
	}



	
}
