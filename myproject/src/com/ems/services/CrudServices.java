package com.ems.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.ems.enums.UpdateChoices;
import com.ems.interfaces.EmsInterface;
import com.ems.model.Employee;
import com.ems.util.EmailValidation;
import com.ems.util.FileUtil;
import com.ems.util.IdValidation;
import com.ems.util.NameValidation;
import com.ems.util.PhoneNoValidation;

public class CrudServices implements EmsInterface {

	public static Map<String, Employee> empMap = new HashMap<>();
	Scanner scanner = new Scanner(System.in);

	public CrudServices() {
		empMap = FileUtil.writeFromJson();
	}

	// create
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
		FileUtil.saveAsJson(empMap);
		System.out.println("Employee added successfully!");
	}

	// disp all
	public void displayEmployeeList() {
		System.out.println("--------------------------------------------");
		if (empMap.isEmpty()) {
			System.out.println("No records found");
			return;
		}
		for (Map.Entry<String, Employee> entry : empMap.entrySet()) {
			System.out.println("Employee ID: " + entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("--------------------------------");
		}
	}

	// disp by id
	public void displayEmployeesById() {
		System.out.println("--------------------------------");
		System.out.println("Enter Employee ID:");
		String empId = scanner.nextLine().trim().toUpperCase();
		if (empMap.containsKey(empId)) {
			System.out.println(empMap.get(empId));
			System.out.println("--------------------------------");
		} else {
			System.out.println("No record found");
			System.out.println("--------------------------------");
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
					System.out.println("Employee ID: " + entry.getKey());
					System.out.println(emp);
					System.out.println("--------------------------------");
					found = true;
				}
			}
		}
		if (!found) {
			System.out.println("No employees found with name: " + searchName);
			System.out.println("--------------------------------");
		}
	}

	
	public void updateEmployeeDetails() {
		System.out.println("--------------------------------");
		System.out.println("Enter Employee ID to update:");
		String empId = scanner.nextLine().trim().toUpperCase();

		if (!empMap.containsKey(empId)) {
			System.out.println("No record found! Cannot update.");
			return;
		}

		Employee employee = empMap.get(empId);

		for (UpdateChoices option : UpdateChoices.values()) {
			System.out.println(option);
		}
		System.out.print("Enter your choice: ");
		String choice = scanner.nextLine().trim().toUpperCase();

		UpdateChoices updateChoice;
		try {
			updateChoice = UpdateChoices.valueOf(choice);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid choice!");
			return;
		}

		switch (updateChoice) {

		case NAME:
			while (true) {
				System.out.println("name in records:" + employee.getEmpName());
				System.out.println("Enter New Name:");
				String empName = scanner.nextLine().trim();
				if (NameValidation.isValid(empName)) {
					employee.setEmpName(empName);
					break;
				} else {
					System.out.println("Invalid name. Try again.");
				}
			}
			break;

		case EMAIL:
			while (true) {
				System.out.println("old email:" + employee.getEmpEmail());
				System.out.println("Enter New Email:");
				String empEmail = scanner.nextLine().trim();
				if (EmailValidation.isValid(empEmail)) {
					employee.setEmpEmail(empEmail);
					break;
				} else {
					System.out.println("Invalid email. Try again.");
				}
			}
			break;

		case SALARY:
			while (true) {
				System.out.println("prev salary:" + employee.getEmpSalary());
				System.out.println("Enter New Salary:");
				double empSalary = scanner.nextDouble();
				scanner.nextLine();
				if (empSalary > 1000) {
					employee.setEmpSalary(empSalary);
					break;
				} else {
					System.out.println("invalid salary, enter again");
				}
			}
			break;

		case PHONENUMBER:
			while (true) {
				System.out.println("prev phone number:" + employee.getEmpPhoneNumber());
				System.out.println("Enter New Phone Number:");
				String empPhoneNumber = scanner.nextLine().trim();
				if (PhoneNoValidation.isValid(empPhoneNumber)) {
					employee.setEmpPhoneNumber(empPhoneNumber);
					break;
				} else {
					System.out.println("Invalid phone. Try again.");
				}
			}
			break;
		}

		empMap.put(empId, employee);
		FileUtil.saveAsJson(empMap);
		System.out.println("Employee updated successfully!");
		System.out.println("--------------------------------");
	}

	// DELETE
	public void deleteEmployeeDetails() {
		System.out.println("Enter Employee ID to delete:");
		String empId = scanner.nextLine().trim().toUpperCase();
		Employee removed = empMap.remove(empId);
		if (removed != null) {
			FileUtil.saveAsJson(empMap);
			System.out.println("Employee deleted successfully.");
			System.out.println("--------------------------------");
		} else {
			System.out.println("Record not found.");
			System.out.println("--------------------------------");
		}
	}}
