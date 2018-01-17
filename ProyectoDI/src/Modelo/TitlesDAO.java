package Modelo;

import java.util.LinkedList;

import Persistencia.FicheTitles;

public class TitlesDAO implements Actualizable {

	public TitlesDAO() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean guardar(Object obj) {
		FicheTitles fiche= new FicheTitles();
		boolean correcto= fiche.guardar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		FicheTitles fiche = new FicheTitles();
		boolean correcto=fiche.modificar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		FicheTitles fiche= new FicheTitles();
		LinkedList<Object> list=fiche.listar();
		fiche.cerrar();
		return list;
	}

	@Override
	public boolean borrar(Object obj) {
		FicheTitles fiche= new FicheTitles();
		boolean correcto=fiche.borrar(obj);
		fiche.cerrar();
		return correcto;
	}


}
