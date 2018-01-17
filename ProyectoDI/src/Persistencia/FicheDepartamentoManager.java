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
import Modelo.Dept_ManagerDTO;

public class FicheDepartamentoManager implements Actualizable {
	
	private final int reg = 4 + 8 + 12 + 12;
	private RandomAccessFile file;

	
	public FicheDepartamentoManager() {
		try {
			file = new RandomAccessFile(new File("FicheDepartamentoManager.dat"), "rw");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} // try
	}// builder

	@Override
	public boolean guardar(Object obj) {
		
		boolean guardado = false;
		
		try {
			long pos= file.length();
			file.seek(pos);
			
			Dept_ManagerDTO dto = (Dept_ManagerDTO) obj;

			file.writeChars(dto.getDept_no());
			file.writeInt(dto.getEmp_no());

			GregorianCalendar from = dto.getFrom_date();
			file.writeInt(from.get(Calendar.DAY_OF_MONTH));
			file.writeInt(from.get(Calendar.MONTH) - 1);
			file.writeInt(from.get(Calendar.YEAR));

			GregorianCalendar todate = dto.getTo_date();
			file.writeInt(todate.get(Calendar.DAY_OF_MONTH));
			file.writeInt(todate.get(Calendar.MONTH) - 1);
			file.writeInt(todate.get(Calendar.YEAR));

			guardado = true;
		
		} catch (IOException e) {
			e.printStackTrace();

		} // try

		return guardado;
	}//function

private boolean guardarObject(Object obj) {
		
		boolean guardado = false;
		
		try {
			long pos= file.length();
			file.seek(pos);
			
			Dept_ManagerDTO dto = (Dept_ManagerDTO) obj;

			file.writeChars(dto.getDept_no());
			file.writeInt(dto.getEmp_no());

			GregorianCalendar from = dto.getFrom_date();
			file.writeInt(from.get(Calendar.DAY_OF_MONTH));
			file.writeInt(from.get(Calendar.MONTH) - 1);
			file.writeInt(from.get(Calendar.YEAR));

			GregorianCalendar todate = dto.getTo_date();
			file.writeInt(todate.get(Calendar.DAY_OF_MONTH));
			file.writeInt(todate.get(Calendar.MONTH) - 1);
			file.writeInt(todate.get(Calendar.YEAR));

			guardado = true;
		
		} catch (IOException e) {
			e.printStackTrace();

		} // try

		return guardado;
	}//function
	@Override
	public boolean borrar(Object obj) {
		
		int pos = 0;
		boolean borrado = false;

		try {
			pos = buscarReg(obj);
			file.seek(reg*pos);
			
			Dept_ManagerDTO dep_mang= new Dept_ManagerDTO();
			guardar(dep_mang);

			
		} catch (IOException e) {
			e.printStackTrace();
		}//try
		
		return borrado;
	}//function

	@Override
	public boolean modificar(Object obj) {

		int pos = 0;
		boolean modificado = false;
		
		try {
		
			pos = buscarReg(obj);
			file.seek(reg*pos);
			
			guardarObject(obj);
		
		} catch (IOException e) {
			e.printStackTrace();
		}//try
		
		return modificado;
		
	}// function

	public LinkedList<Object> listar() {
		
		return new LinkedList<Object>();
	}//function
	
	private String construirCadena(RandomAccessFile ramdonFile, int tamaño) throws IOException {
		char[] builder;
		String cad = "";
		int i;

		builder = new char[tamaño];
		for (i = 0; i < builder.length; i++) {
			cad = cad + file.readChar();
		} // for

		return cad;
	}// function

	private GregorianCalendar construirCregCal(RandomAccessFile ramdonFile) throws IOException {
		int dia, mes, anio;

		dia = file.readInt();
		mes = file.readInt();
		anio = file.readInt();

		return new GregorianCalendar(anio, mes + 1, dia);
	}// function

	private int buscarReg(Object obj) throws IOException {
		
		int numeroEmpleado = 0;
		String codigoDepartamento = "";
		
		int posicionRegistro = 0;
		boolean eof = false;
		boolean empleadoDepartamento = false;
		
		Dept_ManagerDTO registro = (Dept_ManagerDTO) obj;
		
		/*Ponemos el puntero en la posicion inicial del documento para leerlo secuencialmente*/
		
		file.seek(0);
		codigoDepartamento = construirCadena(file, 4);
		numeroEmpleado = file.readInt();

		/*Si al leer el numero del empleado y el codigo del departamento es igual al numero de empleado no entra en la repetitiva
		  Lo cual esta en la primera linea*/
		
		empleadoDepartamento = numeroEmpleado == registro.getEmp_no() & codigoDepartamento.equals(registro.getDept_no());
		
		while(!eof & !empleadoDepartamento) {
			
			/*Si entra al bucle mientras no termine el fichero o sea el numero y codigo del empleado en ese departamento sigue sumando 
			  posiciones de linea*/
			
			try {
				posicionRegistro++;
				file.seek(reg*posicionRegistro);
				codigoDepartamento = construirCadena(file, 4);
				numeroEmpleado = file.readInt();
				
				empleadoDepartamento = numeroEmpleado == registro.getEmp_no() & codigoDepartamento.equals(registro.getDept_no());
			
			}catch(EOFException eofd) {
				eof = true;
				
			}catch (Exception e) {
				e.printStackTrace();
			}//try
		}//while
		
		return posicionRegistro;
	}//function

	public void cerrar() {
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}//class
