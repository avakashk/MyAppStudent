package com.avak.model;

public class StudentBean 
{
	private int regno;
	private String fname;
	private String lname;
	private String gfname;
	private String glname;
	private String password;
	private String admin;
	public int getRegno() {
		return regno;
	}
	public void setRegno(int regno) {
		this.regno = regno;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGfname() {
		return gfname;
	}
	public void setGfname(String gfname) {
		this.gfname = gfname;
	}
	public String getGlname() {
		return glname;
	}
	public void setGlname(String glname) {
		this.glname = glname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public StudentBean(int regno, String fname, String lname, String gfname, String glname, String password,
			String admin) {
		super();
		this.regno = regno;
		this.fname = fname;
		this.lname = lname;
		this.gfname = gfname;
		this.glname = glname;
		this.password = password;
		this.admin = admin;
	}
	public StudentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	

}
