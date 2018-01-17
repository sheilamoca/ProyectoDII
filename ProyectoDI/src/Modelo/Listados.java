package Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import Vista.VCrearEmpleado;
import Vista.VListadoDepartamentos;
import Vista.VListarEmple;

public class Listados {
	
	private static String sacarFecha(GregorianCalendar fecha) {
		String f;
		 f=fecha.get(Calendar.DAY_OF_MONTH)+"/"+ (fecha.get(Calendar.MONTH) + 1)+"/"+ fecha.get(Calendar.YEAR);
		if(f.equals("31/12/2")) {
			f="dd/mm/aaaa";
		}
		return f;
	}

	
	private static GregorianCalendar convertirFecha(String fecha) {
		GregorianCalendar f;
		String[] datos=fecha.split("/");
		if(fecha.equals("")) {
			f= new GregorianCalendar(0,0,0);
		}else {
		f= new GregorianCalendar(Integer.parseInt(datos[2]),Integer.parseInt(datos[1])+1,Integer.parseInt(datos[0]));
		}
		return f;
		
	}
	

	public static void listarDepart() {
		DepartamentosDAO depar= new DepartamentosDAO();
		LinkedList<Object> list=  depar.listar();
		for(int i=0;i<list.size();i++) {
			DepartamentoDTO dep=(DepartamentoDTO)list.get(i);
			VCrearEmpleado.setDepartamentos(dep.getDept_no());
		}
		
		
	}
	
	public static void listarEmpleados() {
		EmpleadosDAO empleados= new EmpleadosDAO();
		LinkedList<Object> list=  empleados.listar();
		for(int i=0;i<list.size();i++) {
			EmpleadoDTO emple= (EmpleadoDTO) list.get(i);
			String[] emp= {String.valueOf(emple.getEmp_no()),emple.getFirst_name(),emple.getLast_name(),emple.getGenero().toString(),
							sacarFecha(emple.getBirth_date()),sacarFecha(emple.getHire_date())};
			VListarEmple.getModelTable().addRow(emp);	
			}
			
			
			}
	
	public static void listadoDepart() {
		DepartamentosDAO depar= new DepartamentosDAO();
		LinkedList<Object> list=  depar.listar();
		for(int i=0;i<list.size();i++) {
			DepartamentoDTO dep=(DepartamentoDTO)list.get(i);
			String[] depart= {dep.getDept_no(),dep.getDept_name()};
			VListadoDepartamentos.getModelo().addRow(depart);
		}
		
	}

	public static void borrarTablaEmple() {
		
		while (VListarEmple.getModelTable().getRowCount() > 0) 
		{
			VListarEmple.getModelTable().removeRow(0);
		}
	}
	
	public static void borrarTablaDepart() {
		while (VListadoDepartamentos.getModelo().getRowCount() > 0) 
		{
			VListadoDepartamentos.getModelo().removeRow(0);
		}
		
	}
	
	
	
	}


