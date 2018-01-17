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
import Modelo.Dept_empDTO;

public class FicheDepartamentoEmpleado implements Actualizable {
	
	private final int reg = 4 + 8 + 12 + 12;
	private RandomAccessFile file;

	
	public FicheDepartamentoEmpleado() {
		try {
			file = new RandomAccessFile(new File("FicheDepartamentoEmpleado.dat"), "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try
	}// builder

	@Override
	public boolean guardar(Object obj) {
		
		boolean guardado = false;
		
		try {
			long pos= file.length();
			file.seek(pos);
			
			Dept_empDTO dto = (Dept_empDTO) obj;

			file.writeInt(dto.getEmp_no());
			file.writeChars(dto.getDept_no());

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

			
			Dept_empDTO dto = (Dept_empDTO) obj;

			file.writeInt(dto.getEmp_no());
			file.writeChars(dto.getDept_no());

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
			
			Dept_empDTO dept_Emple= new Dept_empDTO();
			guardarObject(dept_Emple);
		
			
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
		
		Dept_empDTO registro = (Dept_empDTO) obj;
		
		/*Ponemos el puntero en la posicion inicial del documento para leerlo secuencialmente*/
		
		file.seek(0);
		numeroEmpleado = file.readInt();
		codigoDepartamento = construirCadena(file, 4);

		/*Si al leer el numero del empleado y el codigo del departamento es igual al numero de empleado no entra en la repetitiva
		  Lo cual esta en la primera linea*/
		
		empleadoDepartamento = numeroEmpleado == registro.getEmp_no() & codigoDepartamento.equals(registro.getDept_no());
		
		while(!eof & !empleadoDepartamento) {
			
			/*Si entra al bucle mientras no termine el fichero o sea el numero y codigo del empleado en ese departamento sigue sumando 
			  posiciones de linea*/
			
			try {
				posicionRegistro++;
				file.seek(reg*posicionRegistro);
				numeroEmpleado = file.readInt();
				codigoDepartamento = construirCadena(file, 4);
				
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
