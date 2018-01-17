package Persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import Modelo.Actualizable;
import Modelo.DepartamentoDTO;
import Modelo.SalarioDTO;

public class FicheDepartamentos implements Actualizable {

	private static RandomAccessFile fileRandom;
	private static final int FICHBYTES = 88;

	public FicheDepartamentos() {
		File fichero = new File("FicheDepartamentos.dat");

		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
			}

			fileRandom = new RandomAccessFile(fichero, "rw");
		} catch (IOException ioe) {
			ioe.printStackTrace(System.out);
		}
	}
	
	private boolean guardarObj(Object obj) {
		boolean correcto;

		DepartamentoDTO departamento = (DepartamentoDTO) obj;

		try {
			fileRandom.writeChars(departamento.getDept_no());
			fileRandom.writeChars(departamento.getDept_name());
			correcto = true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			correcto = false;
		}

		return correcto;
	}
	
	@Override
	public boolean guardar(Object obj) {
		boolean correcto;

		DepartamentoDTO departamento = (DepartamentoDTO) obj;

		try {
			DepartamentoDTO dep = (DepartamentoDTO) buscar(departamento.getDept_no());
			if(!dep.getDept_no().equals(departamento.getDept_no())) {
			long pos = fileRandom.length();
			fileRandom.seek(pos);
			fileRandom.writeChars(departamento.getDept_no());
			fileRandom.writeChars(departamento.getDept_name());
			correcto = true;
			}else {
				correcto=false;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	@Override
	public boolean modificar(Object obj) {
		boolean eof = false;
		boolean correcto = false;

		String dept_no;

		DepartamentoDTO departamento = (DepartamentoDTO) obj;
		try {
			fileRandom.seek(0);
		} catch (IOException ioe) {
			ioe.printStackTrace(System.out);
		}

		while (!eof) {

			try {
				dept_no = "";
				for (int i = 1; i <= 4; i++) {
					dept_no = dept_no + fileRandom.readChar();
				}
				if (!dept_no.equals(departamento.getDept_no())) {
					fileRandom.seek(fileRandom.getFilePointer() + 80);
				} else {
					fileRandom.seek(fileRandom.getFilePointer() - 8);
					guardarObj(obj);
					correcto = true;
				}

			} catch (EOFException eoef) {
				eof = true;
			} catch (IOException ioe) {
				ioe.printStackTrace(System.out);
			}
		}

		return correcto;
	}

	@Override
	public LinkedList<Object> listar() {
		LinkedList<Object> lista = new LinkedList<Object>();
		boolean eof = false;
		String dept_no;
		String dept_name;
		
		DepartamentoDTO departamento;

		try {
			fileRandom.seek(0);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

		while (!eof) {
			try {
				
				dept_no = "";
				for (int i = 1; i <= 4; i++) {
					dept_no = dept_no + fileRandom.readChar();
				}
				if (dept_no.trim().equals("")) {
					fileRandom.seek(fileRandom.getFilePointer() + 80);
				} else {
					dept_name = "";
					for (int i = 1; i <= 40; i++) {
						dept_name = dept_name + fileRandom.readChar();
					}
					departamento = new DepartamentoDTO(dept_no, dept_name);
					lista.add(departamento);
				}
			} catch (EOFException eoef) {
				eof = true;
			} catch (IOException ioe) {
				ioe.printStackTrace(System.out);
			}
		}

		return lista;

	}

	@Override
	public boolean borrar(Object obj) {
		boolean eof = false;
		boolean correcto = false;
		String dept_no;

		DepartamentoDTO departamento = (DepartamentoDTO) obj;
		DepartamentoDTO departVacio = new DepartamentoDTO();

		try {
			fileRandom.seek(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!eof) {
			try {
				dept_no = "";
				for (int i = 1; i <= 4; i++) {
					dept_no = dept_no + fileRandom.readChar();
				}
				if (!dept_no.equals(departamento.getDept_no())) {
					fileRandom.seek(fileRandom.getFilePointer() + 80);
				} else {
					fileRandom.seek(fileRandom.getFilePointer() - 8);
					guardarObj(departVacio);
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

	public Object buscar(String clave) {
		boolean eof = false;
		boolean encontrado = false;

		String dept_no, dept_name;
		DepartamentoDTO departamento = new DepartamentoDTO();

		try {
			fileRandom.seek(0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (!eof && !encontrado) {
			try {
				dept_no = "";
				for (int i = 1; i <= 4; i++) {
					dept_no = dept_no + fileRandom.readChar();
				}
				if (!dept_no.equals(clave)) {
					fileRandom.seek(fileRandom.getFilePointer() + 80);
				} else {
					fileRandom.seek(fileRandom.getFilePointer() - 8);
					
					dept_no = "";
					for (int i = 1; i <= 4; i++) {
						dept_no = dept_no + fileRandom.readChar();
					}
					dept_name = "";
					for (int i = 1; i <= 40; i++) {
						dept_name = dept_name + fileRandom.readChar();
					}

					departamento = new DepartamentoDTO(dept_no, dept_name);
					encontrado = true;
				}
			} catch (EOFException e) {
				eof = true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return departamento;

	}

	public void cerrar() {
		try {
			fileRandom.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FicheDepartamentos f = new FicheDepartamentos();

		LinkedList<Object> list;

		// guardar
		
		 StringBuffer st = new StringBuffer("1"); st.setLength(4); String dept_no =
		 st.toString(); StringBuffer str = new StringBuffer("Marketing");
		 str.setLength(40); String dept_name = str.toString();
		 
		 DepartamentoDTO depart = new DepartamentoDTO(dept_no, dept_name);
		 
		 f.guardar(depart);
		 
		 StringBuffer st2 = new StringBuffer("2"); st2.setLength(4); String dept_no2 =
		 st2.toString(); StringBuffer str2 = new StringBuffer("IT");
		 str2.setLength(40); String dept_name2 = str2.toString();
		 
		 DepartamentoDTO depart2 = new DepartamentoDTO(dept_no2, dept_name2);
		 
		 f.guardar(depart2);
		 
		
		// listar
		/*
		 list = f.listar(); for (int i = 0; i < list.size(); i++) { DepartamentoDTO
		 depart = (DepartamentoDTO) list.get(i);
		 System.out.println(depart.getDept_no() + " " + depart.getDept_name()); }
		 */
		
		// modificar
		/*
		 StringBuffer st2 = new StringBuffer("1"); st2.setLength(4); String dept_no2 =
		 st2.toString(); StringBuffer str2 = new StringBuffer("Yoqsetioxdxd");
		 str2.setLength(40); String dept_name2 = str2.toString();
		 
		 DepartamentoDTO depart2 = new DepartamentoDTO(dept_no2, dept_name2);
		 
		 
		 f.modificar(depart2);
		 */
		
		// buscar
		/*
		DepartamentoDTO depart;
		StringBuffer s = new StringBuffer("2");
		s.setLength(4);
		depart = (DepartamentoDTO) f.buscar(s.toString());
		System.out.println(depart.getDept_no() + " " + depart.getDept_name());
		*/
		//borrar falla
		 /*
		DepartamentoDTO depart;
		StringBuffer s = new StringBuffer("2");
		s.setLength(4);
		depart = (DepartamentoDTO) f.buscar(s.toString());
		f.borrar(depart);
		*/
		f.cerrar();

	}

}
