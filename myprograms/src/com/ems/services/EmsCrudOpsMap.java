package com.ems.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ems.model.Employee;
import com.ems.util.EmailValidation;
import com.ems.util.FileUtil;
import com.ems.util.IdValidation;
import com.ems.util.NameValidation;
import com.ems.util.PhoneNoValidation;

public class EmsCrudOpsMap {
	public static Map<String, Employee> empMap = new HashMap<>();
	Scanner scanner = new Scanner(System.in);
	
	// CREATE
	public void addEmployeeDetails() {
		System.out.println("--------------------------------------------");

		System.out.println("Employee Id:");

		String empId = scanner.nextLine().toUpperCase().trim();
		if (!IdValidation.isValid(empId)) {
			System.out.println("invalid id, enter proper format");
			return;
		}
		
		if (empMap.containsKey(empId)) {
			System.out.println(" ID already exists! Cannot insert.");
			return;
		}
		String empName;
		while (true) {
			System.out.println(" Name:");
			empName = scanner.nextLine().trim();
			if (!NameValidation.isValid(empName)) {
				System.out.println("invalid name only alphabets are allowed");
				continue;
			}
			break;
		}

		System.out.println("Email: ");
		String empEmail = scanner.nextLine().trim();

		if (!EmailValidation.isValid(empEmail)) {
			System.out.println("Invalid email format");
			return;
		}

		System.out.println(" salary :");
		double empSalary = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("PhoneNumber : ");
		String empPhoneNumber = scanner.nextLine().trim();
		if (!PhoneNoValidation.isValid(empPhoneNumber)) {
			System.out.println("invalid phone number");
			return;
		}

		empMap.put(empId, new Employee(empName, empEmail, empSalary, empPhoneNumber));
		System.out.println("Employee details added successfully");
		FileUtil.saveEmployee(empId, empMap.get(empId));
	}

	// DISPLAY (all)

	public void displayEmployeeList() {
		FileUtil.loadEmployees(EmsCrudOpsMap.empMap);

		System.out.println("--------------------------------------------");
		
		if (empMap.isEmpty()) {
			System.out.println("NO records found");
			return;
		}
		Set<Map.Entry<String, Employee>> empEntries = new HashSet<>(empMap.entrySet());
		for (Map.Entry<String, Employee> entry : empEntries) {
			System.out.println("Employee ID: " + entry.getKey() + " -> " + entry.getValue());
			System.out.println("--------------------------------");
		}
	}

//DISP by  ID
	public void displayEmployeesById() {
		FileUtil.loadEmployees(EmsCrudOpsMap.empMap);

		System.out.println("--------------------------------");
		System.out.println("Enter Employee ID to get full details: ");
		String empId = scanner.nextLine().trim().toUpperCase();
		// if (empMap.get(empId) != null) {
		if (empMap.containsKey(empId)) {
			System.out.println(empMap.get(empId));
		} else {
			System.out.println("NO record found");
		}
	}

	// disp all employee details by particular name
	public void displayEmployeesByName() {
		FileUtil.loadEmployees(EmsCrudOpsMap.empMap);

		System.out.println("--------------------------------");
		System.out.println("Enter Employee name to search:");
		String searchName = scanner.nextLine().trim();
		boolean found = false;
		System.out.println("Employee IDs with name containing:  " + searchName);

		for (Map.Entry<String, Employee> entry : empMap.entrySet()) {

			Employee emp = entry.getValue();
			String[] nameSplit = emp.getEmpName().split("\\s+");

			for (String part : nameSplit) {
				if (part.equalsIgnoreCase(searchName)) {
					System.out.println("Employee ID: " + entry.getKey() + " -> " + emp);
					found = true;
					System.out.println("---------------------");
				}
			}
		}
		if (!found) {
			System.out.println("No employees found with name:" + searchName);
		}
	}

	// Update Emp Details
	public void updateEmployeeDetails() {
		FileUtil.loadEmployees(EmsCrudOpsMap.empMap);

		System.out.println("--------------------------------");
		System.out.println("Enter emp Id to update:");
		String empId = scanner.nextLine().trim().toUpperCase();

		if (!empMap.containsKey(empId)) {
			System.out.println("NO record found! Cannot update.");
			return;
		}

		Employee employee = empMap.get(empId);

		// Validate and update name
		String empName;
		while (true) {
			System.out.println("Enter New Name:");
			empName = scanner.nextLine().trim();

			if (NameValidation.isValid(empName)) {
				employee.setEmpName(empName); 
				break;
			} else {
				System.out.println("Invalid name! Only alphabets are allowed. Try again.");
			}
		}

		String empEmail;
		while (true) {
			System.out.println("Enter New Email:");
			empEmail = scanner.nextLine().trim();

			if (EmailValidation.isValid(empEmail)) {
				employee.setEmpEmail(empEmail);
				break;
			} else {
				System.out.println("Invalid email format. Try again.");
			}
		}

		FileUtil.rewriteFile(empMap);
		System.out.println("Employee updated successfully and saved to file!");
	}

// delete emp record
	public void deleteEmployeeDetails() {
		FileUtil.loadEmployees(EmsCrudOpsMap.empMap);

		System.out.println("Enter Employee id to delete:");
		String empId = scanner.nextLine().trim().toUpperCase();

		Employee removed = empMap.remove(empId);
		if (removed != null) {
			System.out.println("Employee ID  " + empId + "  deleted successfully");
		} else {
			System.out.println(" record  NOT FOUND");
		}
	}

}
