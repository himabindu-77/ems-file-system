package com.ems.Main;

import java.util.Scanner;

import com.ems.enums.*;
import com.ems.services.EmsCrudOpsMap;

public class Menu {

	Scanner scanner = new Scanner(System.in);
	EmsCrudOpsMap service = new EmsCrudOpsMap();

	public void start() {
		MenuChoices choice = null;
		String input = null;
		do {

			for (MenuChoices option : MenuChoices.values()) {
				System.out.println(option);
			}
			System.out.print("Enter your choice: ");

			try {
				input = scanner.nextLine().trim().toUpperCase();
				choice = MenuChoices.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid choice! Try again.");
				continue;
			}
			switch (choice) {
			case INSERT -> service.addEmployeeDetails();
			case DISPLAY_ALL -> service.displayEmployeeList();
			case DISPLAY_BY_ID -> service.displayEmployeesById();
			case DISPLAY_BY_NAME -> service.displayEmployeesByName();
			case UPDATE -> service.updateEmployeeDetails();
			case DELETE -> service.deleteEmployeeDetails();
			case EXIT -> System.out.println("Exiting...");
			}
		} while (choice != MenuChoices.EXIT);
		scanner.close();
	}
}