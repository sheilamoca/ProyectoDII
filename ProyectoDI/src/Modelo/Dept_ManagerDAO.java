package Modelo;

import java.util.LinkedList;

import Persistencia.FicheDepartamentoManager;

public class Dept_ManagerDAO implements Actualizable {

	public Dept_ManagerDAO() {

	}

	public boolean guardar(Object obj) {
		FicheDepartamentoManager fiche = new FicheDepartamentoManager();
		boolean correcto = fiche.guardar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		FicheDepartamentoManager fiche = new FicheDepartamentoManager();
		boolean correcto = fiche.modificar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		FicheDepartamentoManager fiche = new FicheDepartamentoManager();
		LinkedList<Object> list = fiche.listar();
		fiche.cerrar();
		return list;
	}

	@Override
	public boolean borrar(Object obj) {
		FicheDepartamentoManager fiche = new FicheDepartamentoManager();
		boolean correcto = fiche.borrar(obj);
		fiche.cerrar();
		return correcto;
	}

}
