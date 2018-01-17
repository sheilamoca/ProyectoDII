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
import Modelo.TitlesDTO;

public class FicheTitles implements Actualizable {
	
	private static RandomAccessFile file;
	private static final int DIM=228;

	public FicheTitles() {
		File fichero = new File("FicheTitles.dat");
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
			}

			file = new RandomAccessFile(fichero, "rw");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean guardar(Object obj) {
		boolean correcto;
		TitlesDTO titulo= (TitlesDTO) obj;
		try {
			long pos= file.length();
			file.seek(pos);
			
			file.writeInt(titulo.getEmp_no());
			file.writeChars(titulo.getTitle());
			GregorianCalendar fromDate=titulo.getFrom_date();
			file.writeInt(fromDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fromDate.get(Calendar.MONTH) +1));
			file.writeInt(fromDate.get(Calendar.YEAR));
			GregorianCalendar toDate= titulo.getTo_date();
			file.writeInt(toDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((toDate.get(Calendar.MONTH) +1));
			file.writeInt(toDate.get(Calendar.YEAR));
			correcto=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			correcto=false;
		}
		
		return correcto;
	}
////////////////////////////////
	
	
	private boolean guardarObject(Object obj) {
		boolean correcto;
		TitlesDTO titulo= (TitlesDTO) obj;
		try {

			
			file.writeInt(titulo.getEmp_no());
			file.writeChars(titulo.getTitle());
			GregorianCalendar fromDate=titulo.getFrom_date();
			file.writeInt(fromDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fromDate.get(Calendar.MONTH) +1));
			file.writeInt(fromDate.get(Calendar.YEAR));
			GregorianCalendar toDate= titulo.getTo_date();
			file.writeInt(toDate.get(Calendar.DAY_OF_MONTH));
			file.writeInt((toDate.get(Calendar.MONTH) +1));
			file.writeInt(toDate.get(Calendar.YEAR));
			correcto=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			correcto=false;
		}
		
		return correcto;
	}
	
	////////////////////////
	@Override
	public boolean modificar(Object obj) {
		boolean eof=false;
		boolean correcto=false;
		boolean fechaCorrecta;
		int empNo;
		String title;
		GregorianCalendar fromDate,toDate;
		TitlesDTO titulo= (TitlesDTO)obj;
		while(!eof) {
			try {
				empNo=file.readInt();
				title="";
				for(int i=1;i<=100;i++) {
					title= title + file.readChar();
				}
				int dia=file.readInt();
				int mes=file.readInt();
				int anio=file.readInt();
				fromDate=titulo.getFrom_date();
				if(dia==fromDate.get(Calendar.DAY_OF_MONTH) && mes==fromDate.get(Calendar.MONTH)&& anio==fromDate.get(Calendar.YEAR) ) {
					fechaCorrecta=true;
				}else {
					fechaCorrecta=false;
				}
				
				if(empNo!= titulo.getEmp_no() && title!= titulo.getTitle()&&fechaCorrecta) {
					file.seek(file.getFilePointer()+12);
				}else {
					file.seek(file.getFilePointer()- 216);
					guardarObject(obj);
					correcto=true;
				}

			}catch (EOFException eo) {
				eof=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return correcto;
	}
/////////////////////////////
	@Override
	public LinkedList<Object> listar() {
		return new LinkedList<Object>();
	}
////////////////////////////////
	@Override
	public boolean borrar(Object obj) {

		boolean eof=false;
		boolean correcto=false;
		boolean fechaCorrecta;
		int empNo;
		String title;
		GregorianCalendar fromDate,toDate;
		TitlesDTO titulo= (TitlesDTO)obj;
		while(!eof) {
			try {
				empNo=file.readInt();
				title="";
				for(int i=1;i<=100;i++) {
					title= title + file.readChar();
				}
				int dia=file.readInt();
				int mes=file.readInt();
				int anio=file.readInt();
				fromDate=titulo.getFrom_date();
				if(dia==fromDate.get(Calendar.DAY_OF_MONTH) && mes-1==fromDate.get(Calendar.MONTH) && anio==fromDate.get(Calendar.YEAR) ) {
					fechaCorrecta=true;
				}else {
					fechaCorrecta=false;
				}
				if(empNo!= titulo.getEmp_no() && title!= titulo.getTitle()&& !fechaCorrecta) {
					file.seek(file.getFilePointer()+12);
				}else {
					file.seek(file.getFilePointer()-216);
					TitlesDTO titles= new TitlesDTO();
					guardarObject(titles);
					correcto=true;
				}

			}catch (EOFException eo) {
				eof=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return correcto;

	}

	public void cerrar() {
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
///////////////////////////		
		
	}
	
	public static void main(String[] args) {
		FicheTitles f= new FicheTitles();
		int empNo;
		String titulo;
		GregorianCalendar fromDate,toDate;
		/*
		
		//Probar guardar ///la posicion hay que controlarla antes del guardar
		
		
		StringBuffer str= new StringBuffer("hola");
		str.setLength(100);
		TitlesDTO title= new TitlesDTO(100,str.toString(),new GregorianCalendar(2008,3,15));
		
		f.guardar(title);
		
		StringBuffer st= new StringBuffer("adios");
		st.setLength(100);
		title= new TitlesDTO(200,st.toString(),new GregorianCalendar(2017,2,12));
		
		f.guardar(title);
		
	*/
		
		
		//Leer archivo
		
		try {
			while(file.getFilePointer()<file.length()) {
				empNo=file.readInt();
				titulo="";
				for(int i=1;i<=100;i++) {
					titulo= titulo + file.readChar();
				}
				titulo=titulo.trim();
				int dia=file.readInt();
				int mes=file.readInt();
				int anio=file.readInt();
				int dia2=file.readInt();
				int mes2=file.readInt();
				int anio2=file.readInt();
				System.out.println("empNo: " + empNo + 
									" title: " + titulo
									+ " fromDate" + dia + "/" + mes + "/" +anio+
									" toDate: " + dia2  + "/" + mes2 + "/" + anio2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*
		 
		//modificar un title
		 
		StringBuffer st= new StringBuffer("hola");
		st.setLength(100);
		TitlesDTO title= new TitlesDTO(100,st.toString(),new GregorianCalendar(2008,3,15));
		title.setTo_date(new GregorianCalendar(2020,3,21));
		f.modificar(title);
		*/
		
		/*
		//borrar title
		
		StringBuffer str= new StringBuffer("hola");
		str.setLength(100);
		TitlesDTO title= new TitlesDTO(100,str.toString(),new GregorianCalendar(2008,3,15));
		System.out.println( f.borrar(title));
		*/
		f.cerrar();
	}

}

