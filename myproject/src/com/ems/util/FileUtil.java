package com.ems.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ems.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtil {

	private static String FILE_NAME = "employees.json";
	private static ObjectMapper mapper = new ObjectMapper();

	public static Map<String, Employee> writeFromJson() { // (json to obj)

		try {
			File file = new File(FILE_NAME);

			if (!file.exists() || file.length() == 0) {
				return new HashMap<>();
			}

			return mapper.readValue(file, new TypeReference<Map<String, Employee>>() {
			});

		} catch (Exception e) {

			e.printStackTrace();
			return new HashMap<>();
		}
	}

	public static void saveAsJson(Map<String, Employee> empMap) { // saving map to json//ser

		try {

			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_NAME), empMap);

		} catch (IOException e) {
			System.out.println("Error writing JSON file");
		}
	}
}
