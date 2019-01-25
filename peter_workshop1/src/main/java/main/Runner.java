package main;

import controller.DatabaseController;
import controller.LoginController;
import dao.DaoFactory;

public class Runner extends DatabaseController {
	public static int databaseOption;
	
	public static void main(String[] args) {
		
		//request selectdb
		databaseOption = 0;
		new DaoFactory(databaseOption);
		
		ClearDatabase();
		InitDatabase();

		LoginController loginController = new LoginController();
		loginController.runController();
	}
}
