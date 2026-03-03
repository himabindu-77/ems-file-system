package com.ems.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.ems.model.Employee;

public class FileUtil {

	private static final String FILE_NAME = "employees.txt";

	public static void saveEmployee(String empId, Employee emp) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			bw.write(empId + "," + emp.getEmpName() + "," + emp.getEmpEmail() + "," + emp.getEmpSalary() + ","
					+ emp.getEmpPhoneNumber());
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error writing employee to file: ");
		}
	}

	public static void loadEmployees(Map<String, Employee> empMap) {
		File file = new File(FILE_NAME);

		if (!file.exists()) {
			System.out.println("File not found");
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length != 5) {
					System.out.println("Skipping invalid line (wrong number of fields): " + line);
					continue;
				}

				String id = data[0].trim().toUpperCase();
				String name = data[1].trim();
				String email = data[2].trim();
				String salaryStr = data[3].trim();
				String phone = data[4].trim();

				double salary = 0.0;
				try {
					salary = Double.parseDouble(salaryStr);
				} catch (NumberFormatException e) {
					System.out.println("Skipping invalid Salary: " + salaryStr);
					continue;
				}

				empMap.put(id, new Employee(name, email, salary, phone));
				//System.out.println("\n");
			}
		} catch (IOException e) {
			System.out.println("Error reading file: ");
		}
	}

	
	public static void rewriteFile(Map<String, Employee> empMap) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Map.Entry<String, Employee> entry : empMap.entrySet()) {
				Employee emp = entry.getValue();
				bw.write(entry.getKey() + "," + emp.getEmpName() + "," + emp.getEmpEmail() + "," + emp.getEmpSalary()
						+ "," + emp.getEmpPhoneNumber());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error rewriting file: ");
		}
	}
}