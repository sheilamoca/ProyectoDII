package Modelo;

public class DepartamentoDTO {

	private String dept_no;
	private String dept_name;
	
	public DepartamentoDTO(String dept_no, String dept_name) {
		super();
		StringBuffer dept_noFijo = new StringBuffer(dept_no);
		dept_noFijo.setLength(4);
		StringBuffer dept_nameFijo = new StringBuffer(dept_name);
		dept_nameFijo.setLength(40);
		this.dept_no = dept_noFijo.toString();
		this.dept_name = dept_nameFijo.toString();
	}
	
	public DepartamentoDTO() {
		StringBuffer dept_noFijo = new StringBuffer("");
		dept_noFijo.setLength(4);
		StringBuffer dept_nameFijo = new StringBuffer("");
		dept_nameFijo.setLength(40);
		dept_no = dept_noFijo.toString();
		dept_name= dept_nameFijo.toString();
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
}
