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
import Modelo.EmpleadoDTO;
import Modelo.Gender;

public class FicheEmpleados implements Actualizable {

	private static RandomAccessFile file;
	private static final int DIM=90;

	public FicheEmpleados() {
		File fichero = new File("FicheEmpleados.dat");
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
		
		EmpleadoDTO emplert= (EmpleadoDTO) obj;
		try {
			EmpleadoDTO em= (EmpleadoDTO) buscar(emplert.getEmp_no());
			if(em.getEmp_no()!=emplert.getEmp_no()) {
			long pos= file.length();
			file.seek(pos);
			file.writeInt(emplert.getEmp_no());
			GregorianCalendar fecha_nac=emplert.getBirth_date();
			file.writeInt(fecha_nac.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fecha_nac.get(Calendar.MONTH) +1));
			file.writeInt(fecha_nac.get(Calendar.YEAR));
			file.writeChars(emplert.getFirst_name() );
			file.writeChars(emplert.getLast_name() );
			GregorianCalendar fecha_cont=emplert.getHire_date() ;
			file.writeInt(fecha_cont.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fecha_cont.get(Calendar.MONTH) +1));
			file.writeInt(fecha_cont.get(Calendar.YEAR));
			file.writeChars(emplert.getGenero().toString());
			correcto=true;
			}else {
				correcto=false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			correcto=false;
		}
		

		
		return correcto;
	}

