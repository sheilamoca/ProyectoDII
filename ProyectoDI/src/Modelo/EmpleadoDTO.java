package Modelo;

import java.util.GregorianCalendar;

public class EmpleadoDTO {

	private int emp_no;
	private GregorianCalendar birth_date;
	private String first_name;
	private String last_name;
	private Gender genero;
	private GregorianCalendar hire_date;
	
	
	public EmpleadoDTO(int id, GregorianCalendar fecha_nac, String nombre, String apellido,
			GregorianCalendar fecha_contrato, Gender genero) {
		super();
		this.emp_no = id;
		this.birth_date = fecha_nac;
		this.first_name = nombre;
		this.last_name = apellido;
		this.hire_date = fecha_contrato;
		this.genero = genero;
	}

	public EmpleadoDTO() {
		this.emp_no = 0;
		this.birth_date = new GregorianCalendar(0,0,0);
		this.first_name = "";
		this.last_name = "";
		this.hire_date = new GregorianCalendar(0,0,0);
		this.genero = Gender.M;
	}

	
	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public GregorianCalendar getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(GregorianCalendar birth_date) {
		this.birth_date = birth_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Gender getGenero() {
		return genero;
	}

	public void setGenero(Gender genero) {
		this.genero = genero;
	}

	public GregorianCalendar getHire_date() {
		return hire_date;
	}

	public void setHire_date(GregorianCalendar hire_date) {
		this.hire_date = hire_date;
	}

}
