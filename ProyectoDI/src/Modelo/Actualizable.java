package Modelo;

import java.util.LinkedList;

public interface Actualizable {

	public boolean guardar(Object obj);
	
	public boolean modificar(Object obj);
	
	public LinkedList<Object> listar();

	public boolean borrar(Object obj);
	
}
