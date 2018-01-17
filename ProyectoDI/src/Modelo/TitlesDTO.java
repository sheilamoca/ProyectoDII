package Modelo;

import java.util.GregorianCalendar;

public class TitlesDTO {
	
	private int emp_no=0;
	private String title;
	private GregorianCalendar from_date;
	private GregorianCalendar to_date;
	
	
	public TitlesDTO() {
	 StringBuffer st= new StringBuffer("");
	 st.setLength(100);
	 title=st.toString();
	 from_date= new GregorianCalendar(0,0,0);
	 to_date=new GregorianCalendar(0,0,0);
	}
	
	public TitlesDTO(int emp, String titulo, GregorianCalendar fechaCargo) {
		super();
		emp_no = emp;
		title = titulo;
		from_date = fechaCargo;
		to_date= new GregorianCalendar(0,0,0);
	}
		
	
	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
