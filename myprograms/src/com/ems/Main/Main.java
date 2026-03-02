package com.ems.Main;

import com.ems.services.EmsCrudOpsMap;
import com.ems.util.FileUtil;

public class Main {

	public static void main(String[] args) {
		//FileUtil.loadEmployees(EmsCrudOpsMap.empMap);
		Menu menu = new Menu();
		menu.start();
	}
}
