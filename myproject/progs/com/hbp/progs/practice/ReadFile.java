package com.hbp.progs.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) {

		File obj = new File("randomnew.txt");
		try {
			if (obj.createNewFile()) {
				System.out.println("File created: " + obj.getName());
			}
		} catch (IOException e) {
			System.out.println("Error while creating file.");
			e.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(obj);
			writer.write("\n new stuff is being written\n");

			writer.close();
		} catch (IOException e) {
			System.out.println("Error while writing to file.");
			e.printStackTrace();
		}

		try {
			Scanner reader = new Scanner(obj);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				System.out.println(data);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error while reading file.");
			e.printStackTrace();
		}
	}
}