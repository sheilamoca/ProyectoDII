package Persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import Modelo.Actualizable;
import Modelo.SalarioDTO;

public class FicheSalarios implements Actualizable {

	private static RandomAccessFile file;
	private static final int FICHBYTES = 32;

	public FicheSalarios() {
		File fichero = new File("FicheSalarios.dat");
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
			}

			file = new RandomAccessFile(fichero, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean guardarObj(Object obj) {
		boolean correcto;
		SalarioDTO salario = (SalarioDTO) obj;
		try {
			file.writeInt(salario.getEmp_no());
			file.writeInt(salario.getSalary());
			GregorianCalendar fromDate = salario.getFrom_date();
			file.writeInt(fromDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fromDate.get(Calendar.MONTH) + 1));
			file.writeInt(fromDate.get(Calendar.YEAR));
			GregorianCalendar toDate = salario.getTo_date();
			file.writeInt(toDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((toDate.get(Calendar.MONTH) + 1));
			file.writeInt(toDate.get(Calendar.YEAR));
			correcto = true;
		} catch (IOException e) {
			e.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	@Override
	public boolean guardar(Object obj) {
		boolean correcto;
		SalarioDTO salario = (SalarioDTO) obj;
		try {
			long pos = file.length();
			file.seek(pos);
			file.writeInt(salario.getEmp_no());
			file.writeInt(salario.getSalary());
			GregorianCalendar fromDate = salario.getFrom_date();
			file.writeInt(fromDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fromDate.get(Calendar.MONTH) + 1));
			file.writeInt(fromDate.get(Calendar.YEAR));
			GregorianCalendar toDate = salario.getTo_date();
			file.writeInt(toDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((toDate.get(Calendar.MONTH) + 1));
			file.writeInt(toDate.get(Calendar.YEAR));
			correcto = true;
		} catch (IOException e) {
			e.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		boolean eof = false;
		boolean correcto = false;
		boolean fechaCorrecta;
		int empNo;
		int salary;
		GregorianCalendar fromDate, toDate;
		SalarioDTO salario = (SalarioDTO) obj;

		while (!eof) {
			try {
				empNo = file.readInt();
				salary = file.readInt();
				int dia = file.readInt();
				int mes = file.readInt();
				int anio = file.readInt();
				fromDate = salario.getFrom_date();
				if (dia == fromDate.get(Calendar.DAY_OF_MONTH) && mes == fromDate.get(Calendar.MONTH)
						&& anio == fromDate.get(Calendar.YEAR)) {
					fechaCorrecta = true;
				} else {
					fechaCorrecta = false;
				}

				if (empNo != salario.getEmp_no() && salary != salario.getSalary() && !fechaCorrecta) {
					file.seek(file.getFilePointer() - 12);
				} else {
					file.seek(file.getFilePointer() - 20);
					guardarObj(obj);
					correcto = true;
				}
			} catch (EOFException e) {
				eof = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		return new LinkedList<Object>();
	}

	@Override
	public boolean borrar(Object obj) {
		boolean eof = false;
		boolean correcto = false;
		int empNo;
		SalarioDTO salario = (SalarioDTO) obj;
		SalarioDTO salarioVacio = new SalarioDTO();

		try {
			file.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (!eof) {
			try {
				empNo = file.readInt();
				if (empNo != salario.getEmp_no()) {
					file.seek(file.getFilePointer() + 28);
				} else {
					file.seek(file.getFilePointer() - 4);
					guardarObj(salarioVacio);
					correcto = true;
				}

			} catch (EOFException e) {
				eof = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return correcto;
	}
	
	public void cerrar() {
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FicheSalarios f = new FicheSalarios();
		int empNo;
		int salario;
		GregorianCalendar fromDate, toDate;
		//guardar funciona
		/*
		SalarioDTO s = new SalarioDTO(100, 2000, new GregorianCalendar(2010, 9, 10), new GregorianCalendar(2012,11,12));
		SalarioDTO j = new SalarioDTO(200, 1000, new GregorianCalendar(2003, 4, 3), new GregorianCalendar(2005,6,5));
		f.guardar(s);
		f.guardar(j);
		*/
		
		//leer 
		
		try {
			while(file.getFilePointer()<file.length()) {
				empNo=file.readInt();
				salario = file.readInt();
				int dia=file.readInt();
				int mes=file.readInt();
				int anio=file.readInt();
				int dia2=file.readInt();
				int mes2=file.readInt();
				int anio2=file.readInt();
				System.out.println("empNo: " + empNo + 
									" salario: " + salario
									+ " fromDate: " + dia + "/" + mes + "/" +anio+
									" toDate: " + dia2  + "/" + mes2 + "/" + anio2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//modificar
		/*
		SalarioDTO s = new SalarioDTO(200, 1000, new GregorianCalendar(2010, 9, 10), new GregorianCalendar(2004,4,4));
		f.modificar(s);
		*/
		
		//borrar
		/*
		SalarioDTO s = new SalarioDTO(100, 2000, new GregorianCalendar(2010, 9, 10), new GregorianCalendar(2012,11,12));
		f.borrar(s);
		*/
		
		f.cerrar();
	}

}
