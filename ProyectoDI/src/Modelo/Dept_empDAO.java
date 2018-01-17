package Modelo;

import java.util.LinkedList;

import Persistencia.FicheDepartamentoEmpleado;

public class Dept_empDAO implements Actualizable {

	public Dept_empDAO() {

	}

	@Override
	public boolean guardar(Object obj) {
		FicheDepartamentoEmpleado fiche = new FicheDepartamentoEmpleado();
		boolean correcto = fiche.guardar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		FicheDepartamentoEmpleado fiche = new FicheDepartamentoEmpleado();
		boolean correcto = fiche.modificar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		FicheDepartamentoEmpleado fiche = new FicheDepartamentoEmpleado();
		LinkedList<Object> list = fiche.listar();
		fiche.cerrar();
		return list;
	}

	@Override
	public boolean borrar(Object obj) {
		FicheDepartamentoEmpleado fiche = new FicheDepartamentoEmpleado();
		boolean correcto = fiche.borrar(obj);
		fiche.cerrar();
		return correcto;
	}

}
