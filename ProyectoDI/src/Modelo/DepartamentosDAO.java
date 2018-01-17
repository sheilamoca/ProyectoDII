package Modelo;

import java.util.LinkedList;

import Persistencia.FicheDepartamentos;
import Persistencia.FicheEmpleados;


public class DepartamentosDAO implements Actualizable {
	
	public DepartamentosDAO() {
		
	}

	@Override
	public boolean guardar(Object obj) {
		FicheDepartamentos fiche= new FicheDepartamentos();
		boolean correcto=fiche.guardar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		FicheDepartamentos fiche= new FicheDepartamentos();
		boolean correcto=fiche.modificar(obj);
		fiche.cerrar();
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		FicheDepartamentos fiche= new FicheDepartamentos();
		LinkedList<Object> list=fiche.listar();
		fiche.cerrar();
		return list;
	}

	@Override
	public boolean borrar(Object obj) {
		DepartamentoDTO dep=(DepartamentoDTO)obj;
		 BorrarDepart.borrarDepart(dep.getDept_no());
		 boolean correcto=BorrarDepart.borrarDepart(dep.getDept_no());
		 return correcto;
	}

	public Object buscar(String clave) {
		FicheDepartamentos fiche= new FicheDepartamentos();
		Object obj=fiche.buscar(clave);
		fiche.cerrar();
		return obj;
	}



	


}
