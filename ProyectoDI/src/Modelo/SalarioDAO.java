package Modelo;

import java.util.LinkedList;

import Persistencia.FicheSalarios;

public class SalarioDAO implements Actualizable{

	public SalarioDAO() {
		
	}
	
	public boolean guardar(Object obj) {
		FicheSalarios fiche= new FicheSalarios();
		boolean correcto=fiche.guardar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		FicheSalarios fiche= new FicheSalarios();
		boolean correcto=fiche.modificar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		FicheSalarios fiche= new FicheSalarios();
		LinkedList<Object> list=fiche.listar();
		fiche.cerrar();
		return list;
	}

	@Override
	public boolean borrar(Object obj) {
		FicheSalarios fiche= new FicheSalarios();
		boolean correcto=fiche.borrar(obj);
		fiche.cerrar();
		return correcto;
	}


}
