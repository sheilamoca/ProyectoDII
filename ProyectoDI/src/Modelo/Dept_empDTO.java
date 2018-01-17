package Modelo;

import java.io.RandomAccessFile;
import java.util.GregorianCalendar;

public class Dept_empDTO{
	private int emp_no;
	private String dept_no;
	private GregorianCalendar from_date;
	private GregorianCalendar to_date;
	
	public Dept_empDTO() {
		StringBuffer dep= new StringBuffer("");
		dep.setLength(4);
		dept_no=dep.toString();
		emp_no=0;
		from_date= new GregorianCalendar(0,0,0);
		to_date=new GregorianCalendar(0,0,0);
	}
	
	public Dept_empDTO(int emp_no, String dept_no) {
		this.emp_no = emp_no;
		this.dept_no = dept_no;
		from_date= new GregorianCalendar(0,0,0);
		to_date=new GregorianCalendar(0,0,0);

	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
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
