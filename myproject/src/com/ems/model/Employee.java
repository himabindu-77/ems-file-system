package com.ems.model;

public class Employee  {
	private String empName;
	private String empEmail;
	private double empSalary;
	private String empPhoneNumber;

	public Employee() {
	}

	public Employee(String empName, String empEmail, double empSalary, String empPhoneNumber) {
		this.empName = empName;
		this.empEmail = empEmail;
		this.empSalary = empSalary;
		this.empPhoneNumber = empPhoneNumber;
	}

	@Override
	public String toString() {
		return ("empName=   " + empName + "\nempEmail=  " + empEmail + "\nempSalary= " + empSalary + "\nempPhoneNumber="
				+ empPhoneNumber);

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

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
}