///////////////////////////////////////////////////////
	
	private boolean guardarObject(Object obj) {
		boolean correcto;
		
		EmpleadoDTO emplert= (EmpleadoDTO) obj;
		try {
			file.writeInt(emplert.getEmp_no());
			GregorianCalendar fecha_nac=emplert.getBirth_date();
			file.writeInt(fecha_nac.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fecha_nac.get(Calendar.MONTH) +1));
			file.writeInt(fecha_nac.get(Calendar.YEAR));
			file.writeChars(emplert.getFirst_name() );
			file.writeChars(emplert.getLast_name() );
			GregorianCalendar fecha_cont=emplert.getHire_date() ;
			file.writeInt(fecha_cont.get(Calendar.DAY_OF_MONTH));
			file.writeInt((fecha_cont.get(Calendar.MONTH) +1));
			file.writeInt(fecha_cont.get(Calendar.YEAR));
			file.writeChars(emplert.getGenero().toString());
			correcto=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			correcto=false;
		}
		
		return correcto;
	}
	
	////////////////////////////////
	@Override
	public boolean modificar(Object obj) {
		boolean eof=false;
		boolean correcto=false;
		int id,dia,mes,anio;
		String nombre,apellidos,genero;
		EmpleadoDTO emplert= (EmpleadoDTO)obj;
		try {
			file.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!eof) {
			try {
				id=file.readInt();
				if(id!= emplert.getEmp_no()) {
					file.seek(file.getFilePointer()+86);
				}else {
					file.seek(file.getFilePointer()-4);
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

//////////////////////////////////////////////////////
	public LinkedList<Object> listar() {
		LinkedList<Object> lista= new LinkedList<Object>();
		boolean eof=false;
		int id,dia,mes,anio;
		String nombre,apellidos;
		String genero;
		EmpleadoDTO Empleado;
		GregorianCalendar fechaNac, fechaCont;
		try {
			file.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!eof) {
			try {
				id=file.readInt();
				if(id==0) {
					file.seek(file.getFilePointer()+86);
				}else {
					dia=file.readInt();
					mes=file.readInt();
					anio=file.readInt();
					fechaNac= new GregorianCalendar(anio,mes-1,dia);
					nombre="";
					for(int i=1;i<=14;i++) {
						nombre=nombre+ file.readChar();
					}
					apellidos="";
					for(int i=1;i<=16;i++) {
					apellidos=apellidos+ file.readChar();
					}
					
					dia=file.readInt();
					mes=file.readInt();
					anio=file.readInt();
					fechaCont= new GregorianCalendar(anio,mes-1,dia);
					genero=String.valueOf(file.readChar());
					Gender gen;
					if(genero.equals("F")) {
						gen=Gender.F;
					}else {
						gen=Gender.M;
					}
					Empleado= new EmpleadoDTO(id,fechaNac,nombre,apellidos,fechaCont,gen);
					lista.add(Empleado);
				}

			}catch (EOFException eo) {
				eof=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return lista;
	}

/////////////////////////////////////////////////////////

	public boolean borrar(Object obj) {
		boolean eof=false;
		boolean correcto=false;
		int id,dia,mes,anio;
		String nombre,apellidos,genero;
		EmpleadoDTO emplert= (EmpleadoDTO)obj;
		EmpleadoDTO emplertVacio=new EmpleadoDTO();
		try {
			file.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!eof) {
			try {
				id=file.readInt();
				if(id!= emplert.getEmp_no()) {
					file.seek(file.getFilePointer()+86);
				}else {
					file.seek(file.getFilePointer()-4);
					guardarObject(emplertVacio);
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
	
	public Object buscar(int clave) {
		boolean eof=false;
		boolean encontrado=false;
		GregorianCalendar fechaNac,fechaCont;
		int id,dia,mes,anio;
		String nombre,apellidos,genero;
		EmpleadoDTO emplert= new EmpleadoDTO();
		try {
			file.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!eof && !encontrado) {
			try {
				id=file.readInt();
				if(id!= clave) {
					file.seek(file.getFilePointer()+86);
				}else {
					file.seek(file.getFilePointer()-4);
					emplert.setEmp_no(file.readInt());
					dia=file.readInt();
					mes=file.readInt();
					anio=file.readInt();
					fechaNac= new GregorianCalendar(anio,mes-1,dia);
					nombre="";
					for(int i=1;i<=14;i++) {
						nombre=nombre+ file.readChar();
					}
					apellidos="";
					for(int i=1;i<=16;i++) {
					apellidos=apellidos+ file.readChar();
					}
					
					dia=file.readInt();
					mes=file.readInt();
					anio=file.readInt();
					fechaCont= new GregorianCalendar(anio,mes-1,dia);
					genero=String.valueOf(file.readChar());
					Gender gen= Gender.valueOf(genero);
					emplert= new EmpleadoDTO(id,fechaNac,nombre,apellidos,fechaCont,gen);
					encontrado=true;
				}

			}catch (EOFException eo) {
				eof=true;
				System.out.println("eof");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("io");
			}
		}
		
		return emplert;
		
	}
	/////////////////////////////////////////
	public void cerrar() {
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		FicheEmpleados f= new FicheEmpleados();
		
		LinkedList<Object> list;

		
		//guardar
		
		StringBuffer st= new StringBuffer("conta");
		st.setLength(14);
		String nombre=st.toString();
		StringBuffer str= new StringBuffer("gacho");
		str.setLength(16);
		String apellido=str.toString();

		
		EmpleadoDTO emplert= new EmpleadoDTO(100, new GregorianCalendar(), nombre, apellido,
														new GregorianCalendar(),Gender.F);
		
		f.guardar(emplert);
		
		StringBuffer st2= new StringBuffer("julio");
		st2.setLength(14);
		String nombre2=st2.toString();
		StringBuffer str2= new StringBuffer("perez");
		str2.setLength(16);
		String apellido2=str2.toString();
		
		EmpleadoDTO emple= new EmpleadoDTO(200, new GregorianCalendar(), nombre2, apellido2,
														new GregorianCalendar(),Gender.F);
		
		f.guardar(emple);
		
		
		/*
		
		list=f.listar();
		for(int i=0; i< list.size();i++) {
			 EmpleadoDTO emple = (EmpleadoDTO)list.get(i);
			String fecha= emple.getBirth_date().get(Calendar.DAY_OF_MONTH) + "/" +
					(emple.getBirth_date().get(Calendar.MONTH) +1) + "/" +
					emple.getBirth_date().get(Calendar.YEAR);
			String fecha2= emple.getHire_date() .get(Calendar.DAY_OF_MONTH) + "/" +
					(emple.getHire_date() .get(Calendar.MONTH) +1) + "/" +
					emple.getHire_date() .get(Calendar.YEAR);
			System.out.println(emple.getEmp_no() + " " + fecha + " " + emple.getFirst_name()   + " " +
								emple.getLast_name()  + " " + fecha2 + " " + emple.getGenero() );
		}
		
		/*
		//modificar emple
		
		StringBuffer st2= new StringBuffer("antonio");
		st2.setLength(14);
		String nombre2=st2.toString();
		StringBuffer str2= new StringBuffer("perez");
		str2.setLength(16);
		String apellido2=str2.toString();
		
		EmpleadoDTO emple= new EmpleadoDTO(200, new GregorianCalendar(), nombre2, apellido2,
				new GregorianCalendar(),Gender.F);
		
		f.modificar(emple);
		*/
		/*
		//buscar emple
		
		EmpleadoDTO emple;
		
		emple=(EmpleadoDTO)f.buscar(100);
		String fecha= emple.getBirth_date().get(Calendar.DAY_OF_MONTH) + "/" +
				(emple.getBirth_date().get(Calendar.MONTH) +1) + "/" +
				emple.getBirth_date().get(Calendar.YEAR);
		String fecha2= emple.getHire_date() .get(Calendar.DAY_OF_MONTH) + "/" +
				(emple.getHire_date() .get(Calendar.MONTH) +1) + "/" +
				emple.getHire_date() .get(Calendar.YEAR);
		
		System.out.println(emple.getEmp_no() + " " + fecha + " " + emple.getFirst_name()   + " " +
				emple.getLast_name()  + " " + fecha2 + " " + emple.getGenero() );
*/
		
		
		/*
		EmpleadoDTO emple= new EmpleadoDTO(100, new GregorianCalendar(), "", "", new GregorianCalendar(), Gender.F);
		System.out.println(emple.getEmp_no());
		System.out.println(f.borrar(emple));
		*/
		f.cerrar();
		
	}


	
	
	
	}


