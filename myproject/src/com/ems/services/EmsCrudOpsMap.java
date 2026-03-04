package com.ems.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.ems.model.Employee;
import com.ems.util.EmailValidation;
import com.ems.util.FileUtil;
import com.ems.util.IdValidation;
import com.ems.util.NameValidation;

import com.ems.util.PhoneNoValidation;

public class EmsCrudOpsMap {

	public static Map<String, Employee> empMap = new HashMap<>();
	Scanner scanner = new Scanner(System.in);

	public EmsCrudOpsMap() {
		FileUtil.loadEmployees(empMap);
	}

	// CREATE
	public void addEmployeeDetails() {

		System.out.println("--------------------------------------------");
		System.out.println("Employee Id:");

		String empId = scanner.nextLine().toUpperCase().trim();

		if (!IdValidation.isValid(empId)) {
			System.out.println("Invalid id, enter proper format");
			return;
		}

		if (empMap.containsKey(empId)) {
			System.out.println("ID already exists! Cannot insert.");
			return;
		}

		String empName;
		while (true) {
			System.out.println("Name:");
			empName = scanner.nextLine().trim();

			if (!NameValidation.isValid(empName)) {
				System.out.println("Invalid name. Only alphabets allowed.");
				continue;
			}
			break;
		}

		System.out.println("Email:");
		String empEmail = scanner.nextLine().trim();

		if (!EmailValidation.isValid(empEmail)) {
			System.out.println("Invalid email format");
			return;
		}

		System.out.println("Salary:");
		double empSalary = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("Phone Number:");
		String empPhoneNumber = scanner.nextLine().trim();

		if (!PhoneNoValidation.isValid(empPhoneNumber)) {
			System.out.println("Invalid phone number");
			return;
		}

		empMap.put(empId, new Employee(empName, empEmail, empSalary, empPhoneNumber));

		FileUtil.saveEmployee(empId, empMap.get(empId));
		System.out.println("Employee added successfully!");
	}

	// DISPLAY ALL
	public void displayEmployeeList() {

		System.out.println("--------------------------------------------");

		if (empMap.isEmpty()) {
			System.out.println("No records found");
			return;
		}

		for (Map.Entry<String, Employee> entry : empMap.entrySet()) {
			System.out.println("Employee ID: " + entry.getKey() + " -> " + entry.getValue());
			System.out.println("--------------------------------");
		}
	}

	// DISPLAY BY ID
	public void displayEmployeesById() {

		System.out.println("--------------------------------");
		System.out.println("Enter Employee ID:");

		String empId = scanner.nextLine().trim().toUpperCase();

		if (empMap.containsKey(empId)) {
			System.out.println(empMap.get(empId));
		} else {
			System.out.println("No record found");
		}
	}

	// DISPLAY BY NAME
	public void displayEmployeesByName() {

		System.out.println("--------------------------------");
		System.out.println("Enter Employee name to search:");

		String searchName = scanner.nextLine().trim();
		boolean found = false;

		for (Map.Entry<String, Employee> entry : empMap.entrySet()) {

			Employee emp = entry.getValue();
			String[] nameSplit = emp.getEmpName().split("\\s+");

			for (String part : nameSplit) {
				if (part.equalsIgnoreCase(searchName)) {
					System.out.println("Employee ID: " + entry.getKey() + " -> " + emp);
					System.out.println("--------------------------------");
					found = true;
				}
			}
		}

		if (!found) {
			System.out.println("No employees found with name: " + searchName);
		}
	}

	// UPDATE
	public void updateEmployeeDetails() {

		System.out.println("--------------------------------");
		System.out.println("Enter Employee ID to update:");

		String empId = scanner.nextLine().trim().toUpperCase();

		if (!empMap.containsKey(empId)) {
			System.out.println("No record found! Cannot update.");
			return;
		}

		Employee employee = empMap.get(empId);

		// Update Name
		while (true) {
			System.out.println("Enter New Name:");
			String empName = scanner.nextLine().trim();

			if (NameValidation.isValid(empName)) {
				employee.setEmpName(empName);
				break;
			} else {
				System.out.println("Invalid name. Try again.");
			}
		}

		// Update Email id
		while (true) {
			System.out.println("Enter New Email:");
			String empEmail = scanner.nextLine().trim();

			if (EmailValidation.isValid(empEmail)) {
				employee.setEmpEmail(empEmail);
				break;
			} else {
				System.out.println("Invalid email format. Try again.");
			}
		}

		System.out.println("Enter New Salary:");
		double empSalary = scanner.nextDouble();
		scanner.nextLine();
		employee.setEmpSalary(empSalary);
		System.out.println("Phone Number:");
		String empPhoneNumber = scanner.nextLine().trim();

		if (!PhoneNoValidation.isValid(empPhoneNumber)) {
			System.out.println("Invalid phone number");
			return;
		}

		employee.setEmpPhoneNumber(empPhoneNumber);
		FileUtil.rewriteFile(empMap);
		System.out.println("Employee updated successfully!");
	}

	// DEL
	public void deleteEmployeeDetails() {

		System.out.println("Enter Employee ID to delete:");
		String empId = scanner.nextLine().trim().toUpperCase();

		Employee removed = empMap.remove(empId);

		if (removed != null) {
			FileUtil.rewriteFile(empMap);
			System.out.println("Employee deleted successfully.");
		} else {
			System.out.println("Record not found.");
		}
	}
}