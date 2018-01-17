package Modelo;

import java.util.GregorianCalendar;

public class SalarioDTO {

	private int emp_no;
	private int salary;
	private GregorianCalendar from_date;
	private GregorianCalendar to_date;
	
	public SalarioDTO() {
		emp_no=0;
		salary=0;
		 from_date= new GregorianCalendar(0,0,0);
		 to_date=new GregorianCalendar(0,0,0);
	}

	public SalarioDTO(int emp_no, GregorianCalendar from_date) {
		super();
		this.emp_no = emp_no;
		this.from_date = from_date;
		from_date= new GregorianCalendar(0,0,0);
		to_date=new GregorianCalendar(0,0,0);

	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public GregorianCalendar getFrom_date() {
		return from_date;
	}

	public void setFrom_date(GregorianCalendar from_date) {
		this.from_date = from_date;
	}

	public GregorianCalendar getTo_date() {
		return to_date;
	}

	public void setTo_date(GregorianCalendar to_date) {
		this.to_date = to_date;
	}

}
