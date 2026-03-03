package com.hbp.progs.empmodl;

public class Employee {
	private String empName;
	private String empEmail;
	private String empPhoneNumber;
	
	public Employee(String empName, String empEmail,String empPhoneNumber) {
		this.empName = empName;
		this.empEmail = empEmail;
		this.empPhoneNumber = empPhoneNumber;
	}

		@Override
	public String toString() {
		return ("\nempName=   " + empName + 
				"\nempEmail=  " + empEmail + 
				"\nempPhoneNumber=" +empPhoneNumber);
	
	}

	public String getEmpPhoneNumber() {
		return empPhoneNumber;
	}



	public void setEmpPhoneNumber(String empPhoneNumber) {
		this.empPhoneNumber = empPhoneNumber;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
}